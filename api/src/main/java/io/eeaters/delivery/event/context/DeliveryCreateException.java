package io.eeaters.delivery.event.context;

import io.eeaters.delivery.config.exception.AbstractDeliveryException;

public class DeliveryCreateException extends AbstractDeliveryException {

    public DeliveryCreateException(String message) {
        super(501, message);
    }
}
