package com.ealx.padroesprojetos.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> generalError(Exception e, HttpServletRequest request) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", Instant.now());
        error.put("status", 400);
        error.put("error", "Invalid Request");
        error.put("message", e.getMessage());
        error.put("path", request.getRequestURI());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", Instant.now());
        error.put("status", 404);
        error.put("error", "Not Found");
        error.put("message", e.getMessage());
        error.put("path", request.getRequestURI());
        // Aqui usamos status(404) para ser um erro de "Não Encontrado"
        return ResponseEntity.status(404).body(error);
    }
}