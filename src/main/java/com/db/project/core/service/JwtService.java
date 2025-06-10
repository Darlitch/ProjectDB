package com.db.project.core.service;

import com.db.project.api.dto.jwt.JwtAuthenticationDTO;
import java.time.Duration;
import javax.crypto.SecretKey;

public interface JwtService {

    JwtAuthenticationDTO generateAuthToken(String login);

    JwtAuthenticationDTO refreshBaseToken(String login, String refreshToken);

    String getLoginFromToken(String token);

    boolean validateToken(String token);

    String generateJwtToken(String login);


    String generateRefreshToken(String login);

    String generateToken(String login, Duration duration);

    SecretKey getSingInKey();
}
