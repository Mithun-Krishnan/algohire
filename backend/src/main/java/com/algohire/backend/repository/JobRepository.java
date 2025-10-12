package com.algohire.backend.repository;

import java.util.List;
import java.util.UUID;

import com.algohire.backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import com.algohire.backend.model.Job;

public interface  JobRepository extends JpaRepository<Job, UUID>{
    public List<Job> findByCreatedBy(Users users);
    List<Job> findByCityContainingIgnoreCaseAndTitleContainingIgnoreCase(String city, String keyword);
    
    
}
