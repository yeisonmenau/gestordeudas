package com.yeisonmenau.gestordeudas.infrastructure.adapter.implement;

import com.yeisonmenau.gestordeudas.domain.deuda.model.Deuda;
import com.yeisonmenau.gestordeudas.domain.deuda.out.DeudaRepository;
import com.yeisonmenau.gestordeudas.infrastructure.adapter.DeudaJpaRepository;
import com.yeisonmenau.gestordeudas.infrastructure.adapter.PersonaJpaRepository;
import com.yeisonmenau.gestordeudas.infrastructure.entity.DeudaEntity;
import com.yeisonmenau.gestordeudas.infrastructure.mapper.DeudaMapper;
import com.yeisonmenau.gestordeudas.infrastructure.mapper.PersonaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@RequiredArgsConstructor
public class DeudaAdapter implements DeudaRepository {

    private final DeudaJpaRepository deudaJpaRepository;
    private final PersonaJpaRepository personaJpaRepository;
    private final DeudaMapper mapper;

    @Override
    public Deuda crearDeuda(Deuda deuda) {
        DeudaEntity deudaEntidad = mapper.domainToEntity(deuda);
        DeudaEntity deudaGuardada = deudaJpaRepository.save(deudaEntidad);
        return mapper.entityToDomain(deudaGuardada);
    }

    @Override
    public List<Deuda> mostrarDeudas() {
        return List.of();
    }

    @Override
    public Deuda actualizarDeuda(Long idDeuda, Deuda deuda) {
        return null;
    }

    @Override
    public String eliminarDeuda(Long idDeuda) {
        return "";
    }

    @Override
    public String saldarDeuda(Long idDeuda) {
        return "";
    }

    @Override
    public List<Deuda> mostrarDeudasPorPersona(Long idPersona) {
        return List.of();
    }
}
