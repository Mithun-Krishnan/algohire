package com.algohire.backend.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.algohire.backend.enums.ApplicationStatus;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="application"
)
public class Application extends Auditable{

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",nullable=false)
    private Users users;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="job_id" ,nullable=false)
    private Job jobId;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name="application_status",nullable=false)
    private ApplicationStatus applicationStatus;

    @Column(name="applied_at",nullable=false)
     
    private LocalDateTime appliedAt;

    private boolean isShortListed;

    @Column(name="cover_letter",nullable=false,columnDefinition="TEXT")
    private String coverLetter;



    
}
