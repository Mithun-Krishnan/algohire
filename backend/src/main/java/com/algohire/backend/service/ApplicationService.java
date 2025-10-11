package com.algohire.backend.service;

import com.algohire.backend.dto.request.ApplicationRequestDto;
import com.algohire.backend.dto.request.ApplicationUpdateRequestDto;
import com.algohire.backend.dto.response.ApplicationResponseDto;
import com.algohire.backend.model.Application;

import java.util.List;
import java.util.UUID;

public interface ApplicationService {
    ApplicationResponseDto applyFromReq(ApplicationRequestDto request);
    Application apply(UUID jobId, UUID userId, String coverLetter);
    List<Application> viewApplication(UUID userId);
    List<ApplicationResponseDto> viewApplicationFromReq();
    List<Application> viewApplicationRecruter(UUID jobid,UUID userid);
    List<ApplicationResponseDto> viewApplicationRecruterReq(UUID jobid);
    ApplicationResponseDto updateApplication(ApplicationUpdateRequestDto request);
}
