package com.codigo.mslogin.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JWTService {

    String extractUsername(String token);
    String generateToken(UserDetails userDetails);
    boolean validateToken(String token, UserDetails userDetails);
    String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);
}
/* EJEMPLO DE MAP;
          "nombres", Object
          "apellidos", Object
          "email", Object
          "password", Object
*/