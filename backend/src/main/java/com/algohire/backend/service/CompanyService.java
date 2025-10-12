package com.algohire.backend.service;

import com.algohire.backend.dto.request.CompanyRequstDto;
import com.algohire.backend.dto.response.CompanyExistResponseDto;
import com.algohire.backend.dto.response.CompanyResponseDto;

import java.util.List;


public interface CompanyService {
    CompanyExistResponseDto checkCompany(String name);
    List<CompanyResponseDto> serchCompany(String name);

    CompanyExistResponseDto createCompany(CompanyRequstDto requst);
}
