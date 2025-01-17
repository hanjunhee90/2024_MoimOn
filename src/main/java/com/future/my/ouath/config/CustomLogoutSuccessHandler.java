package com.future.my.ouath.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//public class CustomLogoutSuccessHandler implements LogoutSuccessHandler{
public class CustomLogoutSuccessHandler{

//	@Override
//	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
//			throws IOException, ServletException {
//		 // 예시: 소셜 로그아웃을 위한 리다이렉트 처리
//        String provider = (String) request.getSession().getAttribute("provider"); // 소셜 제공자 정보 세션에서 가져오기
//
//        if ("google".equals(provider)) {
//            response.sendRedirect("https://accounts.google.com/Logout");  // 구글 로그아웃 URL로 리다이렉트
//        } else if ("kakao".equals(provider)) {
//            response.sendRedirect("https://kauth.kakao.com/oauth/logout");  // 카카오 로그아웃 URL
//        } else if ("naver".equals(provider)) {
//            response.sendRedirect("https://nid.naver.com/nidlogin.logout");  // 네이버 로그아웃 URL
//        } else {
//            response.sendRedirect("/");  // 기본 로그아웃 성공 URL로 리다이렉트
//        }
//		
//	}

}
