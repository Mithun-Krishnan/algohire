package com.algohire.backend.service;

import com.algohire.backend.dto.request.CompanyRequstDto;
import com.algohire.backend.dto.response.CompanyExistResponseDto;


public interface CompanyService {
    CompanyExistResponseDto checkCompany(String name, String email);

    CompanyExistResponseDto createCompany(CompanyRequstDto requst);
}
