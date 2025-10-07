package com.algohire.backend.service.impl;

import com.algohire.backend.dto.request.ApplicationRequestDto;
import com.algohire.backend.dto.response.ApplicationResponseDto;
import com.algohire.backend.exception.ResourceNotFoundException;
import com.algohire.backend.exception.UnauthorizedActionException;
import com.algohire.backend.mapper.ApplicationMapper;
import com.algohire.backend.model.Application;
import com.algohire.backend.model.Job;
import com.algohire.backend.model.Users;
import com.algohire.backend.repository.ApplicationRepository;
import com.algohire.backend.repository.JobRepository;
import com.algohire.backend.repository.UserRepository;
import com.algohire.backend.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImapal implements ApplicationService {
    private final AuthserviceImpal authserviceImpal;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;


    @Override
    public ApplicationResponseDto applyFromReq(ApplicationRequestDto request) {
        UUID userid=authserviceImpal.getCurrentUserId();
        Application app=apply(request.getJobId(),userid,request.getCoverLetter());
        return ApplicationMapper.toResponse(app);
    }

    @Override
    public Application apply(UUID jobId, UUID userId, String coverLetter) {
        Job job=jobRepository.findById(jobId).orElseThrow(
                ()->new ResourceNotFoundException("no job found in the job id"+jobId));

        Users users=userRepository.findById(userId).orElseThrow(
                ()->new UnauthorizedActionException("user not found "+userId));

        if(applicationRepository.existsByUserIdIdAndJobIdId(userId,jobId)){
            throw  new IllegalArgumentException("allredy aplied");
        }

        return ApplicationMapper.toEntity(coverLetter,users,job);
    }

    @Override
    public List<Application> viewApplication(UUID userId,UUID jobid) {
        return List.of();
    }

    @Override
    public List<ApplicationResponseDto> viewApplicationFromReq(ApplicationRequestDto request) {
        UUID userid=authserviceImpal.getCurrentUserId();
        
    }

    @Override
    public List<Application> viewApplicationRecruter(UUID jobid, UUID userid) {
        return List.of();
    }

    @Override
    public List<ApplicationResponseDto> viewApplicationRecruterReq(UUID jobid) {
        return List.of();
    }

    @Override
    public ApplicationResponseDto updateApplication(ApplicationRequestDto request) {
        return null;
    }
}
