package com.yeisonmenau.gestordeudas.infrastructure.dto.request;

import com.yeisonmenau.gestordeudas.domain.persona.model.Persona;
import lombok.Data;

import java.time.LocalDate;
@Data
public class DeudaRequestDTO {
    private Persona persona;
    private double deudaValor;
    private LocalDate deudaFecha;
    private LocalDate deudaFechaMaximaPago;
}
