package com.algohire.backend.mapper;

import com.algohire.backend.dto.response.RecruiterResponseDto;
import com.algohire.backend.dto.response.UserResponse;
import com.algohire.backend.model.Users;

public class UserMapper {
    public static RecruiterResponseDto toRecruiterResponse(Users user) {
        return RecruiterResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phone(user.getPhone())
                .userName(user.getUsername())
                .companyName(user.getCompany().getName())
                .role(user.getRole().getRole().toString())
                .build();

    }

    public static UserResponse toUserResponse(Users users){
        return UserResponse.builder()
                .email(users.getEmail())
                .id(users.getId())
                .userName(users.getUsername())
                .phone(users.getPhone())
                .build();
    }
}
