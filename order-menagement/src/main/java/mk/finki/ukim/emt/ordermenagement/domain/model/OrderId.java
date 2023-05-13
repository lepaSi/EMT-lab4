package mk.finki.ukim.emt.ordermenagement.domain.model;

import mk.finki.ukim.emt.sharedkernel.domain.base.DomainObjectId;

public class OrderId extends DomainObjectId {
    private OrderId() {
        super(OrderId.randomId(OrderId.class).getId());
    }

}
