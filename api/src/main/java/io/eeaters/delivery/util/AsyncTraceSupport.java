package io.eeaters.delivery.util;

import org.slf4j.MDC;

public class AsyncTraceSupport {


    public static void execute(String traceId, String spanId, Runnable runnable) {
        try{
            MDC.put("traceId", traceId);
            MDC.put("spanId", spanId);
            runnable.run();
        }finally {
            MDC.clear();
        }
    }
}
