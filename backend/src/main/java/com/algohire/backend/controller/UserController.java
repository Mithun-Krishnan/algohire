package com.algohire.backend.controller;

import com.algohire.backend.exception.UserNotFoundException;
import com.algohire.backend.model.Skills;
import com.algohire.backend.model.Users;
import com.algohire.backend.repository.UserRepository;
import com.algohire.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algohire.backend.dto.request.RecruiterExistCompanyRequstDto;
import com.algohire.backend.dto.request.RecruiterNewCompanyRequestDto;
import com.algohire.backend.dto.request.UserRequestDto;
import com.algohire.backend.dto.response.RecruiterResponseDto;
import com.algohire.backend.dto.response.UserResponse;
import com.algohire.backend.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.Set;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthService authService;

//    @PostMapping("/skills")
//    public ResponseEntity<UserResponse> updateSkills(@RequestBody UserSkillsRequest request){
//
//    }





}
