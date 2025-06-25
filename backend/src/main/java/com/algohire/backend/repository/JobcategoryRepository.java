package com.algohire.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algohire.backend.model.JobCategory;

public interface JobcategoryRepository extends JpaRepository<JobCategory, UUID> {
    
}
