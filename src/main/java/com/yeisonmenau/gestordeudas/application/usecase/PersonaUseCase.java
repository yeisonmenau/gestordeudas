package com.yeisonmenau.gestordeudas.application.usecase;

import com.yeisonmenau.gestordeudas.domain.persona.model.Persona;

import java.util.List;

public interface PersonaUseCase {
    Persona crearPersona (Persona persona);
    List<Persona> mostrarPersonas ();
    Persona actualizarPersona (Long idPersona, Persona persona);
    void eliminarPersona (Long idPersona);
}
