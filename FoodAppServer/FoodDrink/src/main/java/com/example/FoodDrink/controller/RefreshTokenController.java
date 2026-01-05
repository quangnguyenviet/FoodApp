package com.example.FoodDrink.controller;

import com.example.FoodDrink.dto.response.Response;
import com.example.FoodDrink.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/refresh")
@RestController
@RequiredArgsConstructor
public class RefreshTokenController {
    private final RefreshTokenService refreshTokenService;
    @DeleteMapping
    public Response<?> deleteById(@RequestParam String id) {
        return refreshTokenService.deleteByToken(id);
    }

}
