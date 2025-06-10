package com.db.project.core.service.impl;

import com.db.project.api.dto.jwt.JwtAuthenticationDTO;
import com.db.project.core.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.token-lifetime}")
    private Duration jwtTokenLifetime;

    @Value("${jwt.refresh-lifetime}")
    private Duration refreshTokenLifetime;

    @Override
    public JwtAuthenticationDTO generateAuthToken(String login) {
        return JwtAuthenticationDTO.builder()
                .token(generateJwtToken(login))
                .refreshToken(generateRefreshToken(login))
                .build();
    }

    @Override
    public JwtAuthenticationDTO refreshBaseToken(String login, String refreshToken) {
        return JwtAuthenticationDTO.builder()
                .token(generateJwtToken(login))
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public String getLoginFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSingInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSingInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return true;
        } catch (Exception e) {
            log.info("{} -> {}", e.getClass(), e.getMessage());
            return false;
        }
    }

    @Override
    public String generateJwtToken(String login) {
        return generateToken(login, jwtTokenLifetime);
    }

    @Override
    public String generateRefreshToken(String login) {
        return generateToken(login, refreshTokenLifetime);
    }

    @Override
    public String generateToken(String login, Duration duration) {
        Date date = Date.from(LocalDateTime.now().plus(duration).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .subject(login)
                .expiration(date)
                .signWith(getSingInKey())
                .compact();
    }

    @Override
    public SecretKey getSingInKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }
}
