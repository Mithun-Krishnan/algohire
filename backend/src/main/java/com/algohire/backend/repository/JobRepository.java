package com.algohire.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algohire.backend.model.Job;

public interface  JobRepository extends JpaRepository<Job, UUID>{
    
    
}
