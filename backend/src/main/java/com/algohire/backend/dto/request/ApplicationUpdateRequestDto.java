package com.algohire.backend.dto.request;

import com.algohire.backend.enums.ApplicationStatus;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ApplicationUpdateRequestDto {
    private UUID applicationId;
    private ApplicationStatus applicationStatus;

}
