package com.algohire.backend.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class ProfileResponseDto {
    private UUID id;
    private String userName;
    private String email;
    private String role;
    private String phone;
    private String profilePicUrl;
    private String resumeUrl;
    private String description;
    private boolean isActive;
    private LocalDateTime lastLogin;
    private CompanyResponseDto company;  // nested DTO
    private Set<String> skills;       // skill names or IDs

}
