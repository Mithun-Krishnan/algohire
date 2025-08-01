package com.algohire.backend.controller;

import com.algohire.backend.dto.request.RecruiterExistCompanyRequstDto;
import com.algohire.backend.dto.request.RecruiterNewCompanyRequestDto;
import com.algohire.backend.dto.request.UserRequestDto;
import com.algohire.backend.dto.response.RecruiterResponseDto;
import com.algohire.backend.dto.response.UserResponse;
import com.algohire.backend.service.CustomUserDetailsService;
import com.algohire.backend.service.JwtService;
import com.algohire.backend.service.RefreshTokenService;
import com.algohire.backend.service.impl.AuthserviceImpal;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/v1")
@AllArgsConstructor
public class AuthControler {

    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AuthserviceImpal authserviceImpal;

    @PostMapping("/candidate/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequestDto userRequestDto){
       try{
            UserResponse userResponse=authserviceImpal.registerCandidate(userRequestDto);
            String jwt=jwtService.generateToken(userResponse.getEmail());

            return ResponseEntity.ok(JwtRes)
       }
    }

    @PostMapping("/recruiter/registerNewCompsny")
    public ResponseEntity<RecruiterResponseDto> recuiterRegisterNewCompany(@Valid @RequestBody RecruiterNewCompanyRequestDto recruiterRequestDto){
        RecruiterResponseDto response=authserviceImpal.recruiterNewCompanyRegister(recruiterRequestDto);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PostMapping("/recruiter/registerExistingcompany")
    public ResponseEntity<RecruiterResponseDto> recruiterRegisterExistCompany(@Valid @RequestBody RecruiterExistCompanyRequstDto recruiterExistCompanyRequstDto){
        RecruiterResponseDto response=authserviceImpal.recuiterExistingCompany(recruiterExistCompanyRequstDto);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

}
