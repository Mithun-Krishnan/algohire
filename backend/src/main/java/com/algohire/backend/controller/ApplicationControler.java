package com.algohire.backend.controller;

import com.algohire.backend.dto.request.ApplicationRequestDto;
import com.algohire.backend.dto.response.ApplicationResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application")
public class ApplicationControler {

    @PostMapping("/create")
    public ApplicationResponseDto createApplication(ApplicationRequestDto request){

    }

}
