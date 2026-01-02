package com.example.FoodDrink.service;

import com.example.FoodDrink.dto.request.LoginRequest;
import com.example.FoodDrink.dto.request.RegistrationRequest;
import com.example.FoodDrink.dto.response.LoginResponse;
import com.example.FoodDrink.dto.response.Response;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    Response<?> register(RegistrationRequest registrationRequest);
    Response<LoginResponse> login(LoginRequest loginRequest);
    Response<LoginResponse> refreshToken(HttpServletRequest request);
}
