package com.algohire.backend.mapper;

import com.algohire.backend.dto.request.JobRequstDto;
import com.algohire.backend.dto.response.JobDetailsResponseDto;
import com.algohire.backend.dto.response.JobSummeryResponseDto;
import com.algohire.backend.enums.JobStatus;
import com.algohire.backend.model.Job;
import com.algohire.backend.service.impl.UserServiceImpl;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;


public class JobMapper {


    public static Job jobBuilder(JobRequstDto requestDto){




//        JobStatus jobStatus=requstDto.getJobStatus()!= null?requstDto.getJobStatus(): JobStatus.OPEN;
        Job job=Job.builder()
                .title(requestDto.getTitle())
                .description(requestDto.getDescription())
                .city(requestDto.getCity())
                .salary(requestDto.getSalary())// optional
                .requiredExperince(requestDto.getExperience())
                .requiredSkills(requestDto.getSkills())
                .requiredExperince(requestDto.getExperience())  // optional
                .isDeleted(false)                        // default value
                .build();

        return job;
    }

    public static JobSummeryResponseDto toJobSummuryResponse (Job job,String company){
        return JobSummeryResponseDto.builder()
                .jobId(job.getId())
                .title(job.getTitle())
                .skills(job.getRequiredSkills())
                .experince(job.getRequiredExperince())
                .location(job.getCity())
                .description(job.getDescription())
                .company(company)
                .dateTime(job.getCreatedAt())
                .build();
    }

    public static JobDetailsResponseDto toJobDetilsResponse(Optional<Job> jobOpt, String jobOwner){
        Job job=jobOpt.orElseThrow(()->new UsernameNotFoundException("//no user found"));
        return JobDetailsResponseDto.builder()
                .jobId(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .city(job.getCity())
                .salary(job.getSalary())
                .createdAt(job.getCreatedAt())
                .skills(job.getRequiredSkills())
                .experience(job.getRequiredExperince())
                .createdByName(jobOwner)
                .build();
    }
}
