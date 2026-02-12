package com.metacoding.springv2.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.metacoding.springv2._core.util.Resp;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    // ssar, cos -> 실패
    // love -> 성공
    // POST : http://localhost:8080/join
    // BODY(json) -> { username, password, email }
    @PostMapping("/join")
    public ResponseEntity<?> join(@Valid @RequestBody AuthRequest.JoinDTO requestDTO, Errors errors) {
        var responseDTO = authService.회원가입(requestDTO);
        return Resp.ok(responseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest.LoginDTO requestDTO, Errors errors) {
        var responseDTO = authService.로그인(requestDTO);
        return Resp.ok(responseDTO);
    }

    @GetMapping("/check-username")
    public ResponseEntity<?> getUsername(@RequestParam String username) {
        var responseDTO = authService.유저네임중복체크(username);
        return Resp.ok(responseDTO);
    }
}
