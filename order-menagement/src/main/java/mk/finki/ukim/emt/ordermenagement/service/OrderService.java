package mk.finki.ukim.emt.ordermenagement.service;

import mk.finki.ukim.emt.ordermenagement.domain.exceptions.OrderIdNotExistException;
import mk.finki.ukim.emt.ordermenagement.domain.exceptions.OrderItemIdNotExistingException;
import mk.finki.ukim.emt.ordermenagement.domain.model.Order;
import mk.finki.ukim.emt.ordermenagement.domain.model.OrderId;
import mk.finki.ukim.emt.ordermenagement.domain.model.OrderItemId;
import mk.finki.ukim.emt.ordermenagement.service.forms.OrderForm;
import mk.finki.ukim.emt.ordermenagement.service.forms.OrderItemForm;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderId placeOrder(OrderForm orderForm);
    List<Order> findAll();
    Optional<Order> findById(OrderId id);

    void addItem(OrderId orderId, OrderItemForm orderItemForm)
            throws OrderIdNotExistException;

    void deleteItem(OrderId orderId, OrderItemId orderItemId)
        throws OrderIdNotExistException, OrderItemIdNotExistingException;

}
