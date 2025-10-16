package com.algohire.backend.service.impl;

import com.algohire.backend.dto.response.RecruiterStatsResponse;
import com.algohire.backend.enums.ApplicationStatus;
import com.algohire.backend.repository.ApplicationRepository;
import com.algohire.backend.repository.JobRepository;
import com.algohire.backend.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecruiterStatsServiceImpal {
    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;
    private final AuthserviceImpal authserviceImpal;
    private final JwtService jwtService; // optional, only if token-based recruiterId extraction

    public RecruiterStatsResponse getRecruiterStats() {

        UUID recruiterId = authserviceImpal.getCurrentUserId();

        long jobsPosted = jobRepository.countByCreatedById(recruiterId);
        long totalApplications = applicationRepository.countByJobIdCreatedById(recruiterId);
        long shortlisted = applicationRepository.countByJobIdCreatedByIdAndApplicationStatus(recruiterId, ApplicationStatus.SHORTLISTED);

        return RecruiterStatsResponse.builder()
                .applications(totalApplications)
                .jobsPosted(jobsPosted)
                .shortlisted(shortlisted).build();
    }
}
