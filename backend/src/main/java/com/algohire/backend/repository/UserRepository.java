package com.algohire.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algohire.backend.model.Users;

public interface  UserRepository extends JpaRepository<Users, UUID> {

    boolean existsByEmail(String email);
    Users findByUsername(String userName);
    Users findByEmail(String email);
    
}
