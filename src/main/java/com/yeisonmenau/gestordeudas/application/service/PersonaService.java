package com.yeisonmenau.gestordeudas.application.service;

import com.yeisonmenau.gestordeudas.application.exception.PersonaException;
import com.yeisonmenau.gestordeudas.application.usecase.PersonaUseCase;
import com.yeisonmenau.gestordeudas.domain.persona.out.PersonaRepository;
import com.yeisonmenau.gestordeudas.domain.persona.model.Persona;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class PersonaService implements PersonaUseCase {

    private final PersonaRepository personaRepository;
    private final PersonaException personaException;

    @Override
    public Persona crearPersona(Persona persona) {
        personaException.validarDatos(persona);
        return personaRepository.crearPersona(persona);
    }

    @Override
    public List<Persona> mostrarPersonas() {
        return List.of();
    }

    @Override
    public Persona actualizarPersona(Long idPersona, Persona persona) {
        return null;
    }

    @Override
    public void eliminarPersona(Long idPersona) {

    }
}
