package com.algohire.backend.controller;

import com.algohire.backend.dto.request.UserSkillsRequest;
import com.algohire.backend.dto.response.SkillsResponseDto;
import com.algohire.backend.dto.response.UserResponseSkills;
import com.algohire.backend.repository.UserRepository;
import com.algohire.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/skills")
    public ResponseEntity<UserResponseSkills> updateSkills(@RequestBody UserSkillsRequest request){
        return ResponseEntity.ok(userService.updateSkills(request.getSkillsId()));
    }

    @GetMapping("/allskills")
    public ResponseEntity<List<SkillsResponseDto>> getAllSKills(){
        return ResponseEntity.ok(userService.getAllSkills());
    }





}
