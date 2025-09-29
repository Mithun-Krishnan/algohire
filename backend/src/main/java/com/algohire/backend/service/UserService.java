package com.algohire.backend.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.algohire.backend.dto.request.RecruiterExistCompanyRequstDto;
import com.algohire.backend.dto.request.RecruiterNewCompanyRequestDto;
import com.algohire.backend.dto.request.UserRequestDto;
import com.algohire.backend.dto.response.RecruiterResponseDto;
import com.algohire.backend.dto.response.UserResponse;

public interface UserService {

    UserResponse getUserById(UUID id);
    List<UserResponse> getAllUsers();
    RecruiterResponseDto updateSkills(Set<UUID> skillIds);


}
