package com.algohire.backend.service;

import com.algohire.backend.dto.request.RecruiterExistCompanyRequstDto;
import com.algohire.backend.dto.request.RecruiterNewCompanyRequestDto;
import com.algohire.backend.dto.request.UserRequestDto;
import com.algohire.backend.dto.response.RecruiterResponseDto;
import com.algohire.backend.dto.response.UserResponse;

import java.util.UUID;

public interface AuthService {

    RecruiterResponseDto recruiterNewCompanyRegister (RecruiterNewCompanyRequestDto requst);
    RecruiterResponseDto recuiterExistingCompany (RecruiterExistCompanyRequstDto requst);
    UserResponse registerCandidate(UserRequestDto request);
    UUID getCurrentUserId();
}
