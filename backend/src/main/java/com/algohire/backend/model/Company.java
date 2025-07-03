package com.algohire.backend.model;

import java.util.UUID;

import com.algohire.backend.model.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company extends Auditable {

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "name", nullable = false)
    private String name;


    @Column(name="email")
    private String email;

    @Size(max = 255)
    @Column(name = "website")
    private String website;

    @Size(max = 255)
    @Column(name = "address")
    private String address;

    @Size(max = 20)
    @Column(name = "phone")
    private String phone;

    @Size(max = 1000)
    @Column(name = "about")
    private String about;

    @Size(max = 50)
    @Column(name = "company_size")
    private String size;

    @Size(max = 255)
    @Column(name = "logo")
    private String logo;

    @ManyToOne(fetch = FetchType.LAZY) // add, optional = false
    @JoinColumn(name = "created_by")  //add this nullable = false
    private Users createdBy;

    // @CreationTimestamp
    // @Column(name = "created_at", updatable = false) // add nullable = false
    // private LocalDateTime createdAt;

    // @UpdateTimestamp
    // @Column(name = "updated_at")  //add , nullable = false
    // private LocalDateTime updatedAt;
}
