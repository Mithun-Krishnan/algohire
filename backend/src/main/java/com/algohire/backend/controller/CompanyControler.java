package com.algohire.backend.controller;

import com.algohire.backend.dto.response.CompanyResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.algohire.backend.dto.request.CompanyCheckRequstDto;
import com.algohire.backend.dto.request.CompanyRequstDto;
import com.algohire.backend.dto.response.CompanyExistResponseDto;
import com.algohire.backend.service.CompanyService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api/company")
public class CompanyControler {

    @Autowired
    private CompanyService companyService;
    
    @PostMapping("/check/{name}")
    public ResponseEntity<CompanyExistResponseDto> checkCompany(@PathVariable String name){
        
        CompanyExistResponseDto response=companyService.checkCompany(name);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/{query}")
    public List<CompanyResponseDto> serchCompany(@PathVariable String query){
        return  companyService.serchCompany(query);
    }

    @PostMapping("/create")
    public ResponseEntity<CompanyExistResponseDto> creteCompany(@Valid @RequestBody CompanyRequstDto requst){
        CompanyExistResponseDto response=companyService.createCompany(requst);
        return ResponseEntity.ok(response);
    }
}
