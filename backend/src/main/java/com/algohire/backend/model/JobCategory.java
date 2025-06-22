package com.algohire.backend.model;


import java.util.UUID;

import com.algohire.backend.model.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="job_category")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class JobCategory extends Auditable{
    @Id
    @GeneratedValue
    @Column(columnDefinition="UUID",updatable=false,nullable=false)
    private UUID id;

    @Size(max=50)
    @NotBlank
    @Column(name="job_category" ,nullable=false)
    private String category;


   
}
