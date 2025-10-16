package com.algohire.backend.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecruiterStatsResponse {
    private long jobsPosted;
    private long applications;
    private long shortlisted;
}
