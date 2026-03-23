package com.example.FoodDrink.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
@Slf4j
public class JwtUtils {

    private SecretKey key;

    @Value("${secreteJwtString}")
    private String secreteJwtString;

    @Value("${jwt.expiration.time}")
    private Long EXPIRATION_TIME;

    @PostConstruct
    private void init() {
        byte[] keyByte = secreteJwtString.getBytes(StandardCharsets.UTF_8);
        this.key = new SecretKeySpec(keyByte, "HmacSHA256");
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

//    public String getUsernameFromToken(String token) {
//        return extractClaims(token, Claims::getSubject);
//    }
//
//    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
//        return claimsTFunction.apply(Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload());
//    }

    public String getUsernameFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return claims.getSubject();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(key)        // verify token using secret key
                .build()
                .parseSignedClaims(token)
                .getPayload();          // return JWT claims (payload)
    }


    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
//        return extractClaims(token, Claims::getExpiration).before(new Date());
        return getAllClaimsFromToken(token).getExpiration().before(new Date());
    }

}











