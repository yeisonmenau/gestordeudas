package com.yeisonmenau.gestordeudas.domain.persona.model;

import lombok.*;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    private Long personaId;
    private String personaCedula;
    private String personaNombre;
    private LocalDate personaFechaNacimiento;
}
