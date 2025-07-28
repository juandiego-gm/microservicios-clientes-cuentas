package com.jdgoez.cuentasmovimientosservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CuentaNoEncontradaException.class)
    public ResponseEntity<Object> handleCuentaNoEncontrada(CuentaNoEncontradaException ex, WebRequest request) {
        return errorResponse(ex.getMessage(), request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<Object> handleSaldoInsuficiente(SaldoInsuficienteException ex, WebRequest request) {
        return errorResponse(ex.getMessage(), request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MovimientoInvalidoException.class)
    public ResponseEntity<Object> handleMovimientoInvalido(MovimientoInvalidoException ex, WebRequest request) {
        return errorResponse(ex.getMessage(), request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidacion(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("mensaje", "Error de validación");
        body.put("fecha", LocalDateTime.now());
        body.put("ruta", request.getDescription(false));

        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
        );
        body.put("errores", errores);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> errorResponse(String mensaje, WebRequest request, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("mensaje", mensaje);
        body.put("fecha", LocalDateTime.now());
        body.put("ruta", request.getDescription(false));
        return new ResponseEntity<>(body, status);
    }
}