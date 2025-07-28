package com.jdgoez.personasclientesservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    private String genero;
    private Integer edad;

    @NotBlank(message = "La identificación es obligatoria")
    private String identificacion;
    private String direccion;
    private String telefono;
}

