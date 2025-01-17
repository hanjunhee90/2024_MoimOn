package com.future.my.ouath.config;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

// 필터 순서 지정
//@Component
//@Order(1)
//public class RequestLoggingFilter extends GenericFilterBean {
public class RequestLoggingFilter{

//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        
//        //System.out.println("Request URI: " + req.getRequestURI());
//        //System.out.println("Request Method: " + req.getMethod());
//
//        // 다음 필터로 전달
//        chain.doFilter(request, response);
//    }
}
