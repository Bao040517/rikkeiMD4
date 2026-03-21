package com.medical.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

/**
 * Bài 6: Truy vết yêu cầu với MDC (Mapped Diagnostic Context)
 * Mỗi request được gắn một UUID duy nhất để theo dõi log xuyên suốt
 */
@Slf4j
@Component
@Order(1)
public class RequestIdFilter implements Filter {

    private static final String REQUEST_ID_KEY = "requestId";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        // Sinh mã UUID ngẫu nhiên cho mỗi request
        String requestId = UUID.randomUUID().toString().substring(0, 8);

        // Gắn requestId vào MDC
        MDC.put(REQUEST_ID_KEY, requestId);

        try {
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            log.info(">>> Request [{}] {} {}", requestId, httpRequest.getMethod(), httpRequest.getRequestURI());

            // Tiếp tục chuỗi filter
            filterChain.doFilter(servletRequest, servletResponse);

            log.info("<<< Request [{}] hoàn thành", requestId);
        } finally {
            // Xóa MDC sau khi request kết thúc để tránh memory leak
            MDC.clear();
        }
    }
}
