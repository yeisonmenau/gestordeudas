package com.yeisonmenau.gestordeudas.application.service;

import com.yeisonmenau.gestordeudas.application.usecase.PersonaUseCase;
import com.yeisonmenau.gestordeudas.domain.persona.out.PersonaRepository;
import com.yeisonmenau.gestordeudas.domain.persona.model.Persona;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class PersonaService implements PersonaUseCase {

    private final PersonaRepository personaRepository;

    @Override
    public Persona crearPersona(Persona persona) {
        return personaRepository.crearPersona(persona);
    }

    @Override
    public List<Persona> mostrarPersonas() {
        return personaRepository.mostrarPersonas();
    }

    @Override
    public Persona actualizarPersona(Long idPersona, Persona persona) {
        return personaRepository.actualizarPersona(idPersona, persona);
    }

    @Override
    public String eliminarPersona(Long idPersona) {
        return personaRepository.eliminarPersona(idPersona);
    }
}
