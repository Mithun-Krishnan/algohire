package com.algohire.backend.service.impl;

import com.algohire.backend.dto.request.ApplicationRequestDto;
import com.algohire.backend.dto.request.ApplicationUpdateRequestDto;
import com.algohire.backend.dto.response.ApplicationResponseDto;
import com.algohire.backend.enums.ApplicationStatus;
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
import java.util.stream.Collectors;

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

        if(applicationRepository.existsByUsers_IdAndJobId_Id(userId,jobId)){
            throw  new IllegalArgumentException("allredy aplied");
        }

        Application app=ApplicationMapper.toEntity(coverLetter,users,job);
        Application saved=applicationRepository.save(app);
        return saved;
    }

    @Override
    public List<Application> viewApplication(UUID userId) {
        return applicationRepository.findByUsers_Id(userId);
    }

    @Override
    public List<ApplicationResponseDto> viewApplicationFromReq() {
        UUID userid=authserviceImpal.getCurrentUserId();
        return viewApplication(userid).stream()
                .map(ApplicationMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<Application> viewApplicationRecruter(UUID jobid, UUID userid) {
        Job job=jobRepository.findById(jobid).orElseThrow(()->new ResourceNotFoundException("no job with id"+jobid));

        if(!job.getCreatedBy().getId().equals(userid)){
            throw new UnauthorizedActionException("you cant see thees job");
        }

        return applicationRepository.findByJobId_Id(jobid);
    }

    @Override
    public List<ApplicationResponseDto> viewApplicationRecruterReq(UUID jobid) {
        UUID userId=authserviceImpal.getCurrentUserId();
        return viewApplicationRecruter(jobid,userId).stream()
                .map(ApplicationMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public ApplicationResponseDto updateApplication(ApplicationUpdateRequestDto request) {
        UUID userid = authserviceImpal.getCurrentUserId();
        // 1. Fetch the application
        Application app = applicationRepository.findById(request.getApplicationId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No application found with ID: " + request.getApplicationId()));

        // 2. Fetch the job and ensure recruiter owns it
        Job job = app.getJobId();
        if (!userid.equals(job.getCreatedBy().getId())) {
            throw new UnauthorizedActionException("You cannot update applications for jobs you don't own");
        }

        // 3. Validate that the status is not null
        if (request.getApplicationStatus() == null) {
            throw new IllegalArgumentException("Application status must be provided");
        }

        // 4. Update the status
        app.setApplicationStatus(request.getApplicationStatus());

        if(app.getApplicationStatus()==ApplicationStatus.SHORTLISTED){
            app.setShortListed(true);
        }

        // 5. Save the updated application
        applicationRepository.save(app);

        // 6. Return response DTO
        return ApplicationMapper.toResponse(app);
    }
}
