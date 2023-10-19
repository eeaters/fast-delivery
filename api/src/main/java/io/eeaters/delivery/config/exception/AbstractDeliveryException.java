package io.eeaters.delivery.config.exception;

import lombok.Getter;

@Getter
public abstract class AbstractDeliveryException extends RuntimeException {

    private Integer code;

    public AbstractDeliveryException(Integer code, String message) {
        super(message);
        this.code = code;
    }


}
