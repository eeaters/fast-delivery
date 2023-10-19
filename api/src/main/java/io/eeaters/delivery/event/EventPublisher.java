package io.eeaters.delivery.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

    @Autowired
    ApplicationEventPublisher eventPublisher;

    public void deliveryCreate(DeliveryCreateEvent deliveryCreateEvent) {
        eventPublisher.publishEvent(deliveryCreateEvent);
    }
}
