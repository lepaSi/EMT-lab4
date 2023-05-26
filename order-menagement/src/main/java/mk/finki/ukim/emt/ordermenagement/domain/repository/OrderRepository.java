package mk.finki.ukim.emt.ordermenagement.domain.repository;

import mk.finki.ukim.emt.ordermenagement.domain.model.Order;
import mk.finki.ukim.emt.ordermenagement.domain.model.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, OrderId> {

}
