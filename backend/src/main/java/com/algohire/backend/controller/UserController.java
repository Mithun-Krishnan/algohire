package com.algohire.backend.controller;

import com.algohire.backend.dto.request.UserSkillsRequest;
import com.algohire.backend.dto.response.ProfileResponseDto;
import com.algohire.backend.dto.response.SkillsResponseDto;
import com.algohire.backend.dto.response.UserResponseSkills;
import com.algohire.backend.repository.UserRepository;
import com.algohire.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
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





}
