package com.algohire.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algohire.backend.model.Message;

public interface MessageRepository extends JpaRepository<Message, UUID>{
    
}
