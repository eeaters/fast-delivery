package io.eeaters.delivery.util;

import org.slf4j.MDC;

public class AsyncTraceSupport {


    public static void execute(String traceId, String taskName, Runnable runnable) {
        try{
            MDC.put("traceId", traceId);
            MDC.put("spanId", taskName);
            runnable.run();
        }finally {
            MDC.clear();
        }
    }
}
