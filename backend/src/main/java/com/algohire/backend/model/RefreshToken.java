package com.algohire.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RefreshToken {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @OneToOne
    private Users users;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

}
