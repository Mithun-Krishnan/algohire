package com.algohire.backend.controller;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class UserSkillsRequest {
    private Set<UUID> skillsId;
}
