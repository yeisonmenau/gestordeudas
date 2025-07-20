package com.yeisonmenau.gestordeudas.infrastructure.mapper;
import com.yeisonmenau.gestordeudas.domain.persona.model.Persona;
import com.yeisonmenau.gestordeudas.infrastructure.dto.request.PersonaRequestDTO;
import com.yeisonmenau.gestordeudas.infrastructure.dto.response.PersonaResponseDTO;
import com.yeisonmenau.gestordeudas.infrastructure.entity.PersonaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class PersonaMapper {
    
    public PersonaEntity domainToEntity (Persona personaDominio){
        return new PersonaEntity(
                null,
                personaDominio.getPersonaCedula(),
                personaDominio.getPersonaNombre(),
                personaDominio.getPersonaFechaNacimiento());
    }
    public Persona entityToDomain (PersonaEntity personaEntidad){
        return new Persona(
                null,
                personaEntidad.getPersonaCedula(),
                personaEntidad.getPersonaNombre(),
                personaEntidad.getPersonaFechaNacimiento());
    }

    public PersonaEntity personaRequestToEntity (PersonaRequestDTO personaRequest){
        return new PersonaEntity(
                null,
                personaRequest.getPersonaCedula(),
                personaRequest.getPersonaNombre(),
                personaRequest.getPersonaFechaNacimiento());
    }

    public PersonaResponseDTO entityToPersonaResponse (PersonaEntity personaEntidad){
        return new PersonaResponseDTO(
                personaEntidad.getPersonaCedula(),
                personaEntidad.getPersonaNombre(),
                Period.between(personaEntidad.getPersonaFechaNacimiento(), LocalDate.now()).getYears());
    }
    public Persona requestToDomain (PersonaRequestDTO personaRequest){
        return new Persona(
                null,
                personaRequest.getPersonaCedula(),
                personaRequest.getPersonaNombre(),
                personaRequest.getPersonaFechaNacimiento());
    }
    public PersonaResponseDTO domainToPersonaResponse (Persona persona){
        return new PersonaResponseDTO(
                persona.getPersonaCedula(),
                persona.getPersonaNombre(),
                Period.between(persona.getPersonaFechaNacimiento(), LocalDate.now()).getYears());
    }

}
