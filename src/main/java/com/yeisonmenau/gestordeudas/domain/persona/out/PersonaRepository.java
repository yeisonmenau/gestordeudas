package com.yeisonmenau.gestordeudas.domain.persona.out;

import com.yeisonmenau.gestordeudas.domain.persona.model.Persona;
import com.yeisonmenau.gestordeudas.infrastructure.dto.request.PersonaRequestDTO;
import com.yeisonmenau.gestordeudas.infrastructure.dto.response.PersonaResponseDTO;

import java.util.List;

public interface PersonaRepository {
    Persona crearPersona (Persona persona);
    List<Persona> mostrarPersonas ();
    Persona actualizarPersona (Long idPersona, Persona persona);
    void eliminarPersona (Long idPersona);
}
