package com.example.FoodDrink.service;

import com.example.FoodDrink.dto.request.LoginRequest;
import com.example.FoodDrink.dto.request.RegistrationRequest;
import com.example.FoodDrink.dto.response.LoginResponse;
import com.example.FoodDrink.dto.response.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    Response<?> register(RegistrationRequest registrationRequest);
    Response<LoginResponse> login(LoginRequest loginRequest, HttpServletResponse response);
    Response<LoginResponse> refreshToken(HttpServletRequest request);
    Response<?> logout(HttpServletRequest request, HttpServletResponse response);
}
