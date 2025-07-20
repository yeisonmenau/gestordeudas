package com.yeisonmenau.gestordeudas.infrastructure.adapter;

import com.yeisonmenau.gestordeudas.domain.deuda.model.Deuda;
import com.yeisonmenau.gestordeudas.infrastructure.entity.DeudaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeudaJpaRepository extends JpaRepository<DeudaEntity, Long> {
}
