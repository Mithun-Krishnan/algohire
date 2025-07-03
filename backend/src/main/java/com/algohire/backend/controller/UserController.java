package com.algohire.backend.controller;

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

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    @GetMapping
    public String hellouser(){
        return "helo user";
    }

    @PostMapping("/candidate/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequestDto userRequestDto){
        UserResponse response=userService.registerCandidate(userRequestDto);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PostMapping("/recruiter/registerNewCompsny")
    public ResponseEntity<RecruiterResponseDto> recuiterRegisterNewCompany(@Valid @RequestBody RecruiterNewCompanyRequestDto recruiterRequestDto){
        RecruiterResponseDto response=userService.recruiterNewCompanyRegister(recruiterRequestDto);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PostMapping("/recruiter/registerExistingcompany")
    public ResponseEntity<RecruiterResponseDto> recruiterRegisterExistCompany(@Valid @RequestBody RecruiterExistCompanyRequstDto recruiterExistCompanyRequstDto){
        RecruiterResponseDto response=userService.recuiterExistingCompany(recruiterExistCompanyRequstDto);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

}
