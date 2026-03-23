package com.example.FoodDrink.controller;

import com.example.FoodDrink.dto.request.LoginRequest;
import com.example.FoodDrink.dto.request.RegistrationRequest;
import com.example.FoodDrink.dto.response.LoginResponse;
import com.example.FoodDrink.dto.response.Response;
import com.example.FoodDrink.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Response<?>> register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        return ResponseEntity.ok(authService.register(registrationRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<Response<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest,
                                                         HttpServletResponse response) {
        return ResponseEntity.ok(authService.login(loginRequest, response));
    }

    @PostMapping("/refresh")
    public ResponseEntity<Response<LoginResponse>> refreshToken(HttpServletRequest request) {
        return ResponseEntity.ok(authService.refreshToken(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<Response<?>> logout(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok(authService.logout(request, response));
    }
}