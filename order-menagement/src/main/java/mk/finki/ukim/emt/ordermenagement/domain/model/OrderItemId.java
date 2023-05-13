package mk.finki.ukim.emt.ordermenagement.domain.model;

import lombok.NonNull;
import mk.finki.ukim.emt.sharedkernel.domain.base.DomainObjectId;

public class OrderItemId extends DomainObjectId {
    public OrderItemId(String uuid) {
        super(uuid);
    }
    private OrderItemId(){
        super(OrderItemId.randomId(OrderItemId.class).getId());
    }

}
