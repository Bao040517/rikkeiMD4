package com.project.demo.Controller;

import com.project.demo.DTO.LoginRequest;
import com.project.demo.DTO.RegisterRequest;
import com.project.demo.Service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Endpoint test - public, ai cũng truy cập được
     */
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Auth endpoint is public! Truy cập thành công không cần xác thực.");
    }

    /**
     * API Đăng ký tài khoản
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    /**
     * API Đăng nhập
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpSession session) {
        return authService.login(request);
    }
}
