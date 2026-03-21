package com.project.demo.Service;

import com.project.demo.DTO.LoginRequest;
import com.project.demo.DTO.RegisterRequest;
import com.project.demo.Entity.User;
import com.project.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> register(RegisterRequest request) {
        // Check trùng username
        if (userRepository.existsByUsername(request.getUsername())) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Username đã tồn tại!");
            return ResponseEntity.badRequest().body(error);
        }

        // Tạo user mới với password được mã hóa
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // QUAN TRỌNG: mã hóa password
        user.setRole("USER");
        user.setEnabled(true);

        userRepository.save(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Đăng ký thành công!");
        response.put("username", user.getUsername());
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> login(LoginRequest request) {
        // Tìm user theo username
        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());

        if (optionalUser.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "username or password incorrect");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }

        User user = optionalUser.get();

        // So sánh password mã hóa với password người dùng nhập
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "username or password incorrect");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }

        // Đăng nhập thành công
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Đăng nhập thành công!");
        response.put("username", user.getUsername());
        response.put("role", user.getRole());
        return ResponseEntity.ok(response);
    }
}
