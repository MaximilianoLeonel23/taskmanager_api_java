package com.taskmanager.api.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("123")
    private String apiSecret;

    public String generateToken(String username) {
        String token = JWT.create()
                .withSubject(username)
                .sign(Algorithm.HMAC256(apiSecret));
        return token;
    }

    public DecodedJWT verifyToken(String token) {
        return JWT.require(Algorithm.HMAC256(apiSecret))
                .build()
                .verify(token);
    }
}
