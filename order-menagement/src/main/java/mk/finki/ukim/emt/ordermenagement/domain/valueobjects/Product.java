package mk.finki.ukim.emt.ordermenagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import mk.finki.ukim.emt.sharedkernel.domain.base.ValueObject;
import mk.finki.ukim.emt.sharedkernel.domain.financial.Currency;
import mk.finki.ukim.emt.sharedkernel.domain.financial.Money;

@Getter
public class Product implements ValueObject {
    private final ProduktId id;
    private final String name;
    private final Money cenaproiz;

    private final String sostav;

    private Product(){
        this.id = ProduktId.randomId(ProduktId.class);
        this.name = "";
        this.cenaproiz = Money.valueOd(Currency.MKD,0);
        this.sostav = "";
    }

    @JsonCreator
    public Product(ProduktId id, String name, Money cenaproiz, String sostav){
        this.id= id;
        this.name = name;
        this.cenaproiz = cenaproiz;
        this.sostav = sostav;
    }
}
