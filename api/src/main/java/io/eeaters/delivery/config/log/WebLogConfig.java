package io.eeaters.delivery.config.log;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Configuration
public class WebLogConfig {

    @Autowired
    private ProjectLogService logService;

    @Bean
    public HandlerInterceptor logInterceptor() {
        return new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())
                        && request.getMethod().equals(HttpMethod.GET.name())) {
                    logService.logRequest(request, null);
                }
                return true;
            }
        };

    }



}
