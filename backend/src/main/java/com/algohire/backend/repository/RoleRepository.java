package com.algohire.backend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algohire.backend.enums.RoleType;
import com.algohire.backend.model.Role;

public interface RoleRepository extends JpaRepository<Role, UUID>{

    Optional<Role> findByRole(RoleType role);
    
}
