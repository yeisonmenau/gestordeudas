package com.yeisonmenau.gestordeudas.infrastructure.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaResponseDTO {
    private Long personaId;
    private String personaCedula;
    private String personaNombre;
    private Integer personaEdad;
}
