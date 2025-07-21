package com.yeisonmenau.gestordeudas.infrastructure.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
@Data
public class PersonaRequestDTO {
    @NotNull(message = "La cédula no puede ser nula")
    @NotBlank(message = "La cédula no puede estar vacía")
    private String personaCedula;
    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "El nombre no puede estar vacío")
    private String personaNombre;
    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    private LocalDate personaFechaNacimiento;
}
