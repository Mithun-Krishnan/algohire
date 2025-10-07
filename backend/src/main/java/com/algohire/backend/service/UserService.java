package com.algohire.backend.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.algohire.backend.dto.response.ProfileResponseDto;
import com.algohire.backend.dto.response.SkillsResponseDto;
import com.algohire.backend.dto.response.UserResponse;
import com.algohire.backend.dto.response.UserResponseSkills;

public interface UserService {

    UserResponse getUserById(UUID id);
    List<UserResponse> getAllUsers();
    UserResponseSkills updateSkills(Set<UUID> skillIds);
    List<SkillsResponseDto> getAllSkills();
    ProfileResponseDto getProfile();


}
