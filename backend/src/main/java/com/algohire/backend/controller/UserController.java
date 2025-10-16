package com.algohire.backend.controller;

import com.algohire.backend.dto.request.CompanyRequstDto;
import com.algohire.backend.dto.request.ProfileUpdateRequestDto;
import com.algohire.backend.dto.request.UserSkillsRequest;
import com.algohire.backend.dto.response.*;
import com.algohire.backend.repository.UserRepository;
import com.algohire.backend.service.AuthService;
import com.algohire.backend.service.impl.RecruiterStatsServiceImpal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.algohire.backend.service.UserService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final RecruiterStatsServiceImpal recruiterStatsService;

    @PutMapping("/me/skills")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<UserResponseSkills> updateSkills(@RequestBody UserSkillsRequest request){
        return ResponseEntity.ok(userService.updateSkills(request.getSkillsId()));
    }

    @GetMapping("/skills")
    public ResponseEntity<List<SkillsResponseDto>> getAllSKills(){
        return ResponseEntity.ok(userService.getAllSkills());
    }

    @GetMapping("/me/profile")
    public ProfileResponseDto getProfile(){
        return userService.getProfile();
    }

    @PatchMapping("/me/profile/update")
    public ProfileResponseDto updateProfile(@RequestBody ProfileUpdateRequestDto request){
        return userService.updateProfile(request);
    }


    @GetMapping("/stats")
    public ResponseEntity<RecruiterStatsResponse> getRecruiterStats() {
        // Optional: extract recruiterId from token if needed
        RecruiterStatsResponse stats = recruiterStatsService.getRecruiterStats();
        return ResponseEntity.ok(stats);
    }

    @PostMapping("/addCompany")
    public ResponseEntity<CompanyResponseDto> creteCompnay(@RequestBody CompanyRequstDto requst){
        CompanyResponseDto response=userService.creteCompany(requst);
         return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
