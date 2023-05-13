package mk.finki.ukim.emt.ordermenagement.service.impl;

import mk.finki.ukim.emt.ordermenagement.domain.exceptions.OrderIdNotExistException;
import mk.finki.ukim.emt.ordermenagement.domain.exceptions.OrderItemIdNotExistingException;
import mk.finki.ukim.emt.ordermenagement.domain.model.Order;
import mk.finki.ukim.emt.ordermenagement.domain.model.OrderId;
import mk.finki.ukim.emt.ordermenagement.domain.model.OrderItemId;
import mk.finki.ukim.emt.ordermenagement.service.OrderService;
import mk.finki.ukim.emt.ordermenagement.service.forms.OrderForm;
import mk.finki.ukim.emt.ordermenagement.service.forms.OrderItemForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Override
    public OrderId placeOrder(OrderForm orderForm) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Optional<Order> findById() {
        return Optional.empty();
    }

    @Override
    public void addItem(OrderId orderId, OrderItemForm orderItemForm) throws OrderIdNotExistException {

    }

    @Override
    public void deleteItem(OrderId orderId, OrderItemId orderItemId) throws OrderIdNotExistException, OrderItemIdNotExistingException {

    }
}
