package com.algohire.backend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algohire.backend.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, UUID>{
    boolean existsByUsers_IdAndJobId_Id(UUID userid, UUID jobid);
    List<Application> findByUsers_Id(UUID userId);
    List<Application> findByJobId_Id(UUID jobId);
}
