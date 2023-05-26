package mk.finki.ukim.emt.product.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import mk.finki.ukim.emt.product.domain.valueobjects.Quantity;
import mk.finki.ukim.emt.sharedkernel.domain.base.AbstractEntity;
import mk.finki.ukim.emt.sharedkernel.domain.financial.Money;

@Entity
@Table(name = "proizvod")
@Getter
public class Product extends AbstractEntity<ProductId> {
    @Column(name = "imenaproiz")
    private String productName;
   // private Quantity kolicinaproiz;
   // private String sostav;

    private int sales = 0;

    @AttributeOverrides({
            @AttributeOverride(name = "amount",column = @Column(name = "cenaproiz_kolicinaproiz")),
            @AttributeOverride(name = "currency", column = @Column(name = "cenaproiz_currency"))
    })
    private Money cenaproiz;


    private Product() {
        super(ProductId.randomId(ProductId.class));

    }


    public static Product build(String productName, Money cenaproiz, int sales){
        Product p = new Product();
        p.productName = productName;
        p.cenaproiz = cenaproiz;
        p.sales = sales;
        return p;
    }
    public void addSales(int kolicinaproiz){
        this.sales = this.sales - kolicinaproiz;
    }

    public void removeSales(int kolicinaproiz){
        this.sales -= kolicinaproiz;
    }

}
