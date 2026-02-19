package com.metacoding.springv2.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.metacoding.springv2._core.handler.ex.Exception401;
import com.metacoding.springv2._core.util.JwtUtil;
import com.metacoding.springv2.auth.AuthRequest.LoginDTO;
import com.metacoding.springv2.user.User;
import com.metacoding.springv2.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String 로그인(LoginDTO reqDTO) {
        // 1. UserRepository에서 username 확인
        User findUser = userRepository.findByUsername(reqDTO.getUsername())
                .orElseThrow(() -> new Exception401("유저네임을 찾을 수 없어요"));

        // 2. password를 hash해서 비교검증
        boolean isSamePassword = bCryptPasswordEncoder.matches(reqDTO.getPassword(), findUser.getPassword());
        if (!isSamePassword)
            throw new Exception401("비밀번호가 틀렸어요");

        return JwtUtil.create(findUser);
    }

}
