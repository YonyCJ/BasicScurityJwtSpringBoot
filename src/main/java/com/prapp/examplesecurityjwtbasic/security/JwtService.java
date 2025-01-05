package com.prapp.examplesecurityjwtbasic.security;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtService {
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    private final TokenUtils tokenUtils;

    public JsonResponse generateUserToken(String email, String username, List<String> profiles) {
        return generateToken(email, username, profiles);
    }

    public JsonResponse generateToken(String subject, String name, List<String> profiles) {
        try {
            Map<String, Object> claims = new HashMap<>();
            claims.put("iss", "yonydev");
            claims.put("jti", UUID.randomUUID().toString());
            claims.put("sub", subject);
            claims.put("upn", subject);
            claims.put("preferred_username", name);
            claims.put("groups", profiles);
            claims.put("aud", "using-jwt");

            // Configuración del tiempo de expiración (60 minutos)
            long currentTimeInMillis = System.currentTimeMillis();
            claims.put("exp", (currentTimeInMillis + (60 * 60 * 1000)) / 1000);

            String token = tokenUtils.generateToken(claims);
            logger.info("TOKEN generated: {}", token);

            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setToken(token);
            return jsonResponse;
        } catch (Exception e) {
            logger.error("Error generating token", e);
            throw new RuntimeException(e);
        }
    }
}