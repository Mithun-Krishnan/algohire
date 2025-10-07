package com.algohire.backend.dto.response;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CompanyExistResponseDto {
    private boolean exist;
    private UUID id;
    private String message;
   

   
}
