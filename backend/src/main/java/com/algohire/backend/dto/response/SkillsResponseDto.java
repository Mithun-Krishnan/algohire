package com.algohire.backend.dto.response;

import com.algohire.backend.model.Skills;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkillsResponseDto {
    private UUID id;
    private String name;

}
