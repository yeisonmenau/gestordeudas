package com.yeisonmenau.gestordeudas.infrastructure.mapper;

import com.yeisonmenau.gestordeudas.application.exception.PersonaNoEncontradaException;
import com.yeisonmenau.gestordeudas.domain.deuda.model.Deuda;
import com.yeisonmenau.gestordeudas.domain.persona.model.Persona;
import com.yeisonmenau.gestordeudas.infrastructure.adapter.PersonaJpaRepository;
import com.yeisonmenau.gestordeudas.infrastructure.dto.request.DeudaRequestDTO;
import com.yeisonmenau.gestordeudas.infrastructure.dto.response.DeudaResponseDTO;
import com.yeisonmenau.gestordeudas.infrastructure.entity.DeudaEntity;
import com.yeisonmenau.gestordeudas.infrastructure.entity.PersonaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeudaMapper {
    private final PersonaJpaRepository personaJpaRepository;

    public DeudaEntity domainToEntity(Deuda deudaDominio) {
        return new DeudaEntity(
                null,
                new PersonaEntity(deudaDominio.getPersona().getPersonaId(),
                        deudaDominio.getPersona().getPersonaCedula(),
                        deudaDominio.getPersona().getPersonaNombre(),
                        deudaDominio.getPersona().getPersonaFechaNacimiento()),
                deudaDominio.getDeudaValor(),
                deudaDominio.getDeudaFecha(),
                deudaDominio.getDeudaFechaMaximaPago(),
                false
        );
    }
    public Deuda entityToDomain(DeudaEntity deudaEntidad) {
        return new Deuda(
                deudaEntidad.getDeudaId(),
                new Persona(deudaEntidad.getPersona().getPersonaId(),
                        deudaEntidad.getPersona().getPersonaCedula(),
                        deudaEntidad.getPersona().getPersonaNombre(),
                        deudaEntidad.getPersona().getPersonaFechaNacimiento()),
                deudaEntidad.getDeudaValor(),
                deudaEntidad.getDeudaFecha(),
                deudaEntidad.getDeudaFechaMaximaPago(),
                deudaEntidad.getPagado()
        );
    }
    public DeudaEntity deudaRequestToEntity(DeudaRequestDTO deuda) {
        return new DeudaEntity(
                null,
                personaJpaRepository.findById(deuda.getPersonaId())
                        .orElseThrow(() -> new PersonaNoEncontradaException(deuda.getPersonaId())),
                deuda.getDeudaValor(),
                deuda.getDeudaFecha(),
                deuda.getDeudaFechaMaximaPago(),
                false
        );
    }
    public DeudaResponseDTO entityToDeudaResponse(DeudaEntity deudaEntidad) {
        return new DeudaResponseDTO(
                deudaEntidad.getDeudaId(),
                deudaEntidad.getPersona().getPersonaNombre(),
                deudaEntidad.getPersona().getPersonaCedula(),
                deudaEntidad.getDeudaValor(),
                deudaEntidad.getDeudaFecha(),
                deudaEntidad.getDeudaFechaMaximaPago(),
                deudaEntidad.getPagado()
        );
    }
    public Deuda requestToDomain(DeudaRequestDTO deuda) {
        DeudaEntity deudaEntity = deudaRequestToEntity(deuda);
        return entityToDomain(deudaEntity);
    }

    public DeudaResponseDTO domainToDeudaResponse(Deuda deuda) {
        return new DeudaResponseDTO(
                deuda.getDeudaId(),
                deuda.getPersona().getPersonaNombre(),
                deuda.getPersona().getPersonaCedula(),
                deuda.getDeudaValor(),
                deuda.getDeudaFecha(),
                deuda.getDeudaFechaMaximaPago(),
                deuda.getPagado()
        );
    }


}
