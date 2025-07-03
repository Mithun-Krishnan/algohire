package com.algohire.backend.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class RecruiterExistCompanyRequstDto {
      
    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message="phone is required")
    private String phone;

    @NotBlank(message = "Password is required")
    private String password;

    private UUID companyId;
}
