package mk.finki.ukim.emt.ordermenagement.domain.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import mk.finki.ukim.emt.ordermenagement.domain.valueobjects.ProductId;
import mk.finki.ukim.emt.sharedkernel.domain.base.AbstractEntity;
import mk.finki.ukim.emt.sharedkernel.domain.base.DomainObjectId;
import mk.finki.ukim.emt.sharedkernel.domain.financial.Money;

@Entity
@Table(name = "proizvonaracka")
@Getter
public class OrderItem extends AbstractEntity<OrderItemId> {
    private Money cenaitem;
    @Column(name = "kolicina",nullable = false)
    private int kolicinapn;

    @AttributeOverride(name = "id", column = @Column(name = "produkt_id",nullable = false))
    private ProductId productId;

    public OrderItem(@NonNull ProductId productId, Money cenaitem, int kolicinapn){
        super(DomainObjectId.randomId(OrderItemId.class));
        this.productId = productId;
        this.cenaitem = cenaitem;
        this.kolicinapn = kolicinapn;
    }

    public OrderItem() {

    }

    public Money subtotal(){
        return  cenaitem.multiply(kolicinapn);
    }
}
