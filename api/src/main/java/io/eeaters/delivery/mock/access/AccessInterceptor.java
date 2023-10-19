package io.eeaters.delivery.mock.access;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

public class AccessInterceptor implements HandlerInterceptor {

    final UserProperty userProperty;


    public AccessInterceptor(UserProperty userProperty) {
        this.userProperty = userProperty;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        //放swagger
        if (requestURI.contains("swagger") || requestURI.contains("api-docs")) {
            return true;
        }
        //放登录请求
        if (requestURI.contains("login")) {
            return true;
        }

        String apikey = request.getHeader("token");
        if (apikey != null && Objects.equals(userProperty.getToken(), apikey)) {
            return true;
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return false;
    }

}
