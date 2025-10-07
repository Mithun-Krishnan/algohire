package com.algohire.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algohire.backend.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, UUID>{
    boolean existsByUserIdIdAndJobIdId(UUID userid,UUID jobid);
}
