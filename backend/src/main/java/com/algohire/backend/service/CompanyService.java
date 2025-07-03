package com.algohire.backend.service;

import com.algohire.backend.dto.request.CompanyRequstDto;
import com.algohire.backend.dto.response.CompanyResponseDto;


public interface CompanyService {
    CompanyResponseDto checkCompany(String name,String email);

    CompanyResponseDto createCompany(CompanyRequstDto requst);
}
