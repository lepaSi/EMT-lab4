package mk.finki.ukim.emt.ordermenagement.domain.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.NonNull;
import mk.finki.ukim.emt.sharedkernel.domain.base.DomainObjectId;

@Embeddable
public class ProductId extends DomainObjectId {

    public ProductId() {
        super(ProductId.randomId(ProductId.class).getId());
    }

    public ProductId(@NonNull String uuid) {
        super(uuid);
    }


    public static ProductId of(String uuid) {
        ProductId p = new ProductId(uuid);
        return p;
    }

}
