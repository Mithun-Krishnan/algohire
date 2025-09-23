package com.algohire.backend.controller;

import com.algohire.backend.dto.request.LoginRequstDto;
import com.algohire.backend.dto.request.RecruiterExistCompanyRequstDto;
import com.algohire.backend.dto.request.RecruiterNewCompanyRequestDto;
import com.algohire.backend.dto.request.UserRequestDto;
import com.algohire.backend.dto.response.JwtResponseDto;
import com.algohire.backend.dto.response.RecruiterResponseDto;
import com.algohire.backend.dto.response.UserResponse;
import com.algohire.backend.model.RefreshToken;
import com.algohire.backend.service.JwtService;
import com.algohire.backend.service.RefreshTokenService;
import com.algohire.backend.service.impl.AuthserviceImpal;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/v1")
@AllArgsConstructor
public class AuthControler {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final AuthserviceImpal authserviceImpal;

        @PostMapping("/candidate/register")
    public ResponseEntity<JwtResponseDto> register(@Valid @RequestBody UserRequestDto userRequestDto){

            UserResponse userResponse=authserviceImpal.registerCandidate(userRequestDto);
            String jwt=jwtService.generateToken(userResponse.getEmail());
           RefreshToken refreshToken=refreshTokenService.createRefreshToken(userResponse.getEmail());

            return ResponseEntity.ok(JwtResponseDto.builder()
                    .accessToken(jwt)
                    .refreshToken(refreshToken.getToken())
                    .build());

//          EXAMPLE JSON
//            {
//                "name": "John Doe",
//                    "email": "john.doe@example2323.com",
//                    "phone": "9876543216",
//                    "password": "S@trongPass1"
//            }


    }

    @PostMapping("/recruiter/registerNewCompsny")
    public ResponseEntity<JwtResponseDto> recuiterRegisterNewCompany(@Valid @RequestBody RecruiterNewCompanyRequestDto recruiterRequestDto){
        RecruiterResponseDto response=authserviceImpal.recruiterNewCompanyRegister(recruiterRequestDto);

        String jwt=jwtService.generateToken(response.getEmail());
        RefreshToken refreshToken=refreshTokenService.createRefreshToken(response.getEmail());

        return ResponseEntity.ok(JwtResponseDto.builder()
                .accessToken(jwt)
                .refreshToken(refreshToken.getToken())
                .build());
    }

    @PostMapping("/recruiter/registerExistingcompany")
    public ResponseEntity<JwtResponseDto> recruiterRegisterExistCompany(@Valid @RequestBody RecruiterExistCompanyRequstDto recruiterExistCompanyRequstDto){
        RecruiterResponseDto response=authserviceImpal.recuiterExistingCompany(recruiterExistCompanyRequstDto);
        String jwt=jwtService.generateToken(response.getEmail());
        RefreshToken refreshToken=refreshTokenService.createRefreshToken(response.getEmail());

        return ResponseEntity.ok(JwtResponseDto.builder()
                .accessToken(jwt)
                .refreshToken(refreshToken.getToken())
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@Valid @RequestBody LoginRequstDto requst){
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requst.getEmail(),requst.getPassword())
        );

        if(authentication.isAuthenticated()){
            UserDetails userDetails=(UserDetails) authentication.getPrincipal();
            String accessToken=jwtService.generateToken(userDetails);
            RefreshToken refreshToken=refreshTokenService.createRefreshToken(userDetails.getUsername());

            return ResponseEntity.ok(JwtResponseDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken.getToken())
                    .build());
        }

        throw new UsernameNotFoundException("invalid user or password");
    }

}
