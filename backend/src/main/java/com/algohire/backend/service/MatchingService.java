package com.algohire.backend.service;

import com.algohire.backend.dto.response.ApplicationResponseDto;
import com.algohire.backend.model.Application;
import com.algohire.backend.model.Job;
import com.algohire.backend.model.Skills;
import com.algohire.backend.model.Users;
import org.apache.kafka.common.protocol.types.Field;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface MatchingService {
    public double calculateMatchScore(Users user, Job job);
    double computeSkillMatch(Set<String> userSkills, Set<String> jobSkills);
    List<ApplicationResponseDto> getCandidateMatching(UUID job);
}
