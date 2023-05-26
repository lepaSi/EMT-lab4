package mk.finki.ukim.emt.ordermenagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.finki.ukim.emt.sharedkernel.domain.base.ValueObject;
import mk.finki.ukim.emt.sharedkernel.domain.financial.Currency;
import mk.finki.ukim.emt.sharedkernel.domain.financial.Money;

@Getter
public class Product implements ValueObject {
    private final ProductId id;
    private final String name;
    private final Money cenaproiz;
    private final int sales;

    private Product(){
        this.id = ProductId.randomId(ProductId.class);
        this.name = "";
        this.cenaproiz = Money.valueOd(Currency.MKD,0);
        this.sales = 0;
    }

    @JsonCreator
    public Product(ProductId id, @JsonProperty("productName") String name, @JsonProperty("cenaproiz") Money cenaproiz, @JsonProperty("sales") int sales){
        this.id= id;
        this.name = name;
        this.cenaproiz = cenaproiz;
        this.sales = sales;
    }
}
