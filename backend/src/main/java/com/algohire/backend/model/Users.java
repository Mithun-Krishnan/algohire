package com.algohire.backend.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.algohire.backend.model.audit.Auditable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users",indexes={
    @Index(name="indx_eamil",columnList="email"),
    @Index(name="indx_phone",columnList="phone")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users extends Auditable{

    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "username", nullable = false)
    private String username;

    @NotBlank
    @Email
    @Size(max = 100)
    @Column(name = "email", nullable = false,unique=true)
    private String email;

    @NotBlank
    @Size(min = 8)
    @Column(name = "password", nullable = false)
    private String password;

    @Size(max = 255)
    @Column(name = "profile_pic_url")
    private String profilePicUrl;

    @Size(max = 255)
    @Column(name = "resume_url")
    private String resumeUrl;

    @NotBlank
    @Size(max = 20)
    @Column(name = "phone", nullable = false,unique=true)
    private String phone;

    @Size(max = 500)
    @Column(name = "description")
    private String description;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    // @CreationTimestamp
    // @Column(name = "created_at", updatable = false, nullable = false)
    // private LocalDateTime createdAt;

    // @UpdateTimestamp
    // @Column(name = "updated_at", nullable = false)
    // private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id",nullable=true)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY,optional=true)
    @JoinColumn(name = "role_id", nullable = true)
    private Role role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_skills",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "skills_id")
    )
    private Set<Skills> skills=new HashSet<>();
}
