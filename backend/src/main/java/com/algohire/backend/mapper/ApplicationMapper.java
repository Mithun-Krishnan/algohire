package com.algohire.backend.mapper;

import com.algohire.backend.dto.request.ApplicationRequestDto;
import com.algohire.backend.dto.response.ApplicationResponseDto;
import com.algohire.backend.enums.ApplicationStatus;
import com.algohire.backend.model.Application;
import com.algohire.backend.model.Job;
import com.algohire.backend.model.Users;

import java.time.LocalDateTime;

public class ApplicationMapper {
    public static ApplicationResponseDto toResponse(Application application){
        return ApplicationResponseDto.builder()
                .id(application.getId())
                .userId(application.getUsers().getId())
                .jobId(application.getJobId().getId())
                .applicationStatus(application.getApplicationStatus())
                .appliedAt(application.getAppliedAt())
                .isShortListed(application.isShortListed())
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
