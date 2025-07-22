package com.yeisonmenau.gestordeudas.infrastructure.adapter.implement;

import com.yeisonmenau.gestordeudas.application.exception.DeudaNoEncontradaException;
import com.yeisonmenau.gestordeudas.domain.deuda.model.Deuda;
import com.yeisonmenau.gestordeudas.domain.deuda.out.DeudaRepository;
import com.yeisonmenau.gestordeudas.domain.persona.out.PersonaRepository;
import com.yeisonmenau.gestordeudas.infrastructure.adapter.DeudaJpaRepository;
import com.yeisonmenau.gestordeudas.infrastructure.adapter.PersonaJpaRepository;
import com.yeisonmenau.gestordeudas.infrastructure.entity.DeudaEntity;
import com.yeisonmenau.gestordeudas.infrastructure.entity.PersonaEntity;
import com.yeisonmenau.gestordeudas.infrastructure.mapper.DeudaMapper;
import com.yeisonmenau.gestordeudas.infrastructure.mapper.PersonaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class DeudaAdapter implements DeudaRepository {

    private final DeudaJpaRepository deudaJpaRepository;
    private final DeudaMapper mapper;
    private final PersonaJpaRepository personaJpaRepository;

    @Override
    public Deuda crearDeuda(Deuda deuda) {
        DeudaEntity deudaEntidad = mapper.domainToEntity(deuda);
        DeudaEntity deudaGuardada = deudaJpaRepository.save(deudaEntidad);
        return mapper.entityToDomain(deudaGuardada);
    }

    @Override
    public List<Deuda> mostrarDeudas() {
        List<DeudaEntity> deudasRegistradas = deudaJpaRepository.findAll();
        return deudasRegistradas.stream()
                .map(mapper::entityToDomain)
                .toList();
    }

    @Override
    public Deuda actualizarDeuda(Long idDeuda, Deuda deuda) {
        DeudaEntity existente = deudaJpaRepository.findById(idDeuda)
                .orElseThrow(() -> new DeudaNoEncontradaException(idDeuda));
        DeudaEntity deudaActualizada = mapper.domainToEntity(deuda);
        deudaActualizada.setDeudaId(existente.getDeudaId());
        DeudaEntity deudaGuardada = deudaJpaRepository.save(deudaActualizada);
        return mapper.entityToDomain(deudaGuardada);
    }

    @Override
    public String eliminarDeuda(Long idDeuda) {
        DeudaEntity existente = deudaJpaRepository.findById(idDeuda)
                .orElseThrow(() -> new DeudaNoEncontradaException(idDeuda));
        deudaJpaRepository.delete(existente);
        return "Deuda con ID " + idDeuda + " eliminada correctamente.";
    }

    @Override
    public String saldarDeuda(Long idDeuda) {
        DeudaEntity existente = deudaJpaRepository.findById(idDeuda)
                .orElseThrow(() -> new DeudaNoEncontradaException(idDeuda));
        existente.setPagado(true);
        deudaJpaRepository.save(existente);
        return "Deuda de " + existente.getPersona().getPersonaNombre() + " con ID " + idDeuda + " saldada correctamente.";
    }

    @Override
    public List<Deuda> mostrarDeudasPorPersona(Long idPersona) {
        PersonaEntity existente = personaJpaRepository.findById(idPersona)
                .orElseThrow(() -> new DeudaNoEncontradaException(idPersona));
        List<DeudaEntity> deudasPorPersona = deudaJpaRepository.findAll().stream()
                .filter(deudaEntity -> Objects.equals(existente, deudaEntity.getPersona())).toList();
        return deudasPorPersona.stream()
                .map(mapper::entityToDomain)
                .toList();
    }
}
