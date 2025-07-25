package com.yeisonmenau.gestordeudas.infrastructure.adapter.implement;

import com.yeisonmenau.gestordeudas.application.exception.PersonaNoEncontradaException;
import com.yeisonmenau.gestordeudas.domain.persona.out.PersonaRepository;
import com.yeisonmenau.gestordeudas.domain.persona.model.Persona;
import com.yeisonmenau.gestordeudas.infrastructure.adapter.PersonaJpaRepository;
import com.yeisonmenau.gestordeudas.infrastructure.entity.PersonaEntity;
import com.yeisonmenau.gestordeudas.infrastructure.mapper.PersonaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@RequiredArgsConstructor
public class PersonaAdapter implements PersonaRepository {

    private final PersonaJpaRepository personaJpaRepository;
    private final PersonaMapper mapper;

    @Override
    public Persona crearPersona(Persona persona) {
        PersonaEntity personaEntity = mapper.domainToEntity(persona);
        PersonaEntity personaGuardada = personaJpaRepository.save(personaEntity);
        return mapper.entityToDomain(personaGuardada);
    }

    @Override
    public List<Persona> mostrarPersonas() {
        List<PersonaEntity> personas = personaJpaRepository.findAll();
        return personas.stream()
                .map(mapper::entityToDomain)
                .toList();
    }

    @Override
    public Persona actualizarPersona(Long idPersona, Persona persona) {
        PersonaEntity existente = personaJpaRepository.findById(idPersona)
                .orElseThrow(() -> new PersonaNoEncontradaException(idPersona));
        existente.setPersonaCedula(persona.getPersonaCedula());
        existente.setPersonaNombre(persona.getPersonaNombre());
        existente.setPersonaFechaNacimiento(persona.getPersonaFechaNacimiento());
        PersonaEntity personaActualizada = personaJpaRepository.save(existente);
        return mapper.entityToDomain(personaActualizada);
    }

    @Override
    public String eliminarPersona(Long idPersona) {
        PersonaEntity existente = personaJpaRepository.findById(idPersona)
                .orElseThrow(() -> new PersonaNoEncontradaException(idPersona));
        personaJpaRepository.delete(existente);
        return "Persona con ID " + idPersona + " eliminada correctamente.";
    }
}
