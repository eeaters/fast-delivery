package io.eeaters.delivery.config.exception;

import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;

public class AssertException extends AbstractDeliveryException {

    public AssertException(String message) {
        super(PRECONDITION_FAILED.value(), message);
    }
}
