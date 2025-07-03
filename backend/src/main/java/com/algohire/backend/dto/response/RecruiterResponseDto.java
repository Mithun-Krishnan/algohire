package com.algohire.backend.dto.response;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecruiterResponseDto {


    private UUID id;
    private String userName;
    private String email;
    private String role;
    private String companyName;
    private String phone;
    

}
