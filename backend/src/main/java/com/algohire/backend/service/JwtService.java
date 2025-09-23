package com.algohire.backend.service;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

public interface JwtService {
     public String generateToken(UserDetails userDetails);

     String generateToken(String email);

     public String createToken(Map<String, Object> claims, String subject);
     public Date extractExpiration(String token);
     public String extractUsername(String token);
     public <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
     public boolean validateToken(String token, UserDetails userDetails);



}
