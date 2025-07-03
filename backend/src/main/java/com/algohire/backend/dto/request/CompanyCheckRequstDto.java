package com.algohire.backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyCheckRequstDto {

    @NotBlank
    private  String name;
    @Email
    @NotBlank
    private String email;
}
