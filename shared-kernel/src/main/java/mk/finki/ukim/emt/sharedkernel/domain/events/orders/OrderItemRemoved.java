package mk.finki.ukim.emt.sharedkernel.domain.events.orders;

import lombok.Getter;
import mk.finki.ukim.emt.sharedkernel.domain.config.TopicHolder;
import mk.finki.ukim.emt.sharedkernel.domain.events.DomainEvent;

@Getter
public class OrderItemRemoved extends DomainEvent {

    private  String productId;
    private int kolicinapn;
    public OrderItemRemoved(String topic) {
        super(TopicHolder.TOPIC_ORDER_ITEM_REMOVED);
    }
    public OrderItemRemoved(String topic, String productId, int kolicinapn){
        super(TopicHolder.TOPIC_ORDER_ITEM_REMOVED);
        this.productId = productId;
        this.kolicinapn = kolicinapn;
    }

}
