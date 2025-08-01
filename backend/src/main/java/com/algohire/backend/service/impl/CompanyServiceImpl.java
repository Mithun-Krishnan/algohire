package com.algohire.backend.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algohire.backend.dto.request.CompanyRequstDto;
import com.algohire.backend.dto.response.CompanyResponseDto;
import com.algohire.backend.model.Company;
import com.algohire.backend.repository.CompanyRepository;
import com.algohire.backend.service.CompanyService;


@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;


    @Override
    public CompanyResponseDto checkCompany(String name,String email) {
       Optional<Company> optionalCompany=companyRepository.findByNameIgnoreCaseAndEmailIgnoreCase(name,email);
        if(optionalCompany.isPresent()){
            Company company=optionalCompany.get();
            return CompanyResponseDto.builder()
                    .exist(true)
                    .id(company.getId())
                    .message("company exist")
                    .build();
       
                }

        return CompanyResponseDto.builder()
        .exist(false)
        .id(null)
        .message("company dosent exist")
        .build();
    }


    @Override
    public CompanyResponseDto createCompany(CompanyRequstDto requst) {

       Optional<Company> exist=companyRepository.findByNameIgnoreCaseAndEmailIgnoreCase(requst.getName(), requst.getEmail());

       if(exist.isPresent()){

        Company company=exist.get();
        return CompanyResponseDto.builder()
                .exist(true)
                .id(company.getId())
                .message("company exist")
                .build();
       }

       Company company=Company.builder()
                .name(requst.getName())
                .email(requst.getEmail())
                .address(requst.getAddress())
                .phone(requst.getPhone())
                .website(requst.getWebsite())
                .about(requst.getAbout())
                .logo(requst.getLogoUrl())
                .size(requst.getCompanySize())
                .build();

       Company saved=companyRepository.save(company);

       return CompanyResponseDto.builder()
            .exist(true)
            .id(saved.getId())
            .message("creted susscfully")
            .build();
    }
    
    
}
