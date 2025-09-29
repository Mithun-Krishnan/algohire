package com.algohire.backend.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.algohire.backend.mapper.UserMapper;
import com.algohire.backend.model.Skills;
import com.algohire.backend.repository.SkillsRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.algohire.backend.dto.request.RecruiterExistCompanyRequstDto;
import com.algohire.backend.dto.request.RecruiterNewCompanyRequestDto;
import com.algohire.backend.dto.request.UserRequestDto;
import com.algohire.backend.dto.response.RecruiterResponseDto;
import com.algohire.backend.dto.response.UserResponse;
import com.algohire.backend.enums.RoleType;
import com.algohire.backend.model.Company;
import com.algohire.backend.model.Role;
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
    public RecruiterResponseDto updateSkills(Set<UUID> skillIds) {
//    public UserResponse updateSkills(Set<UUID> skillIds) {
//        UUID userId=authserviceImpal.getCurrentUserId();
//        Users users=userRepository.findById(userId).orElseThrow(()->
//                new UsernameNotFoundException("//User with this id not found -->update skill user service impal"));
//
//        Set<Skills> skillsSet=new HashSet<>(skillsRepository.findAllById(skillIds));
//        users.setSkills(skillsSet);
//        userRepository.save(users);
//
//        return UserMapper.toResponse(users);

        return RecruiterResponseDto.builder()
                .email("").build();
    }


}
