package com.algohire.backend.dto.response;

import com.algohire.backend.enums.ApplicationStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ApplicationResponseDto {
    private UUID id;
    private UUID userId;
    private UUID jobId;
    private ApplicationStatus applicationStatus;
    private LocalDateTime appliedAt;
    private boolean isShortListed;
    private String coverLetter;
}
