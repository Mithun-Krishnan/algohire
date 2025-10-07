package com.algohire.backend.service;

import com.algohire.backend.dto.request.JobRequstDto;
import com.algohire.backend.dto.response.JobSummeryResponseDto;
import com.algohire.backend.dto.response.JobDetailsResponseDto;
import com.algohire.backend.model.Job;

import java.util.List;
import java.util.UUID;

public interface JobService {
    public Job addJob(Job job,UUID userId);
    public List<JobSummeryResponseDto> viewJob(String city,String keyword);
    public JobDetailsResponseDto viewDeatilesJob(UUID jobId);
    public List<JobSummeryResponseDto> getJobRecruter();
    public JobSummeryResponseDto updateJob(JobRequstDto requst,UUID jobId);
    public void deletJob(UUID jobId);


}
