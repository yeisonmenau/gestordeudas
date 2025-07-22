package com.yeisonmenau.gestordeudas.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Table(name = "deudas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeudaEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "deuda_id")
    private Long deudaId;
    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false)
    private PersonaEntity persona;
    @Column (name = "deuda_valor")
    private double deudaValor;
    @Column (name = "deuda_fecha")
    private LocalDate deudaFecha;
    @Column (name = "deuda_fecha_maxima_pago")
    private LocalDate deudaFechaMaximaPago;
    private Boolean pagado;
}
