package com.algohire.backend.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;



import com.algohire.backend.enums.JobStatus;
import com.algohire.backend.model.audit.Auditable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    private String title;

     
    @Column(name = "job_description", nullable = false, columnDefinition = "TEXT")
    private String description;

     
    @Column(nullable = false)
    private String city;

    private String company;

     
//    @Column(nullable = false)
//    private String state;

     

     
    @Column(nullable = false)
    private String salary;

    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "created_by", nullable = false)
    private Users createdBy;

//    @Column(name = "deadline", nullable = false)
//    private LocalDateTime deadLine;

//    @Column
//    private String skills;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "job_skills",
//            joinColumns = @JoinColumn(name = "job_id"),
//            inverseJoinColumns = @JoinColumn(name = "skills_id")
//    )
    private Set<String> requiredSkills = new HashSet<>();

    @Column(nullable = true)
    private Integer requiredExperince;

//    @Enumerated(EnumType.STRING)
//
//    @Column(name = "job_status", nullable = false)
//    private JobStatus jobStatus;

//    @ManyToOne(fetch=FetchType.LAZY,optional=false)
//    @JoinColumn(name="job_category",nullable=false)
//    private JobCategory jobCategory;

    //    @Column(name = "address", nullable = false)
//    private String address;


//    using Auditble for thees





     @CreationTimestamp
     @Column(name = "created_at", updatable = false)
     private LocalDateTime createdAt;

     @UpdateTimestamp
     @Column(name = "updated_at")
     private LocalDateTime updatedAt;

    

    
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;
}
