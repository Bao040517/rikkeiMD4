package com.project.demo.Controller;

import com.project.demo.DTO.request.ActiveUserRequest;
import com.project.demo.DTO.request.LoginRequest;
import com.project.demo.DTO.request.RegisterRequest;
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

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Auth endpoint is public! Truy cập thành công không cần xác thực.");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/active-user")
    public ResponseEntity<?> activeUser(@RequestBody ActiveUserRequest request) {
        return authService.activeUser(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpSession session) {
        return authService.login(request);
    }
}
