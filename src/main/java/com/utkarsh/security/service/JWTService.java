package com.utkarsh.security.service;

import com.utkarsh.security.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {
    private static final String ISSUER = "Utkarsh";
    private static final String SECRET_KEY = "mRk7u6K6VPs5xDqO2xg1rQ6jy3ZzZmKD0/VYb1F2phI=";
    private static final Integer TOKEN_VALIDITY = 60 * 10 * 1000;

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims) // Set custom claims
                .setSubject(user.getUsername()) // Subject (usually the username)
                .issuer(ISSUER)
                .setIssuedAt(new Date()) // Issue time
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY)) // Expiration time
                .signWith(generateKey()) // Sign the token with the secret key
                .compact(); // Generate the token
    }

    public String extractUsername(String jwtToken) {
        return extractClaims(jwtToken, Claims::getSubject);
    }

    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        final String username = extractUsername(jwt);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwt));
    }

    private SecretKey generateKey() {
        byte[] secretKeyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimResolver) {
        Claims claims = extractClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

}
