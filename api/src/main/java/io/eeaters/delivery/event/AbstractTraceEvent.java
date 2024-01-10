package io.eeaters.delivery.event;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.MDC;
import org.springframework.context.ApplicationEvent;

/**
 * 用于可能异步消费时,便于消费时获取traceId
 */
@Getter
@Setter
public class AbstractTraceEvent extends ApplicationEvent {

    private String traceId;

    public AbstractTraceEvent(Object source) {
        super(source);
        this.traceId = MDC.get("traceId");
    }

}
