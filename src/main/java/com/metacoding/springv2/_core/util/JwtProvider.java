package com.metacoding.springv2._core.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.metacoding.springv2.user.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    // Bearer JWT -> JWT만 추출하기
    public String resolveToken(HttpServletRequest request) {
        return null;
    }

    // 토큰을 검증하고 Authentication 반환
    public Authentication getAuthentication(String token) {
        return null;
    }

    // 토큰이 유효한지 단순 체크
    public boolean validateToken(String token) {
        return false;
    }
}