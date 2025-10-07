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

   
    @Column(name = "job_title", nullable = false)
    private String jobTitle;

     
    @Column(name = "job_description", nullable = false, columnDefinition = "TEXT")
    private String jobDescription;

     
    @Column(nullable = false)
    private String city;

     
    @Column(nullable = false)
    private String state;

     
    @Column(name = "address", nullable = false)
    private String address;
     
    @Column(nullable = false)
    private String salary;

    
    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadLine;

    @Enumerated(EnumType.STRING)
     
    @Column(name = "job_status", nullable = false)
    private JobStatus jobStatus;
    
    @ManyToOne(fetch=FetchType.LAZY,optional=false)
    @JoinColumn(name="job_category",nullable=false)
    private JobCategory jobCategory;


//    using Auditble for thees

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "created_by", nullable = false)
    private Users createdBy;




    // @CreationTimestamp
    // @Column(name = "created_at", updatable = false)
    // private LocalDateTime createdAt;

    // @UpdateTimestamp
    // @Column(name = "updated_at")
    // private LocalDateTime updatedAt;

    

    
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;
}
