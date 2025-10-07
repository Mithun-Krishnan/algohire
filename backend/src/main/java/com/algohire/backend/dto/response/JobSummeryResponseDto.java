package com.algohire.backend.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class JobSummeryResponseDto {
    private UUID jobId;
    private String title;
    private String description;
    private String location;
    private String category;
}
