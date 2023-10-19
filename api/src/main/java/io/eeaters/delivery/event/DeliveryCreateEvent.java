package io.eeaters.delivery.event;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.MDC;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class DeliveryCreateEvent extends ApplicationEvent {

    private String traceId;

    public DeliveryCreateEvent(Long orderId) {
        super(orderId);
        this.traceId = MDC.get("traceId");
    }

    public Long getOrderId() {
        return (Long) getSource();
    }

}
