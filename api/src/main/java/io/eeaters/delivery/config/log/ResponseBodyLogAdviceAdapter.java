package io.eeaters.delivery.config.log;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@AllArgsConstructor
@ControllerAdvice
public class ResponseBodyLogAdviceAdapter implements ResponseBodyAdvice {

    private final ProjectLogService logService;


    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest servletRequest,
                                  ServerHttpResponse serverHttpResponse) {
        if (servletRequest instanceof ServletServerHttpRequest && serverHttpResponse instanceof ServletServerHttpResponse) {
            HttpServletRequest request = ((ServletServerHttpRequest) servletRequest).getServletRequest();
            HttpServletResponse response = ((ServletServerHttpResponse) serverHttpResponse).getServletResponse();
            logService.logResponse(request, response, body);
        }
        return body;
    }
}
