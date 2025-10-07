package com.algohire.backend.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.algohire.backend.service.JwtService;

import io.jsonwebtoken.Claims;

@Component
public class JwtServiceImpal implements JwtService{


    @Value("${jwt.secret}")
    private  String SECRET;


    private Key key;

    @PostConstruct
    public void init(){
        this.key= Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

//    @Override
//    public String generateToken(String emial) {
//        Map<String,Object> claims=new HashMap<>();
//        return createToken(claims,emial);
//
//    }

    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String,Object> claims=new HashMap<>();
        List<String> roles=userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                        .toList();
        claims.put("roles",roles);
        return createToken(claims,userDetails.getUsername());

    }


    @Override
    public String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(key,SignatureAlgorithm.HS256)
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

    public List<String> extractRoles(String token){
        return extractClaim(token,claims -> (List<String>) claims.get("roles"));
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
         final Claims claims=Jwts.parser()
                 .setSigningKey(key)
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
