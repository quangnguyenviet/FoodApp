package com.example.FoodDrink.service.impl;

import com.example.FoodDrink.entity.RefreshToken;
import com.example.FoodDrink.entity.User;
import com.example.FoodDrink.exceptions.NotFoundException;
import com.example.FoodDrink.repository.RefreshTokenRepository;
import com.example.FoodDrink.repository.UserRepository;
import com.example.FoodDrink.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Value("${jwt.refresh.token.expiration.time}" )
    private Long refreshTokenDurationSec;

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    @Override
    public RefreshToken create(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("User with email " + email + " not found")
        );
        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .user(user)
                .expiryDate(Instant.now().plusSeconds(refreshTokenDurationSec)) //
                .build();
        RefreshToken savedToken = refreshTokenRepository.save(refreshToken);
        return savedToken;
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken refreshToken) {
        if (refreshToken.getExpiryDate().isBefore(Instant.now())) {
            throw new RuntimeException("Refresh token has expired");
        }
        return refreshToken;
    }
}
