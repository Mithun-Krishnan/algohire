package com.algohire.backend.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRunTimeException(RuntimeException ex){
        log.error("runtimeeror{}",ex.getMessage(),ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
        log.error("Unhandled Exception: {}", ex.getMessage(), ex);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", ex.getMessage() != null ? ex.getMessage() : "Internal Server Error");
        response.put("errorType", ex.getClass().getSimpleName());
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?>handleUserNotFoundException(UserNotFoundException ex){
        log.error("user not found: {}",ex.getMessage(),ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The User Not Found In The User name");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentials(BadCredentialsException ex) {
        log.error("Invalid email or password");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }

    @ExceptionHandler(AlreadyAppliedException.class)
    public ResponseEntity<Map<String,Object>> allpredyApplied(AlreadyAppliedException ex){
        log.error("alredy aplied"+ex.getMessage());

        Map<String,Object> response=new HashMap<>();
        response.put("status","eror");
        response.put("message",ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);

    }
}
