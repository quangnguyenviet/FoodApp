package com.example.FoodDrink.security;

import com.example.FoodDrink.entity.RefreshToken;
import com.example.FoodDrink.service.RefreshTokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;
    @Value("${cookie.refresh.token.max.age}")
    private int refreshTokenCookieMaxAge;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();

        // create refresh token cookie
        RefreshToken refreshToken = refreshTokenService.create(oidcUser.getEmail());
        Cookie cookie = new Cookie("refreshToken", refreshToken.getToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/api/auth");
        cookie.setMaxAge(refreshTokenCookieMaxAge);
        response.addCookie(cookie);

        // Redirect to frontend with token as query parameter
        String redirectUrl = "http://localhost:3000/oauth2/callback";
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
