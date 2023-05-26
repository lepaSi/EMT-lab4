package mk.finki.ukim.emt.ordermenagement.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import mk.finki.ukim.emt.ordermenagement.domain.valueobjects.Product;
import mk.finki.ukim.emt.sharedkernel.domain.base.AbstractEntity;
import mk.finki.ukim.emt.sharedkernel.domain.financial.Currency;
import mk.finki.ukim.emt.sharedkernel.domain.financial.Money;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "naracka")
@Getter
public class Order extends AbstractEntity<OrderId> {
    private Instant nadatum;
    @Enumerated(value = EnumType.STRING)
    private OrderState sostojba;

    @Column(name = "naracka_currency")
    @Enumerated(value = EnumType.STRING)
    private Currency currency;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<OrderItem> orderItemList = new HashSet<>();

    public Order(){
        super(OrderId.randomId(OrderId.class));
    }


    public Order(Instant now, mk.finki.ukim.emt.sharedkernel.domain.financial.Currency currency) {
        super(OrderId.randomId(OrderId.class));
        this.nadatum = now;
        this.currency = currency;
    }


    public Money vkupnoc(){
        return orderItemList.stream().map(OrderItem::subtotal).reduce(new Money(currency,0),
        Money::add);
    }
    public OrderItem addItem(@NonNull Product product, int quantity){
        Objects.requireNonNull(product,"product must not be null");
        var item = new OrderItem(product.getId(),product.getCenaproiz(),quantity);
        orderItemList.add(item);
        return item;
    }
    public void removeItem(@NonNull OrderItemId orderItemId){
        Objects.requireNonNull(orderItemId,"Order Item must not be null");

        orderItemList.removeIf(v->v.getId().equals(orderItemId));
    }
}
