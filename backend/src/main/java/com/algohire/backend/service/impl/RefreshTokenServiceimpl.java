package com.algohire.backend.service.impl;

import com.algohire.backend.model.RefreshToken;
import com.algohire.backend.model.Users;
import com.algohire.backend.repository.RefreshTokenRepository;
import com.algohire.backend.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class RefreshTokenServiceimpl implements RefreshTokenService {

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken createRefreshToken(Users users) {
        RefreshToken token=RefreshToken.builder()
                .users(users)
                .token(UUID.randomUUID().toString())
                .expiryDate(LocalDateTime.now().plusDays(20))
                .build();
        return refreshTokenRepository.save(token);
    }

    @Override
    public boolean isValid(String token) {
        return false;
    }

    @Override
    public void deleteUsers(Users users) {

    }

    @Override
    public RefreshToken valideToken(RefreshToken token) {
        if(token.getExpiryDate().isBefore(LocalDateTime.now())){
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken()+"Refresh token expired");
        }
        return token;
    }

    @Override
    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }
}
