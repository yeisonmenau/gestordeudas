package com.yeisonmenau.gestordeudas.infrastructure.dto.request;

import lombok.Data;

import java.time.LocalDate;
@Data
public class PersonaRequestDTO {
    private String personaCedula;
    private String personaNombre;
    private LocalDate personaFechaNacimiento;
}
