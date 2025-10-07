package com.algohire.backend.controller;

import com.algohire.backend.dto.request.RefreshTokenRequstDto;
import com.algohire.backend.dto.response.JwtResponseDto;
import com.algohire.backend.exception.TokenNotFoundException;
import com.algohire.backend.model.RefreshToken;
import com.algohire.backend.service.CustomUserDetailsService;
import com.algohire.backend.service.JwtService;
import com.algohire.backend.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/v1")
@RequiredArgsConstructor
public class TokenControler {

    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @PostMapping("/refreshToken")
    public ResponseEntity<JwtResponseDto> refreshToken(RefreshTokenRequstDto requst){
        RefreshToken refreshToken=refreshTokenService.findByToken(requst.getToken())
                .orElseThrow(()->new TokenNotFoundException("Refresh token not found"));

        refreshTokenService.validateToken(refreshToken);
        UserDetails userDetails=customUserDetailsService.loadUserByUsername(refreshToken.getUsers().getEmail());
        String accessToken=jwtService.generateToken(userDetails);
        JwtResponseDto response=JwtResponseDto.builder()
                .refreshToken(refreshToken.getToken())
                .accessToken(accessToken)
                .build();

        return ResponseEntity.ok(response);
    }
}
