package com.yeisonmenau.gestordeudas.application.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class GlobalExceptionHandler {
    // Validaciones del DTO con @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.put("mensaje", error.getDefaultMessage());
            break; // solo muestra el primer error
        }
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }

    // Cédula duplicada (clave única)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleUniqueConstraint(DataIntegrityViolationException ex) {
        Throwable cause = ex.getCause();
        if (cause instanceof ConstraintViolationException && cause.getMessage().contains("persona_cedula")) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "No se permite cédula repetida");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        // Otro tipo de error de integridad
        Map<String, String> error = new HashMap<>();
        error.put("mensaje", "Violación de integridad de datos");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Manejo general de otras excepciones
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneral(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("mensaje", "Error interno: " + ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
