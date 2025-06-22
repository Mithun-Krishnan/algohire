package com.algohire.backend.model;


import java.util.UUID;

import com.algohire.backend.model.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="employment_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class EmploymentType extends Auditable {
    @Id
    @GeneratedValue
    @Column(columnDefinition="UUID")
    private UUID id;

    @Column(name="type")
    private String type;

    
}
