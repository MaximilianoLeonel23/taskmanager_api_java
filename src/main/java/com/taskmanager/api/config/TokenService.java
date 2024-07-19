package com.taskmanager.api.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("123")
    private String apiSecret;

    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    public String generateToken(String username) {
        String token = JWT.create()
                .withSubject(username)
                .sign(Algorithm.HMAC256(apiSecret));
        logger.debug("Generated token: {}", token);
        return token;
    }

    public DecodedJWT verifyToken(String token) {
        logger.debug("Verifying token: {}", token);
        return JWT.require(Algorithm.HMAC256(apiSecret))
                .build()
                .verify(token);
    }
}
