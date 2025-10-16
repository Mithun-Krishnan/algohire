package com.algohire.backend.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.algohire.backend.dto.request.CompanyRequstDto;
import com.algohire.backend.dto.request.ProfileUpdateRequestDto;
import com.algohire.backend.dto.response.*;

public interface UserService {

    UserResponse getUserById(UUID id);
    List<UserResponse> getAllUsers();
    UserResponseSkills updateSkills(Set<UUID> skillIds);
    List<SkillsResponseDto> getAllSkills();
    ProfileResponseDto getProfile();
    CompanyResponseDto creteCompany(CompanyRequstDto requst);
    ProfileResponseDto updateProfile(ProfileUpdateRequestDto request);


}
