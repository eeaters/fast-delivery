package io.eeaters.delivery.mock.access;

import io.eeaters.delivery.config.exception.AbstractDeliveryException;
import org.springframework.http.HttpStatus;

public class LoginException extends AbstractDeliveryException {


    public LoginException(String message) {
        super(HttpStatus.UNAUTHORIZED.value(), message);
    }
}
