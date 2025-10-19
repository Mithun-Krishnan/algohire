package com.algohire.backend.service.impl;

import com.algohire.backend.dto.request.JobRequstDto;
import com.algohire.backend.dto.response.JobDetailsResponseDto;
import com.algohire.backend.dto.response.JobSummeryResponseDto;
import com.algohire.backend.exception.ResourceNotFoundException;
import com.algohire.backend.exception.UnauthorizedActionException;
import com.algohire.backend.exception.UserNotFoundException;
import com.algohire.backend.mapper.JobMapper;
import com.algohire.backend.model.Job;
import com.algohire.backend.model.Users;
import com.algohire.backend.repository.JobRepository;
import com.algohire.backend.repository.JobcategoryRepository;
import com.algohire.backend.repository.UserRepository;
import com.algohire.backend.service.AuthService;
import com.algohire.backend.service.JobService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class JobServiceImapal implements JobService {

    private final JobcategoryRepository jobcategoryRepository;
    private final AuthService authService;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;


    @Override
    public Job addJob(Job job, UUID userId) {
        job.setCreatedBy(userRepository.findById(userId).orElseThrow(
                ()->new UsernameNotFoundException("//User not found with id"+userId+"in add job")));
        return jobRepository.save(job);
    }

    public JobSummeryResponseDto createJobfromReq(JobRequstDto requst){
        Job job= JobMapper.jobBuilder(requst);
//        System.out.println("JobCategoryId from request: " + requst.getJobCategoryId());

        // due to some resne removing the jobcat

//        JobCategory category = jobcategoryRepository.findById(requst.getJobCategoryId())
//                .orElseThrow(() -> new ResourceNotFoundException("Job category not found"));

        UUID jobCreatorId=authService.getCurrentUserId();
        System.out.println(jobCreatorId);

        Users recruiter = userRepository.findById(jobCreatorId)
                .orElseThrow(() -> new ResourceNotFoundException("Recruiter not found"));




//        job.setJobCategory(category);
        job.setCreatedBy(recruiter);
        job.setCompany(recruiter.getCompany().getName());
        Job savedJob=addJob(job,jobCreatorId);
        String compny=recruiter.getCompany().getName();


        return JobMapper.toJobSummuryResponse(savedJob,compny);
    }

    @Override
    public List<JobSummeryResponseDto> viewJob(String city,String keyword) {
        Users users=userRepository.findById(authService.getCurrentUserId()).orElseThrow(
                ()->new UsernameNotFoundException("no user found")
        );

        
      if(city==null)city="";
      if(keyword==null)keyword="";
      List<Job> jobs=jobRepository.findByCityContainingIgnoreCaseAndTitleContainingIgnoreCaseOrderByCreatedAtDesc(city, keyword);
      return jobs.stream()
              .filter(job -> !job.isDeleted())
              .map(job -> {
                  Users jobCre=job.getCreatedBy();
                  String company=jobCre.getCompany().getName();
                  return JobMapper.toJobSummuryResponse(job,company);
              })
              .collect(Collectors.toList());
    }

    @Override
    public JobDetailsResponseDto viewDeatilesJob(UUID jobId) {
        Optional<Job> job=jobRepository.findById(jobId);
        Users users=job.get().getCreatedBy();
        String username=users.getUsername();
        return JobMapper.toJobDetilsResponse(job,username);
    }

    @Override
    public List<JobSummeryResponseDto> getJobRecruter() {
        UUID userid=authService.getCurrentUserId();
        Users users=userRepository.findById(userid).orElseThrow(
                ()->new UsernameNotFoundException("// user not found in id"+userid+"in getJobrecutrie"));
        return jobRepository.findByCreatedByAndIsDeletedFalseOrderByCreatedAtDesc(users).stream()
                .map(job -> {
                    String companyName = null;
                    if (job.getCreatedBy() != null && job.getCreatedBy().getCompany() != null) {
                        companyName = job.getCreatedBy().getCompany().getName();
                    }
                    return JobMapper.toJobSummuryResponse(job, companyName);
                })
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public JobSummeryResponseDto updateJob(JobRequstDto request,UUID jobId) {
        UUID userId=authService.getCurrentUserId();
        Job existJob=jobRepository.findById(jobId).orElseThrow(
                ()->new ResourceNotFoundException("no job found in id"+jobId));

        if(!userId.equals(existJob.getCreatedBy().getId())){
            throw new UnauthorizedActionException("not authorized for update job");
        }

        if (request.getTitle() != null) existJob.setTitle(request.getTitle());
        if (request.getDescription() != null) existJob.setDescription(request.getDescription());
        if (request.getCity() != null) existJob.setCity(request.getCity());
        if (request.getSalary() != null) existJob.setSalary(request.getSalary());
//        if (request.getSkills() != null) existJob.setSkills(request.getSkills());
        if (request.getExperience() != 0) existJob.setRequiredExperince(request.getExperience());

//        if (request.getJobCategoryId() != null) {
//            JobCategory category = jobcategoryRepository.findById(request.getJobCategoryId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Job category not found"));
//            existJob.setJobCategory(category);
//        }


        Job updatedJob = jobRepository.save(existJob);
        Users users=userRepository.findById(userId).orElseThrow(
                ()->new UserNotFoundException("user not found"));
        String company=users.getCompany().getName();


        return JobMapper.toJobSummuryResponse(updatedJob,company);

    }

    @Transactional
    @Override
    public void deletJob(UUID jobId) {

        UUID userId=authService.getCurrentUserId();

        Job job=jobRepository.findById(jobId).orElseThrow(()-> new ResourceNotFoundException("no job with id"+jobId));

        if(!userId.equals(job.getCreatedBy().getId())) throw new UnauthorizedActionException("you dont have authority to delete it");

        job.setDeleted(true);

        jobRepository.save(job);
    }
}
