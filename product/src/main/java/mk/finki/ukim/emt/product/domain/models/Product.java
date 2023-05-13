package mk.finki.ukim.emt.product.domain.models;

import jakarta.persistence.*;
import mk.finki.ukim.emt.product.domain.valueobjects.Quantity;
import mk.finki.ukim.emt.sharedkernel.domain.base.AbstractEntity;
import mk.finki.ukim.emt.sharedkernel.domain.financial.Money;

@Entity
@Table(name = "proizvod")
public class Product extends AbstractEntity<ProductId> {
    @Column(name = "imenaproiz")
    private String productName;
    private Quantity kolicinaproiz;
    private String sostav;

    @AttributeOverrides({
            @AttributeOverride(name = "kolicinaproiz",column =
            @Column(name = "cenaproiz_kolicinaproiz")),
            @AttributeOverride(name = "currency", column =
            @Column(name = "cenaproiz_currency"))
    })
    private Money cenaproiz;
}
