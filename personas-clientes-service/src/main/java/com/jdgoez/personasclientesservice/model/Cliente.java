package com.jdgoez.personasclientesservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Persona {

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;
}
