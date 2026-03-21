package com.project.demo.Service;

import com.project.demo.DTO.request.ActiveUserRequest;
import com.project.demo.DTO.request.LoginRequest;
import com.project.demo.DTO.request.RegisterRequest;
import com.project.demo.DTO.response.JwtResponse;
import com.project.demo.DTO.response.MessageResponse;
import com.project.demo.DTO.response.RegisterResponse;
import com.project.demo.Entity.User;
import com.project.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.demo.security.JwtProvider;
import com.project.demo.security.UserPrincipal;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private EmailService emailService;

    public ResponseEntity<?> register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Username đã tồn tại!"));
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email đã tồn tại!"));
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole("USER");
        user.setEnabled(false);

        String otp = String.format("%06d", new Random().nextInt(999999));
        user.setOtpCode(otp);
        user.setOtpExpiration(LocalDateTime.now().plusMinutes(5));

        userRepository.save(user);

        String subject = "Mã xác thực tài khoản";
        String body = "Mã OTP của bạn là: " + otp + "\nMã có hiệu lực trong 5 phút.";
        try {
            emailService.sendEmail(user.getEmail(), subject, body);
        } catch (Exception e) {
            System.err.println("Failed to send OTP email: " + e.getMessage());
        }

        return ResponseEntity.ok(new RegisterResponse("Đăng ký thành công! Vui lòng kiểm tra email để nhận mã OTP.", user.getUsername()));
    }

    public ResponseEntity<?> activeUser(ActiveUserRequest request) {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email không tồn tại."));
        }

        User user = optionalUser.get();
        if (user.isEnabled()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Tài khoản đã được kích hoạt."));
        }

        if (user.getOtpCode() == null || !user.getOtpCode().equals(request.getOtp())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Mã OTP không chính xác."));
        }

        if (user.getOtpExpiration() == null || user.getOtpExpiration().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Mã OTP đã hết hạn."));
        }

        user.setEnabled(true);
        user.setOtpCode(null);
        user.setOtpExpiration(null);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Kích hoạt thành công"));
    }

    public ResponseEntity<?> login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            String jwt = jwtProvider.generateToken(userPrincipal);

            JwtResponse jwtResponse = new JwtResponse(jwt, userPrincipal.getUsername(), userPrincipal.getUser().getRole());
            return ResponseEntity.ok(jwtResponse);
        } catch (DisabledException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MessageResponse("Tài khoản chưa được kích hoạt"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("username or password incorrect"));
        }
    }
}
