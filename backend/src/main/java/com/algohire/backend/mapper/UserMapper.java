package com.algohire.backend.mapper;

import com.algohire.backend.dto.response.*;
import com.algohire.backend.model.Company;
import com.algohire.backend.model.Users;

import java.util.stream.Collectors;

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

//    public static CompanyResponseDto to companyResponseDto(Company company){
//        return CompanyResponseDto.builder()
//                .id(company.getId())
//                .exist()
//    }

    public static UserResponseSkills toSkillsResponce(Users users){
        return UserResponseSkills.builder()
                .id(users.getId())
                .name(users.getUsername())
                .skills(users.getSkills().stream().map(skill->new SkillsResponseDto(skill.getId(), skill.getName()))
                        .collect(Collectors.toSet()))
                .build();
    }
}
