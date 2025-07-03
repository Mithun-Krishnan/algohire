package com.algohire.backend.service.impl;

import java.util.List;
import java.util.UUID;

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

    @Override
    public UserResponse registerCandidate(UserRequestDto request) {
       if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already registered");
       }

       Users user =new Users();
       user.setUsername(request.getName());
       user.setEmail(request.getEmail());
       user.setPassword(passwordEncoder.encode(request.getPassword()));
       user.setPhone(request.getPhone());
       
       Role role=roleRepository.findByRole(RoleType.CANDIDATE)
       .orElseThrow(()->new RuntimeException("invalid id"));
       user.setRole(role);

    //    Company company=companyRepository.findById(request.getCompanyId())
    //    .orElseThrow(()-> new RuntimeException("invalid company id"));
    //    user.setCompany(company);


       Users savedUser=userRepository.save(user);

       return UserResponse.builder()
            .id(savedUser.getId())
            .userName(savedUser.getUsername())
            .email(savedUser.getEmail())
            .role(savedUser.getRole().getRole().name()) 
            .phone(savedUser.getPhone())
            .build();
                
    }

   

    

    @Override
    public RecruiterResponseDto recruiterNewCompanyRegister(RecruiterNewCompanyRequestDto requst) {
       if(userRepository.existsByEmail(requst.getEmail())){
        throw new RuntimeException("email alredy exist");
       }



        Role role=roleRepository.findByRole(RoleType.RECRUITER)
       .orElseThrow(()-> new RuntimeException("invalid id"));

       Users user=Users.builder()
            .username(requst.getName())
            .email(requst.getEmail())
            .phone(requst.getPhone())
            .password(passwordEncoder.encode(requst.getPassword()))
            .role(role)
            .build();
            

        Users savedRecruiter=userRepository.save(user);


      

       Company company=companyRepository.findByNameIgnoreCaseAndEmailIgnoreCase(requst.getCompanyName(), requst.getCompanyEmail())
                .orElseGet(()->{
                Company newCompany=Company.builder()
                .name(requst.getCompanyName())
                .email(requst.getCompanyEmail())
                .address(requst.getCompanyAddress())
                .phone(requst.getCompanyPhone())
                .size(requst.getCompanySize())
                .about(requst.getAbout())
                .website(requst.getWebsite())
                .createdBy(savedRecruiter)
                .logo(requst.getLogoUrl())
                .build();

                return companyRepository.save(newCompany);
                });

            
            savedRecruiter.setCompany(company);
            userRepository.save(savedRecruiter);

        

        return RecruiterResponseDto.builder()
                .id(savedRecruiter.getId())
                .userName(savedRecruiter.getUsername())
                .email(savedRecruiter.getEmail())
                .role(savedRecruiter.getRole().getRole().name())
                .companyName(savedRecruiter.getCompany().getName())
                .phone(savedRecruiter.getPhone())
                .build();

    
    }





     @Override
    public RecruiterResponseDto recuiterExistingCompany(RecruiterExistCompanyRequstDto requst) {

        if(userRepository.existsByEmail(requst.getEmail())){
            throw new RuntimeException("email allredy exist");
        }

        Role role=roleRepository.findByRole(RoleType.RECRUITER)
        .orElseThrow(()->new RuntimeException("invalid id"));

        Company company=companyRepository.findById(requst.getCompanyId())
            .orElseThrow(()-> new RuntimeException("invalid company id"));

        Users user=Users.builder()
                .username(requst.getName())
                .email(requst.getEmail())
                .company(company)
                .role(role)
                .phone(requst.getPhone())
                .password(passwordEncoder.encode(requst.getPassword()))
                .build();

        Users savedRecruiter=userRepository.save(user);


        return RecruiterResponseDto.builder()
                .userName(savedRecruiter.getUsername())
                .email(savedRecruiter.getEmail())
                .role(savedRecruiter.getRole().getRole().name())
                .companyName(savedRecruiter.getCompany().getName())
                .phone(savedRecruiter.getPhone())
                .build();

    }





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



   
    
}
