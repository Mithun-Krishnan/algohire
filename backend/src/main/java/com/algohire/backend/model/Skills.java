package com.algohire.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@Table(name = "skills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skills {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;
}
