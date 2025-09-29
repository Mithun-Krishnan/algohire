package com.algohire.backend.service;

import com.algohire.backend.model.RefreshToken;
import com.algohire.backend.model.Users;

import java.util.Optional;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(Users users);
    boolean isValid(String token);
    void deleteUsers(Users users);
    RefreshToken validateToken(RefreshToken token);
    Optional<RefreshToken> findByToken(String token);
    RefreshToken createRefreshToken(String email);
    void deleteToken(String token);



}
