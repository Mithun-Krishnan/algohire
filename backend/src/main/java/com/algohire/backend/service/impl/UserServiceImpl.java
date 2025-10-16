package com.algohire.backend.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.algohire.backend.dto.request.CompanyRequstDto;
import com.algohire.backend.dto.request.ProfileUpdateRequestDto;
import com.algohire.backend.dto.response.*;
import com.algohire.backend.exception.UserNotFoundException;
import com.algohire.backend.mapper.UserMapper;
import com.algohire.backend.model.Company;
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
        users.setSkills(null);
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
                .location(users.getCity())
                .email(users.getEmail())       // email
                .role(users.getRole().getRole().name())  // assuming role is Enum
                .phone(users.getPhone())       // phone number
                .skills(users.getSkills())
                .location(users.getCity())
                .experince(users.getExperince())
                .company(users.getCompany() !=null
                        ? UserMapper.toCompanyDto(users.getCompany())
                        : null).build();


    }

    @Override
    public CompanyResponseDto creteCompany(CompanyRequstDto requst) {

         UUID userid=authserviceImpal.getCurrentUserId();
         Users users=userRepository.findById(userid).orElseThrow(
                 ()->new UserNotFoundException("no user fond")
         );
        Company company=Company.builder()
                .about(requst.getAbout())
                .createdBy(users)
                .name(requst.getName())
                .email(requst.getEmail())
                .build();

        Company savedCompany = companyRepository.save(company);

        return CompanyResponseDto.builder()
                .id(savedCompany.getId())
                .name(savedCompany.getName())
                .email(savedCompany.getEmail())
                .build();
    }

    @Override
    public ProfileResponseDto updateProfile(ProfileUpdateRequestDto request) {


         UUID userId=authserviceImpal.getCurrentUserId();
         Users user=userRepository.findById(userId).orElseThrow(
                 ()->new UsernameNotFoundException("no user found")
         );

        if (request.getFullName() != null) {
            user.setUsername(request.getFullName());
        }

        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }

        if (request.getSkills() != null && !request.getSkills().isEmpty()) {
            user.setSkills(request.getSkills());
        }

        if (request.getPhoneNumber() != null) {
            user.setPhone(request.getPhoneNumber());
        }

        if (request.getLocation() != null) {
            user.setCity(request.getLocation());
        }

        if (request.getExperience() != null) {
            user.setExperince(request.getExperience());
        }

        if (request.getResumeUrl() != null) {
            user.setResumeUrl(request.getResumeUrl());
        }

        Users updatedUser = userRepository.save(user);

        return ProfileResponseDto.builder()
                .id(updatedUser.getId())
                .userName(updatedUser.getUsername())
                .email(updatedUser.getEmail())
                .skills(updatedUser.getSkills())
                .phone(updatedUser.getPhone())
                .location(updatedUser.getCity())
                .experince(updatedUser.getExperince())
                .resumeUrl(updatedUser.getResumeUrl())
                .build();
    }


}
