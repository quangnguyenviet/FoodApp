package com.example.FoodDrink.service;

import com.example.FoodDrink.entity.RefreshToken;

public interface RefreshTokenService {
    RefreshToken create(String email);
    RefreshToken verifyExpiration(RefreshToken refreshToken);
}
