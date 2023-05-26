package mk.finki.ukim.emt.sharedkernel.domain.events.orders;

import lombok.Getter;
import mk.finki.ukim.emt.sharedkernel.domain.config.TopicHolder;
import mk.finki.ukim.emt.sharedkernel.domain.events.DomainEvent;

@Getter
public class OrderItemCreated extends DomainEvent {

    private  String productId;
    private int kolicinapn;
    public OrderItemCreated(String topic) {
        super(TopicHolder.TOPIC_ORDER_ITEM_CREATED);
    }
    public OrderItemCreated(String productId, int kolicinapn){
        super(TopicHolder.TOPIC_ORDER_ITEM_CREATED);
        this.productId = productId;
        this.kolicinapn = kolicinapn;
    }
}
