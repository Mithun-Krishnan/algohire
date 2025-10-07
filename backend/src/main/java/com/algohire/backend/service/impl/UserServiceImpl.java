package com.algohire.backend.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.algohire.backend.dto.response.*;
import com.algohire.backend.mapper.UserMapper;
import com.algohire.backend.model.Skills;
import com.algohire.backend.repository.SkillsRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.algohire.backend.model.Users;
import com.algohire.backend.repository.CompanyRepository;
import com.algohire.backend.repository.RoleRepository;
import com.algohire.backend.repository.UserRepository;
import com.algohire.backend.service.UserService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CompanyRepository companyRepository;
    private final AuthserviceImpal authserviceImpal;
    private final SkillsRepository skillsRepository;







     @Override
    public UserResponse getUserById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
    }

    @Override
    public List<UserResponse> getAllUsers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
    }

    @Override
    public UserResponseSkills updateSkills(Set<UUID> skillIds) {
//    public UserResponse updateSkills(Set<UUID> skillIds) {
        UUID userId=authserviceImpal.getCurrentUserId();
        Users users=userRepository.findById(userId).orElseThrow(()->
                new UsernameNotFoundException("//User with this id not found -->update skill user service impal"));

        Set<Skills> skillsSet=new HashSet<>(skillsRepository.findAllById(skillIds));
        users.setSkills(skillsSet);
        userRepository.save(users);

        return UserMapper.toSkillsResponce(users);


    }

    @Override
    public List<SkillsResponseDto> getAllSkills() {
        return skillsRepository.findAll().stream().map(
                skills -> new SkillsResponseDto(skills.getId(),skills.getName())

        ).toList();
    }

    @Override
    public ProfileResponseDto getProfile() {
        UUID userId=authserviceImpal.getCurrentUserId();
        Users users=userRepository.findById(userId).orElseThrow(
                ()-> new UsernameNotFoundException("//user with id not fount"+userId)
        );

        return ProfileResponseDto.builder()
                .id(users.getId())  // UUID of the user
                .userName(users.getUsername()) // username
                .email(users.getEmail())       // email
                .role(users.getRole().getRole().name())  // assuming role is Enum
                .phone(users.getPhone())       // phone number
                .profilePicUrl(users.getProfilePicUrl()) // profile picture link
                .resumeUrl(users.getResumeUrl())         // resume file link
                .description(users.getDescription())     // user bio/summary
                .isActive(users.isActive())
                .skills(users.getSkills() !=null
                ? users.getSkills().stream().map(Skills::getName)
                        .collect(Collectors.toSet()) : Collections.EMPTY_SET)// active flag
                .lastLogin(users.getLastLogin())
                .company(users.getCompany() !=null
                        ? UserMapper.toCompanyDto(users.getCompany())
                        : null).build();


    }


}
