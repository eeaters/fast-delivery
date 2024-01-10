package io.eeaters.delivery.event;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.MDC;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class DeliveryCreateEvent extends AbstractTraceEvent {

    public DeliveryCreateEvent(Long orderId) {
        super(orderId);
    }

    public Long getOrderId() {
        return (Long) getSource();
    }

}
