package com.algohire.backend.dto.request;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.algohire.backend.enums.JobStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JobRequstDto {

    @NotBlank(message = "Job title is required")
    private String title;

    @NotBlank(message = "Job description is required")
    private String description;

    @NotBlank(message = "City is required")
    private String city;

//    @NotBlank(message = "State is required")
//    private String state;

    @NotBlank(message = "Salary is required")
    private String salary;

    private Set<String> skills; // optional

    private int experience; // optional



//    @NotNull(message = "Deadline is required")
//    private LocalDateTime deadLine;


//    private String state;
//
//
//    private String address;


//    @NotNull(message = "Deadline is required")
//    @Future(message = "Deadline must be in the future")
//    private LocalDateTime deadline;

//    private JobStatus jobStatus;

//    @NotNull
//    private UUID userId;

//    @NotNull(message = "Job category ID is required")
//    private UUID jobCategoryId;
}
