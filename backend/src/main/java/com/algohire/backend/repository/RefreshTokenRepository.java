package com.algohire.backend.repository;

import com.algohire.backend.model.RefreshToken;
import com.algohire.backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken>findByUsers(Users users);
    void deleteByUsers(Users users);

    void deleteAllByUsers(Users users);

}
