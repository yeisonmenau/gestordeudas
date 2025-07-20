package com.yeisonmenau.gestordeudas.infrastructure.adapter.implement;

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
