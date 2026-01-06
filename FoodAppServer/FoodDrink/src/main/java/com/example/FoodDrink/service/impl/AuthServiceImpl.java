package com.example.FoodDrink.service.impl;


import com.example.FoodDrink.dto.request.LoginRequest;
import com.example.FoodDrink.dto.request.RegistrationRequest;
import com.example.FoodDrink.dto.response.LoginResponse;
import com.example.FoodDrink.dto.response.Response;
import com.example.FoodDrink.entity.RefreshToken;
import com.example.FoodDrink.entity.Role;
import com.example.FoodDrink.entity.User;
import com.example.FoodDrink.exceptions.BadRequestException;
import com.example.FoodDrink.exceptions.NotFoundException;
import com.example.FoodDrink.repository.RefreshTokenRepository;
import com.example.FoodDrink.repository.RoleRepository;
import com.example.FoodDrink.repository.UserRepository;
import com.example.FoodDrink.security.JwtUtils;
import com.example.FoodDrink.service.AuthService;
import com.example.FoodDrink.service.RefreshTokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final RoleRepository roleRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenService refreshTokenService;

    @Value("${isProduction}")
    private boolean isProduction;

    @Value("${cookie.refresh.token.max.age}")
    private int refreshTokenCookieMaxAge;


    @Override
    public Response<?> register(RegistrationRequest registrationRequest) {

        log.info("INSIDE register()");

        // Validate the registration request
        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        // collect all roles from the request
        List<Role> userRoles;
        if (registrationRequest.getRoles() != null && !registrationRequest.getRoles().isEmpty()) {
            userRoles = registrationRequest.getRoles().stream()
                    .map(roleName -> roleRepository.findByName(roleName.toUpperCase())
                            .orElseThrow(() -> new NotFoundException("Role '" + roleName + "' Not Found")))
                    .toList();
        } else {
            // If no roles provided, default to CUSTOMER
            Role defaultRole = roleRepository.findByName("CUSTOMER")
                    .orElseThrow(() -> new NotFoundException("Default CUSTOMER role Not Found"));
            userRoles = List.of(defaultRole);
        }
        // Build the user object
        User userToSave = User.builder()
                .name(registrationRequest.getName())
                .email(registrationRequest.getEmail())
                .phoneNumber(registrationRequest.getPhoneNumber())
                .address(registrationRequest.getAddress())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .roles(userRoles)
                .active(true)
                .createdAt(LocalDateTime.now())
                .build();

        // Save the user
        userRepository.save(userToSave);

        log.info("User registered successfully");

        return Response.builder()
                .statusCode(HttpStatus.OK.value())
                .message("User Registered Successfully")
                .build();


    }

    @Override
    public Response<LoginResponse> login(LoginRequest loginRequest, HttpServletResponse response) {

        log.info("INSIDE login()");

        // Find the user by email
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid Email"));

        if (!user.getActive()) {
            throw new NotFoundException("Account not active, Please contact customer support");
        }

        // Verify the password
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid Password");
        }

        // Generate a token
        String token = jwtUtils.generateToken(user.getEmail());
        // Create a refresh token
        RefreshToken refreshToken = refreshTokenService.create(user.getEmail());

        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken.getToken())
                .httpOnly(true)
                .secure(true)              // REQUIRED when SameSite=None
                .sameSite("None")          // REQUIRED for cross-origin
                .path("/api/auth")
                .maxAge(refreshTokenCookieMaxAge)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());

        // Extract role names as a list
        List<String> roleNames = user.getRoles().stream()
                .map(Role::getName)
                .toList();


        LoginResponse loginResponse = new LoginResponse();

        loginResponse.setToken(token);
        loginResponse.setRoles(roleNames);

        return Response.<LoginResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Login Successful")
                .data(loginResponse)
                .build();
    }

//    @Override
//    public Response<LoginResponse> refreshToken(HttpServletRequest request) {
//        // 1. Get the cookie from the request
//        String token = Arrays.stream(request.getCookies())
//                .filter(cookie -> "refreshToken".equals(cookie.getName()))
//                .map(Cookie::getValue)
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Refresh token missing"));
//
//        // 2. Validate and find the user associated with this token
//        return refreshTokenRepository.findByToken(token)
//                .map(refreshTokenService::verifyExpiration) // Check if expired
//                .map(RefreshToken::getUser)
//                .map(user -> {
//                    // 3. Generate a new JWT
//                    String accessToken = jwtUtils.generateToken(user.getEmail());
//                    List<String> roleNames = user.getRoles().stream()
//                            .map(Role::getName)
//                            .toList();
//                    LoginResponse loginResponse = new LoginResponse();
//                    loginResponse.setToken(accessToken);
//                    loginResponse.setRoles(roleNames);
//                    return Response.<LoginResponse>builder()
//                            .statusCode(HttpStatus.OK.value())
//                            .message("Token refreshed successfully")
//                            .data(loginResponse)
//                            .build();
//                })
//                .orElseThrow(() -> new RuntimeException("Refresh token is not in database!"));
//    }
    @Override
    public Response<LoginResponse> refreshToken(HttpServletRequest request) {
        // 1. Get the refresh token from the request cookies
        String refreshToken = getRefreshTokenFromCookies(request);

        // 2. Find the refresh token in the database
        RefreshToken tokenEntity = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Refresh token is not in database!"));

        // 3. Verify that the token has not expired
        refreshTokenService.verifyExpiration(tokenEntity);

        // 4. Get the user associated with this refresh token
        User user = tokenEntity.getUser();

        // 5. Generate a new JWT access token
        String newAccessToken = jwtUtils.generateToken(user.getEmail());

        // 6. Get the user's roles
        List<String> roleNames = user.getRoles().stream()
                .map(Role::getName)
                .toList();

        // 7. Create the login response
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(newAccessToken);
        loginResponse.setRoles(roleNames);

        // 8. Return the response
        return Response.<LoginResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Token refreshed successfully")
                .data(loginResponse)
                .build();
    }

    @Transactional
    @Override
    public Response<?> logout(HttpServletRequest request, HttpServletResponse response) {
        log.info("INSIDE logout()");
        String refreshToken = getRefreshTokenFromCookies(request);

        if (refreshToken != null) {
            RefreshToken tokenEntity = refreshTokenRepository.findByToken(refreshToken)
                    .orElseThrow(() -> new RuntimeException("Refresh token is not in database!"));

            // set the refresh token in user to null
//            User user = tokenEntity.getUser();
//            if (user != null) {
//                user.setRefreshToken(null);
//            }
            refreshTokenRepository.delete(tokenEntity);

            // delete cookie by setting max age to 0
            Cookie cookie = new Cookie("refreshToken", null);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/api/auth");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

        return Response.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Logout successful")
                .build();
    }

    /**
     * Helper method to extract the refresh token from cookies
     */
    private String getRefreshTokenFromCookies(HttpServletRequest request) {
        if (request.getCookies() == null) {
            throw new RuntimeException("No cookies found in request");
        }
        for (Cookie cookie : request.getCookies()) {
            if ("refreshToken".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;

//        return Arrays.stream(request.getCookies())
//                .filter(cookie -> "refreshToken".equals(cookie.getName()))
//                .map(Cookie::getValue)
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Refresh token missing"));
    }

}