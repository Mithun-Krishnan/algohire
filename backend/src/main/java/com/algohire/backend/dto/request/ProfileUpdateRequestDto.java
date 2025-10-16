package com.algohire.backend.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Set;

@Data
public class ProfileUpdateRequestDto {
    @Size(max = 100, message = "Full name must be less than 100 characters")
    private String fullName;

    @Email(message = "Invalid email format")
    private String email;

    private Set<String> skills;

//    private Integer experience;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phoneNumber;

    @Size(max = 100, message = "Location must be less than 100 characters")
    private String location;

    @Min(value = 0, message = "Years of experience cannot be negative")
    @Max(value = 50, message = "Years of experience is unrealistic")
    private Integer experience;

    private String resumeUrl;
}
