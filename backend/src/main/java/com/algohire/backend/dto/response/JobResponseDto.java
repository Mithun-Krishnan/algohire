package com.algohire.backend.dto.response;

import lombok.Data;

@Data
public class JobResponseDto {
    private Long jobId;
    private String title;
    private String description;
    private String location;
    private String category;
    private String createdBy;
}
