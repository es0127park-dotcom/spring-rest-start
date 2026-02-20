package com.metacoding.springv2.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    // cos만 접속 가능! (인증이 아닌 ADMIN 권한이 필요함)
    @GetMapping("/admin/test")
    public String test() {
        return "<h1>관리자페이지</h>";
    }
}
