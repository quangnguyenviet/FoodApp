package com.example.FoodDrink.security;

import com.example.FoodDrink.exceptions.CustomAccessDenialHandler;
import com.example.FoodDrink.exceptions.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityFilter {

    private final AuthFilter authFilter;
    private final CustomAccessDenialHandler customAccessDenialHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomOidcUserService customOidcUserService;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .exceptionHandling(ex ->
                        ex.accessDeniedHandler(customAccessDenialHandler).authenticationEntryPoint(customAuthenticationEntryPoint))
                .authorizeHttpRequests(req ->
                        req.requestMatchers("/api/auth/**", "/api/categories/**", "/api/menu/**",
                                        "/api/reviews/**",
                                        "/oauth2/**"
                                ).permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(mag -> mag.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                // Configure OAuth2 login
                .oauth2Login(oauth2 -> oauth2
                                .userInfoEndpoint(userInfo -> userInfo
                                        // Use the custom OIDC user service to process user info
                                        .oidcUserService(customOidcUserService)
                                ).successHandler(oAuth2LoginSuccessHandler)
                        // Redirect to the frontend after successful login (session-based)
//                        .defaultSuccessUrl("http://localhost:5173", true)
                );

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
















