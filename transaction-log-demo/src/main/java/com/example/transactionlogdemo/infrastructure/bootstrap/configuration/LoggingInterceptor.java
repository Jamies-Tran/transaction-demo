package com.example.transactionlogdemo.infrastructure.bootstrap.configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoggingInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("X-Request-At", System.currentTimeMillis());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
