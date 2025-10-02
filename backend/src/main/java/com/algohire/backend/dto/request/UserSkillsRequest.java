package com.algohire.backend.dto.request;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class UserSkillsRequest {
    private Set<UUID> skillsId;
}
