package com.olujobii.shorty.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, Object> errors = new HashMap<>();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ex.getBindingResult().getFieldErrors().forEach(error -> errors
                .put(error.getField(), error.getDefaultMessage()));

        errors.put("status", status);
        errors.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(status).body(errors);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex){
        Map<String, Object> errors = new HashMap<>();
        HttpStatus status = HttpStatus.NOT_FOUND;

        errors.put("message",ex.getMessage());
        errors.put("status", status);
        errors.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(status).body(errors);
    }
}
