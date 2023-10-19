package io.eeaters.delivery.config.exception;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * global exception handle
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    private static final String ERROR_HEADER = "error-message";

    /**
     * param exception handle
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleException(MethodArgumentNotValidException exception) {
        log.warn("param error, exception info : {}", ExceptionUtils.getStackTrace(exception));
        StringBuilder sb = new StringBuilder();
        exception.getBindingResult()
                .getAllErrors()
                .iterator()
                .forEachRemaining(objectError -> {
                    if (FieldError.class.isAssignableFrom(objectError.getClass())) {
                        FieldError fieldError = (FieldError) objectError;
                        sb.append(fieldError.getField())
                                .append(" : ")
                                .append(objectError.getDefaultMessage())
                                .append(" | ");
                    } else {
                        sb.append(objectError.getDefaultMessage())
                                .append(" | ");
                    }
                });
        String message = sb.substring(0, sb.length() - 1);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .header(ERROR_HEADER, message)
                .build();
    }

    /**
     * business exception handle
     * @param exception
     * @return
     */
    @ExceptionHandler(AbstractDeliveryException.class)
    public ResponseEntity<String> handleException(AbstractDeliveryException exception) {
        log.info("business error , exception info : {}", ExceptionUtils.getStackTrace(exception));
        return ResponseEntity.status(exception.getCode())
                .header(ERROR_HEADER, exception.getMessage())
                .build();
    }

    /**
     * other exception handle
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        log.error("system error , exception info : {}", ExceptionUtils.getStackTrace(exception));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header(ERROR_HEADER, exception.getMessage())
                .build();
    }
}
