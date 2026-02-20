package com.metacoding.springv2._core.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.metacoding.springv2._core.util.JwtProvider;
import com.metacoding.springv2._core.util.JwtUtil;
import com.metacoding.springv2.user.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 인가 필터
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 제이슨 웹 토큰을 헤더에서 가져오는 것
        String jwt = JwtProvider.resolveToken(request);

        // Authentication(인증 객체)으로 변환해서 SecurityContextHolder에 담기
        if (jwt != null) {
            Authentication authentication = JwtProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

}