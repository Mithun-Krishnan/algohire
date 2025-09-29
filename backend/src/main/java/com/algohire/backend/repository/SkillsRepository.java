package com.algohire.backend.repository;

import com.algohire.backend.model.Skills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SkillsRepository extends JpaRepository<Skills, UUID> {

}
