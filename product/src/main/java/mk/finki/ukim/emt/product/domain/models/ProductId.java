package mk.finki.ukim.emt.product.domain.models;

import org.springframework.lang.NonNull;
import mk.finki.ukim.emt.sharedkernel.domain.base.DomainObjectId;

public class ProductId extends DomainObjectId {
    public ProductId(@NonNull String uuid) {
        super(uuid);
    }
    private ProductId(){
        super(ProductId.randomId(ProductId.class).getId());
    }

    public static ProductId of(String uuid) {
        ProductId p = new ProductId(uuid);
        return p;
    }

}
