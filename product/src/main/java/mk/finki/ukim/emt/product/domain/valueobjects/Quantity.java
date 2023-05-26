package mk.finki.ukim.emt.product.domain.valueobjects;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import mk.finki.ukim.emt.sharedkernel.domain.base.ValueObject;

@Embeddable
@Getter
public class Quantity implements ValueObject {
    private final int kolicinaproiz;

    protected Quantity() {
        this.kolicinaproiz = 0;
    }
}
