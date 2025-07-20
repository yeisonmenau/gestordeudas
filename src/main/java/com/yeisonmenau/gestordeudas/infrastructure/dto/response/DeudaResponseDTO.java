package com.yeisonmenau.gestordeudas.infrastructure.dto.response;

import com.yeisonmenau.gestordeudas.domain.persona.model.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeudaResponseDTO {
    private Long deudaId;
    private String nombrePersona;
    private String cedulaPersona;
    private double deudaValor;
    private LocalDate deudaFecha;
    private LocalDate deudaFechaMaximaPago;
    private Boolean pagado = false;
}
