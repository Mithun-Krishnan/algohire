package com.algohire.backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;



@Data
public class UserRequestDto {
    @NotBlank(message="user name can't be blank")
    private String name;

    @Email(message="invalid email format")
    @NotBlank(message="email can't be blank")
    private String email;

    @NotBlank(message="phone number needed")
    private String phone;


    @Pattern(
    regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}\\[\\]:;\"'<>?,./]).{8,}$",
    message = "Password must be at least 8 characters and include uppercase, lowercase, digit, and special character")
    @NotBlank(message="password can't be blank")
    private String password;

    // @NotNull(message = "Role is required")
    // private UUID roleId;

    // @NotNull(message = "Company ID is required", groups = RecruiterValidation.class) 
    // private UUID companyId;  removed for creating the recruterRequst.
    

}
