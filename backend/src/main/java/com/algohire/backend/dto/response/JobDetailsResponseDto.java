package com.algohire.backend.dto.response;

import com.algohire.backend.enums.JobStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class JobDetailsResponseDto {
    private UUID jobId;
    private String title;
    private String description;
    private String location;
    private String salary;
    private LocalDateTime deadline;
    private String jobstatus;
    private String category;
    private String createdByName;

}
