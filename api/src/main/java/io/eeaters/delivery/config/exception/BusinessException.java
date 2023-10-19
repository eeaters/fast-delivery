package io.eeaters.delivery.config.exception;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

public class BusinessException extends AbstractDeliveryException{

    public BusinessException(Integer code, String message) {
        super(code, message);
    }

    public BusinessException(String message) {
        this(NOT_ACCEPTABLE.value(), message);
    }
}
