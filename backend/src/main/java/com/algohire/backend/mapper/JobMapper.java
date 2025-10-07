package com.algohire.backend.mapper;

import com.algohire.backend.dto.request.JobRequstDto;
import com.algohire.backend.dto.response.JobDetailsResponseDto;
import com.algohire.backend.dto.response.JobSummeryResponseDto;
import com.algohire.backend.enums.JobStatus;
import com.algohire.backend.model.Job;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;


public class JobMapper {

    public static Job jobBuilder(JobRequstDto requstDto){


        JobStatus jobStatus=requstDto.getJobStatus()!= null?requstDto.getJobStatus(): JobStatus.OPEN;
        Job job=Job.builder()
                .jobTitle(requstDto.getTitle())
                .jobDescription(requstDto.getDescription())
                .city(requstDto.getCity())
                .jobStatus(jobStatus)
                .state(requstDto.getState())
                .address(requstDto.getAddress())
                .salary(requstDto.getSalary())
                .deadLine(requstDto.getDeadline())
                .build();

        return job;
    }

    public static JobSummeryResponseDto toJobSummuryResponse (Job job){
        return JobSummeryResponseDto.builder()
                .jobId(job.getId())
                .category(job.getJobCategory().getCategory())
                .title(job.getJobTitle())
                .location(job.getCity())
                .description(job.getJobDescription())
                .build();
    }

    public static JobDetailsResponseDto toJobDetilsResponse(Optional<Job> jobOpt, String jobOwner){
        Job job=jobOpt.orElseThrow(()->new UsernameNotFoundException("//no user found"));
        return JobDetailsResponseDto.builder()
                .jobId(job.getId())
                .jobstatus(job.getJobStatus().name())
                .title(job.getJobTitle())
                .salary(job.getSalary())
                .description(job.getJobDescription())
                .location(job.getCity())
                .createdByName(jobOwner)
                .category(job.getJobCategory().getCategory())
                .build();
    }
}
