package com.algohire.backend.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.algohire.backend.enums.RoleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue
    @Column(columnDefinition="UUID")
    private UUID id;

    @Column(name="role" ,unique=true)
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;



}


// INSERT INTO role (id, role) VALUES
//   ('a2f664dc-2eb8-4e56-b030-2f4f0c7f7bb9', 'ADMIN'),
//   ('b3e199f4-3f6f-4c92-a8a5-899d6d7fa21d', 'RECRUITER'),
//   ('c3f6f3f1-8393-4eb5-b9a5-108a6c8f46d2', 'CANDIDATE');
