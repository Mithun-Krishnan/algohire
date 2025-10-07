package com.algohire.backend.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CompanyResponseDto {
    private UUID id;
    private String name;
    private String email;
    private String website;
    private String address;
    private String phone;
    private String about;
    private String size;
    private String logo;

    // Instead of exposing entire User entity, expose only id or name/email
    private UUID createdById;
    private String createdByEmail;
}
