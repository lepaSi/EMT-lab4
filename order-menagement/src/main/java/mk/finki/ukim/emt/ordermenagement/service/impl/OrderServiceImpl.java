package mk.finki.ukim.emt.ordermenagement.service.impl;

import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import mk.finki.ukim.emt.ordermenagement.domain.exceptions.OrderIdNotExistException;
import mk.finki.ukim.emt.ordermenagement.domain.exceptions.OrderItemIdNotExistingException;
import mk.finki.ukim.emt.ordermenagement.domain.model.Order;
import mk.finki.ukim.emt.ordermenagement.domain.model.OrderId;
import mk.finki.ukim.emt.ordermenagement.domain.model.OrderItemId;
import mk.finki.ukim.emt.ordermenagement.domain.repository.OrderRepository;
import mk.finki.ukim.emt.ordermenagement.service.OrderService;
import mk.finki.ukim.emt.ordermenagement.service.forms.OrderForm;
import mk.finki.ukim.emt.ordermenagement.service.forms.OrderItemForm;
import mk.finki.ukim.emt.sharedkernel.domain.events.orders.OrderItemCreated;
import mk.finki.ukim.emt.sharedkernel.infra.DomainEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Validator;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DomainEventPublisher domainEventPublisher;
    private final Validator validator;

    @Override
    public OrderId placeOrder(OrderForm orderForm) {
        Objects.requireNonNull(orderForm,"order must not be null.");
        var constraintViolations = validator.validate(orderForm);
        if (constraintViolations.size()>0) {
            throw new ConstraintViolationException("The order form is not valid", constraintViolations);
        }

        var newOrder = orderRepository.saveAndFlush(toDomainObject(orderForm));
        newOrder.getOrderItemList().forEach(item->domainEventPublisher.publish(new OrderItemCreated(item.getProductId().getId(),item.getKolicinapn())));
        return newOrder.getId();
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return orderRepository.findById(id);
    }

    @Override
    public void addItem(OrderId orderId, OrderItemForm orderItemForm) throws OrderIdNotExistException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        order.addItem(orderItemForm.getProduct(), orderItemForm.getKolicinapn());
        orderRepository.saveAndFlush(order);
        domainEventPublisher.publish(new OrderItemCreated(orderItemForm.getProduct().getId().getId(),orderItemForm.getKolicinapn()));


    }

    @Override
    public void deleteItem(OrderId orderId, OrderItemId orderItemId) throws OrderIdNotExistException, OrderItemIdNotExistingException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        order.removeItem(orderItemId);
        orderRepository.saveAndFlush(order);

    }
    private Order toDomainObject(OrderForm orderForm){
        var order = new Order(Instant.now(),orderForm.getCurrency());
        orderForm.getItems().forEach(item-> order.addItem(item.getProduct(),item.getKolicinapn()));
        return order;
    }
}
