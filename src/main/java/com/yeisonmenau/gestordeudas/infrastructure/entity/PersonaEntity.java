package com.yeisonmenau.gestordeudas.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "personas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persona_id")
    private Long personaId;
    @Column(name = "persona_cedula")
    private String personaCedula;
    @Column(name = "persona_nombre")
    private String personaNombre;
    @Column(name = "persona_fecha_nacimiento")
    private LocalDate personaFechaNacimiento;
}
