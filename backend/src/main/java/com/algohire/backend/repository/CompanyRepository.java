package com.algohire.backend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algohire.backend.model.Company;

public interface CompanyRepository extends JpaRepository<Company, UUID>{
     Optional<Company> findByNameIgnoreCaseAndEmailIgnoreCase(String name, String email);
}
