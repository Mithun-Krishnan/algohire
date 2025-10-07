package com.algohire.backend.controller;

import com.algohire.backend.dto.request.JobRequstDto;
import com.algohire.backend.dto.response.JobDetailsResponseDto;
import com.algohire.backend.dto.response.JobSummeryResponseDto;
import com.algohire.backend.service.JobService;
import com.algohire.backend.service.impl.JobServiceImapal;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@RestController
@RequestMapping("/job")
public class JobControler {

    private final JobServiceImapal jobServiceImapal;

    @PostMapping("/create")
    @PreAuthorize("hasRole('RECRUITER')")
    public JobSummeryResponseDto createJob(@RequestBody JobRequstDto requstDto){
        return jobServiceImapal.createJobfromReq(requstDto);
    }


    @GetMapping("/recruter/jobs")
    @PreAuthorize("hasRole('RECRUITER')")
    public List<JobSummeryResponseDto> getJobRecruter(){
        return jobServiceImapal.getJobRecruter();
    }

    @GetMapping("/{jobId}")
    public JobDetailsResponseDto getDetialdJob(@PathVariable UUID jobId){
        return jobServiceImapal.viewDeatilesJob(jobId);
    }

    @PatchMapping("/recruter/update/{jobid}")
    @PreAuthorize("hasRole('RECRUITER')")
    public JobSummeryResponseDto updateJob(@RequestBody JobRequstDto jobRequstDto,@PathVariable UUID jobid){
        return jobServiceImapal.updateJob(jobRequstDto,jobid);
    }


    @DeleteMapping("/recruter/delete/{jobId}")
    @PreAuthorize("hasRole('RECRUITER')")
    public ResponseEntity<Void> deleteJob(@PathVariable UUID jobId){
        jobServiceImapal.deletJob(jobId);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/serchjob")
    public ResponseEntity<List<JobSummeryResponseDto>> serchJob(@RequestParam (required = false) String city,
                                                          @RequestParam(required = false)String keyword){

        List<JobSummeryResponseDto> jobSummeryResponseDtos=jobServiceImapal.viewJob(city, keyword);
        return ResponseEntity.ok(jobSummeryResponseDtos);

    }

}
