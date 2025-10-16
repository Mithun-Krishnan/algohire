package com.algohire.backend.mapper;

import com.algohire.backend.dto.response.ApplicationResponseDto;
import com.algohire.backend.enums.ApplicationStatus;
import com.algohire.backend.model.Application;
import com.algohire.backend.model.Job;
import com.algohire.backend.model.Users;

import java.time.LocalDateTime;


public class ApplicationMapper {



    public static ApplicationResponseDto toResponse(Application application,String company,double score){

        Users users=application.getJobId().getCreatedBy();
        String location=users.getCity();

        return ApplicationResponseDto.builder()
                .id(application.getId())
                .userId(application.getUsers().getId())
                .userName(application.getUsers().getUsername())
                .email(application.getUsers().getEmail())
                .jobId(application.getJobId().getId())
                .location(location)
                .score(score)
                .jobName(application.getJobId().getTitle())
                .applicationStatus(application.getApplicationStatus())
                .appliedAt(application.getAppliedAt())
                .status(application.getApplicationStatus())
                .isShortListed(application.isShortListed())
                .company(company)
                .coverLetter(application.getCoverLetter()).build();
    }

    public static Application toEntity(String coverLetter, Users users, Job job){
        return Application.builder()
                .users(users)
                .jobId(job)
                .coverLetter(coverLetter)
                .applicationStatus(ApplicationStatus.APPLIED) // default
                .appliedAt(LocalDateTime.now())
                .isShortListed(false).build();

    }
}
