package com.algohire.backend.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.algohire.backend.service.JwtService;

import io.jsonwebtoken.Claims;

@Component
public class JwtServiceImpal implements JwtService{


    @Value("${jwt.secret}")
    private  String SECRET;

    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String,Object> claims=new HashMap<>();
        return createToken(claims,userDetails.getUsername());
         
    }

    @Override
    public String generateToken(String email) {
        Map<String,Object> claims=new HashMap<>();
        return createToken(claims,email);

    }


    @Override
    public String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256,SECRET)
                .compact();
    }
    @Override
    public Date extractExpiration(String token) {

        return extractClaim(token,Claims::getExpiration);
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
         
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
         final Claims claims=Jwts.parser()
                 .setSigningKey(SECRET)
                 .parseClaimsJws(token)
                 .getBody();
         return claimsResolver.apply(claims);
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
         final String username=extractUsername(token);
         return (username.equals(userDetails.getUsername()));
    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());

    }
    
}
