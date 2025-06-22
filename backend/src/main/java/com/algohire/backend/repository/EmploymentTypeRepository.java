package com.algohire.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algohire.backend.model.EmploymentType;

public interface EmploymentTypeRepository extends JpaRepository<EmploymentType, UUID>{
    
}
