package com.algohire.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algohire.backend.dto.request.CompanyCheckRequstDto;
import com.algohire.backend.dto.request.CompanyRequstDto;
import com.algohire.backend.dto.response.CompanyResponseDto;
import com.algohire.backend.service.CompanyService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("api/company")
public class CompanyControler {

    @Autowired
    private CompanyService companyService;
    
    @PostMapping("/check")
    public ResponseEntity<CompanyResponseDto> checkCompany(@Valid @RequestBody CompanyCheckRequstDto requst){
        
        CompanyResponseDto response=companyService.checkCompany(requst.getName(), requst.getEmail());

        return ResponseEntity.ok(response);
    } 

    @PostMapping("/create")
    public ResponseEntity<CompanyResponseDto> creteCompany(@Valid @RequestBody CompanyRequstDto requst){
        CompanyResponseDto response=companyService.createCompany(requst);
        return ResponseEntity.ok(response);
    }
}
