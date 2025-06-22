package com.algohire.backend.model;

import java.time.LocalDateTime;
import java.util.UUID;



import com.algohire.backend.enums.JobStatus;
import com.algohire.backend.model.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "jobs") 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job extends Auditable{

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @NotBlank
    @Column(name = "job_description", nullable = false, columnDefinition = "TEXT")
    private String jobDescription;

    @NotBlank
    @Column(nullable = false)
    private String city;

    @NotBlank
    @Column(nullable = false)
    private String state;

    @NotBlank
    @Column(name = "address", nullable = false)
    private String address;
    @NotBlank
    @Column(nullable = false)
    private String salary;

    @NotNull
    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadLine;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "job_status", nullable = false)
    private JobStatus jobStatus;
    
    @ManyToOne(fetch=FetchType.LAZY,optional=false)
    @JoinColumn(name="job_category",nullable=false)
    private JobCategory jobCategory;


    //using Auditble for thees 

    // @ManyToOne(fetch = FetchType.LAZY, optional = false)
    // @JoinColumn(name = "created_by", nullable = false)
    // private Users createdBy;


    // @CreationTimestamp
    // @Column(name = "created_at", updatable = false)
    // private LocalDateTime createdAt;

    // @UpdateTimestamp
    // @Column(name = "updated_at")
    // private LocalDateTime updatedAt;

    

    
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;
}
