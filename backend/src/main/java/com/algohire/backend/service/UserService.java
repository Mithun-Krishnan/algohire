package com.algohire.backend.service;

import java.util.List;
import java.util.UUID;

import com.algohire.backend.dto.request.RecruiterExistCompanyRequstDto;
import com.algohire.backend.dto.request.RecruiterNewCompanyRequestDto;
import com.algohire.backend.dto.request.UserRequestDto;
import com.algohire.backend.dto.response.RecruiterResponseDto;
import com.algohire.backend.dto.response.UserResponse;

public interface UserService {
    UserResponse registerCandidate(UserRequestDto request);
    UserResponse getUserById(UUID id);
    RecruiterResponseDto recruiterNewCompanyRegister (RecruiterNewCompanyRequestDto requst);
    RecruiterResponseDto recuiterExistingCompany (RecruiterExistCompanyRequstDto requst);
    List<UserResponse> getAllUsers();
}
