package com.metacoding.springv2.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metacoding.springv2._core.handler.ex.Exception401;
import com.metacoding.springv2._core.handler.ex.Exception404;
import com.metacoding.springv2._core.util.JwtUtil;
import com.metacoding.springv2.user.User;
import com.metacoding.springv2.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true) // 변경감지 생략
@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder; // 비밀번호 암호화
    
    @Transactional
    public AuthResponse.DTO 회원가입(AuthRequest.JoinDTO requestDTO) {
        if (userRepository.findByUsername(requestDTO.username()).isPresent())
            throw new Exception401("이미 존재하는 유저네임입니다");
        String encPassword = bCryptPasswordEncoder.encode(requestDTO.password());
        User savedUser = userRepository.save(requestDTO.toEntity(encPassword));
        return new AuthResponse.DTO(savedUser);
    }

    public String 로그인(AuthRequest.LoginDTO requestDTO) {
        User findUser = userRepository.findByUsername(requestDTO.username())
                .orElseThrow(() -> new Exception404("유저네임 혹은 비밀번호가 일치하지 않습니다"));
        if (!bCryptPasswordEncoder.matches(requestDTO.password(), findUser.getPassword()))
            throw new Exception401("유저네임 혹은 비밀번호가 일치하지 않습니다");
        return JwtUtil.create(findUser);
    }

    public Map<String, Object> 유저네임중복체크(String username) {
        Map<String, Object> mapDTO = new HashMap<>();
        if (userRepository.findByUsername(username).isPresent()) {
            mapDTO.put("available", false);
        } else {
            mapDTO.put("available", true);
        }
        return mapDTO;
    }
}
