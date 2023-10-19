package io.eeaters.delivery.config.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.eeaters.delivery.util.NonExSupplier;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import java.util.Enumeration;

/**
 * @see AbstractRequestLoggingFilter
 */
@Service
@Slf4j
public class ProjectLogService {

    @Autowired
    ObjectMapper objectMapper;

    public void logRequest(HttpServletRequest request, Object body) {
        StringBuilder msg = new StringBuilder();
        msg.append(AbstractRequestLoggingFilter.DEFAULT_BEFORE_MESSAGE_PREFIX)
                .append(request.getMethod())
                .append("  ")
                .append(request.getRequestURI());

        String queryString = request.getQueryString();
        if (queryString != null) {
            msg.append('?').append(queryString);
        }
        String client = request.getRemoteAddr();
        if (StringUtils.hasLength(client)) {
            msg.append(", client=").append(client);
        }
        HttpSession session = request.getSession(false);
        if (session != null) {
            msg.append(", session=").append(session.getId());
        }
        String user = request.getRemoteUser();
        if (user != null) {
            msg.append(", user=").append(user);
        }
        HttpHeaders headers = new ServletServerHttpRequest(request).getHeaders();
        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String header = names.nextElement();
            headers.set(header, "masked");
        }
        msg.append(", headers=").append(headers);
        if (body != null) {
            msg.append(", body=" + NonExSupplier.exec(() -> objectMapper.writeValueAsString(body)));
        }
        msg.append(AbstractRequestLoggingFilter.DEFAULT_BEFORE_MESSAGE_SUFFIX);
        log.info(msg.toString());

    }

    public void logResponse(HttpServletRequest request, HttpServletResponse response, Object body) {
        StringBuilder msg = new StringBuilder();
        msg.append(AbstractRequestLoggingFilter.DEFAULT_AFTER_MESSAGE_PREFIX)
                .append(request.getMethod())
                .append("  ")
                .append(request.getRequestURI());
        String queryString = request.getQueryString();
        if (queryString != null) {
            msg.append("?").append(queryString);
        }
        if (body != null) {
            msg.append(", responseBody=" + NonExSupplier.exec(() -> objectMapper.writeValueAsString(body)));
        }
        msg.append(AbstractRequestLoggingFilter.DEFAULT_AFTER_MESSAGE_SUFFIX);
        log.info(msg.toString());
    }

}
