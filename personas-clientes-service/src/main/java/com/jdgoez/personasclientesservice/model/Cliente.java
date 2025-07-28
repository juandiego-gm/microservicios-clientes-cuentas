package com.jdgoez.personasclientesservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Persona {

    private String password;
    private Boolean estado;
}
