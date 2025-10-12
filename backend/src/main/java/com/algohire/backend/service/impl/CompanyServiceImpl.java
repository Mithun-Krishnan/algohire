package com.algohire.backend.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.algohire.backend.dto.response.CompanyResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algohire.backend.dto.request.CompanyRequstDto;
import com.algohire.backend.dto.response.CompanyExistResponseDto;
import com.algohire.backend.model.Company;
import com.algohire.backend.repository.CompanyRepository;
import com.algohire.backend.service.CompanyService;


@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;


    @Override
    public CompanyExistResponseDto checkCompany(String name) {
       Optional<Company> optionalCompany=companyRepository.findByNameIgnoreCase(name);
        if(optionalCompany.isPresent()){
            Company company=optionalCompany.get();
            return CompanyExistResponseDto.builder()
                    .exist(true)
                    .id(company.getId())
                    .name(company.getName())
                    .email(company.getEmail())
                    .build();
       
                }

        return CompanyExistResponseDto.builder()
        .exist(false)
        .id(null)
        .name(null).email(null)
        .build();
    }

    @Override
    public List<CompanyResponseDto> serchCompany(String name) {
        List<Company> companies=companyRepository.findAllByNameContainingIgnoreCase(name);

        return companies.stream()
                .map(company -> CompanyResponseDto.builder()
                        .id(company.getId())
                        .name(company.getName())
                        .email(company.getEmail())

                                .build()
                        ).collect(Collectors.toList());

    }


    @Override
    public CompanyExistResponseDto createCompany(CompanyRequstDto requst) {

       Optional<Company> exist=companyRepository.findByNameIgnoreCase(requst.getName());

       if(exist.isPresent()){

        Company company=exist.get();
        return CompanyExistResponseDto.builder()
                .exist(true)
                .id(company.getId())
                .name(company.getName())

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

       return CompanyExistResponseDto.builder()
            .exist(true)
            .id(saved.getId())
            .name("creted susscfully")
            .build();
    }
    
    
}
