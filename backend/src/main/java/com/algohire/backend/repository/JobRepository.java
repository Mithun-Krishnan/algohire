package com.algohire.backend.repository;

import java.util.List;
import java.util.UUID;

import com.algohire.backend.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.algohire.backend.model.Job;

public interface  JobRepository extends JpaRepository<Job, UUID>{
    public List<Job> findByCreatedBy(Users users);
    List<Job> findByCityContainingIgnoreCaseAndTitleContainingIgnoreCaseOrderByCreatedAtDesc(String city, String keyword);

    Page<Job> findByCityContainingIgnoreCaseAndTitleContainingIgnoreCaseAndIsDeletedFalseOrderByCreatedAtDesc(String city, String keyword, Pageable pageable);

    // count all active jobs by recruiter/user
    long countByCreatedById(UUID userId);

    // optional: count jobs by deleted status
    long countByCreatedByIdAndIsDeleted(UUID userId, boolean isDeleted);

    List<Job> findByCreatedByAndIsDeletedFalseOrderByCreatedAtDesc(Users createdBy);
}
