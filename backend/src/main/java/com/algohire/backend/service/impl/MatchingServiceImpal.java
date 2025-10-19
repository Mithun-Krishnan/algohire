package com.algohire.backend.service.impl;

import com.algohire.backend.dto.response.ApplicationResponseDto;
import com.algohire.backend.exception.ResourceNotFoundException;
import com.algohire.backend.mapper.ApplicationMapper;
import com.algohire.backend.model.Application;
import com.algohire.backend.model.Job;
import com.algohire.backend.model.Users;
import com.algohire.backend.repository.ApplicationRepository;
import com.algohire.backend.repository.JobRepository;
import com.algohire.backend.repository.UserRepository;
import com.algohire.backend.service.MatchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MatchingServiceImpal implements MatchingService {
    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;

    @Override
    public double calculateMatchScore(Users user, Job job) {
        double skillMatch = computeSkillMatch(user.getSkills(), job.getRequiredSkills());
        double experienceMatch = Math.min(1.0, (double) user.getExperince() / job.getRequiredExperince());
        double locationMatch = user.getCity().equalsIgnoreCase(job.getCity()) ? 1.0 : 0.5;
        Integer activedays=(user.getActiveDays()!=null?user.getActiveDays():0);
        double activityScore = activedays < 7 ? 1.0 : 0.7;

        return (0.5 * skillMatch)
                + (0.3 * experienceMatch)
                + (0.1 * locationMatch)
                + (0.1 * activityScore);
    }

    @Override
    public double computeSkillMatch(Set<String> userSkills, Set<String> jobSkills) {
        if (jobSkills.isEmpty()) return 0.0;
        long matched = userSkills.stream()
                .filter(jobSkills::contains)
                .count();
        return (double) matched / jobSkills.size();
    }

    @Override
    public List<ApplicationResponseDto> getCandidateMatching(UUID jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("No job found"));

        List<Application> applications = applicationRepository.findByJobId_Id(jobId);

        // Sort applications by user matching score
        List<Application> sortedApplications = applications.stream()
                .sorted((a1, a2) -> Double.compare(
                        calculateMatchScore(a2.getUsers(), job),
                        calculateMatchScore(a1.getUsers(), job)))
                .collect(Collectors.toList());

        // Map each application to DTO with company name
        return sortedApplications.stream()
                .map(app -> {
                    double score = calculateMatchScore(app.getUsers(), job);
                    return ApplicationMapper.toResponse(app, job.getCompany(), score);
                        }
                )
                .collect(Collectors.toList());
    }
}
