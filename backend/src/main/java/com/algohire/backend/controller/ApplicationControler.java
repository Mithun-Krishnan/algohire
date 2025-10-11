package com.algohire.backend.controller;

import com.algohire.backend.dto.request.ApplicationRequestDto;
import com.algohire.backend.dto.request.ApplicationUpdateRequestDto;
import com.algohire.backend.dto.response.ApplicationResponseDto;
import com.algohire.backend.service.impl.ApplicationServiceImapal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
public class ApplicationControler {

    private final ApplicationServiceImapal applicationServiceImapal;

    @PostMapping("/create")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ApplicationResponseDto createApplication(@RequestBody ApplicationRequestDto request){
            return applicationServiceImapal.applyFromReq(request);
    }

    @GetMapping("/candidate/view")
    public List< ApplicationResponseDto> viewCandidate(){
        return applicationServiceImapal.viewApplicationFromReq();
    }

    @GetMapping("/recruiter/view/{jobId}")
    public List<ApplicationResponseDto> viewRecruiter(@PathVariable UUID jobId){
        return applicationServiceImapal.viewApplicationRecruterReq(jobId);
    }

    @PatchMapping("/recruiter/update")
    @PreAuthorize("hasRole('RECRUITER')")
    public ApplicationResponseDto updateApplication(@RequestBody ApplicationUpdateRequestDto request){
        return applicationServiceImapal.updateApplication(request);
    }

}
