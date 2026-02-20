package com.CircleUp.CircleUp.controller;

import com.CircleUp.CircleUp.dto.AuthResponse;
import com.CircleUp.CircleUp.dto.LoginRequest;
import com.CircleUp.CircleUp.model.User;
import com.CircleUp.CircleUp.repository.UserRepository;
import com.CircleUp.CircleUp.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    @PostMapping("/login")
     public ResponseEntity<?> login (@RequestBody LoginRequest request){
        User user=userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new RuntimeException("User not found"));
        if(!encoder.matches(request.getPassword(),user.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password!!");

        }

        String token= jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(new AuthResponse(token, user.getEmail(), 3600));
    }
}
