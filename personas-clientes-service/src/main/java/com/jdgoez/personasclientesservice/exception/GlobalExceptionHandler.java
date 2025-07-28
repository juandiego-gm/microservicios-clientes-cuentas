package com.jdgoez.personasclientesservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IdentificacionDuplicadaException.class)
    public ResponseEntity<Object> manejarDuplicado(
            IdentificacionDuplicadaException ex,
            WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("mensaje", ex.getMessage());
        body.put("fecha", LocalDateTime.now());
        body.put("ruta", request.getDescription(false));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> manejarErroresDeValidacion(
            MethodArgumentNotValidException ex,
            WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("mensaje", "Error de validación");
        body.put("fecha", LocalDateTime.now());
        body.put("ruta", request.getDescription(false));

        // Listar errores de campo
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
        );
        body.put("errores", errores);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ClienteNoEncontradoException.class)
    public ResponseEntity<Object> manejarClienteNoEncontrado(
            ClienteNoEncontradoException ex,
            WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("mensaje", ex.getMessage());
        body.put("fecha", LocalDateTime.now());
        body.put("ruta", request.getDescription(false));

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> manejarErroresGenerales(
            Exception ex,
            WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("mensaje", "Error interno: " + ex.getMessage());
        body.put("fecha", LocalDateTime.now());
        body.put("ruta", request.getDescription(false));

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}