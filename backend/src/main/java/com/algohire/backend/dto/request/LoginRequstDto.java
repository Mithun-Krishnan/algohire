package com.algohire.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequstDto {
    
    @NotBlank(message="email cant be null")
    private String email;

    @NotBlank(message="password cant be null")
    private String password;
}
