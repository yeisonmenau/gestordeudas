package com.yeisonmenau.gestordeudas.domain.deuda.model;

import com.yeisonmenau.gestordeudas.domain.persona.model.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Deuda {
    private Long deudaId;
    private Persona persona;
    private double deudaValor;
    private LocalDate deudaFecha;
    private LocalDate deudaFechaMaximaPago;
    private Boolean pagado;
}
