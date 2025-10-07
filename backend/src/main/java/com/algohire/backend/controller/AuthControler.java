package com.algohire.backend.controller;

import com.algohire.backend.dto.request.*;
import com.algohire.backend.dto.response.JwtResponseDto;
import com.algohire.backend.dto.response.RecruiterResponseDto;
import com.algohire.backend.dto.response.UserResponse;
import com.algohire.backend.exception.TokenNotFoundException;
import com.algohire.backend.model.RefreshToken;
import com.algohire.backend.service.CustomUserDetailsService;
import com.algohire.backend.service.JwtService;
import com.algohire.backend.service.RefreshTokenService;
import com.algohire.backend.service.impl.AuthserviceImpal;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth/v1")
@AllArgsConstructor
@Slf4j
public class AuthControler {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final AuthserviceImpal authserviceImpal;
    private final CustomUserDetailsService customUserDetailsService;

        @PostMapping("/candidate/register")
    public ResponseEntity<JwtResponseDto> register(@Valid @RequestBody UserRequestDto userRequestDto){

            UserResponse userResponse=authserviceImpal.registerCandidate(userRequestDto);
            UserDetails userDetails=customUserDetailsService.loadUserByUsername(userResponse.getEmail());
            String jwt=jwtService.generateToken(userDetails);
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

    @PostMapping("/recruiter/registerNewCompany")
    public ResponseEntity<JwtResponseDto> recuiterRegisterNewCompany(@Valid @RequestBody RecruiterNewCompanyRequestDto recruiterRequestDto){
        RecruiterResponseDto response=authserviceImpal.recruiterNewCompanyRegister(recruiterRequestDto);

        UserDetails userDetails=customUserDetailsService.loadUserByUsername(response.getEmail());
        String jwt=jwtService.generateToken(userDetails);
        RefreshToken refreshToken=refreshTokenService.createRefreshToken(response.getEmail());

        return ResponseEntity.ok(JwtResponseDto.builder()
                .accessToken(jwt)
                .refreshToken(refreshToken.getToken())
                .build());
    }

    @PostMapping("/recruiter/registerExistingcompany")
    public ResponseEntity<JwtResponseDto> recruiterRegisterExistCompany(@Valid @RequestBody RecruiterExistCompanyRequstDto recruiterExistCompanyRequstDto){
        RecruiterResponseDto response=authserviceImpal.recuiterExistingCompany(recruiterExistCompanyRequstDto);
        UserDetails userDetails=customUserDetailsService.loadUserByUsername(response.getEmail());
        String jwt=jwtService.generateToken(userDetails);
        RefreshToken refreshToken=refreshTokenService.createRefreshToken(response.getEmail());

        return ResponseEntity.ok(JwtResponseDto.builder()
                .accessToken(jwt)
                .refreshToken(refreshToken.getToken())
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@Valid @RequestBody LoginRequstDto requst){

            try {
                Authentication authentication=authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(requst.getEmail(),requst.getPassword())
                );
            UserDetails userDetails=(UserDetails) authentication.getPrincipal();
            String accessToken=jwtService.generateToken(userDetails);
            RefreshToken refreshToken=refreshTokenService.createRefreshToken(userDetails.getUsername());

            return ResponseEntity.ok(JwtResponseDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken.getToken())
                    .build());
            }
            catch (AuthenticationException ex){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(null);
            }






//        throw new UsernameNotFoundException("invalid user or password");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody String refreshToken) {
        refreshTokenService.findByToken(refreshToken)
                .ifPresent(token->refreshTokenService.deleteUsers(token.getUsers())); // implement deleteToken
        return ResponseEntity.ok("Logged out successfully");
    }


    @PostMapping("/refresh")
    public ResponseEntity<JwtResponseDto> refresh(@RequestBody RefreshTokenRequstDto req) {

        log.info("//Received refresh request for token: {}", req.getToken());
        RefreshToken token = refreshTokenService.findByToken(req.getToken())
                .orElseThrow(() -> new TokenNotFoundException("Refresh token not found"));

        // validate refresh token (throws exception if expired and deletes it)
        refreshTokenService.validateToken(token);

        UserDetails userDetails=customUserDetailsService.loadUserByUsername(token.getUsers().getEmail());

        String newAccessToken = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(JwtResponseDto.builder()
                .accessToken(newAccessToken)
                .refreshToken(token.getToken()) // reuse same refresh token
                .build());
    }


}
