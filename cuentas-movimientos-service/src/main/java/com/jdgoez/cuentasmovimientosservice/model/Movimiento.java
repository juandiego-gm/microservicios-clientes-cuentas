package com.jdgoez.cuentasmovimientosservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha = LocalDateTime.now();

    @NotBlank(message = "El tipo de movimiento es obligatorio")
    private String tipoMovimiento; // "DEPOSITO" o "RETIRO"

    @NotNull(message = "El valor es obligatorio")
    private Double valor;

    private Double saldo;

    @NotNull(message = "El ID de la cuenta es obligatorio")
    private Long cuentaId;
}