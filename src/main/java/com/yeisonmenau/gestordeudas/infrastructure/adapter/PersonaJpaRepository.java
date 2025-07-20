package com.yeisonmenau.gestordeudas.infrastructure.adapter;

import com.yeisonmenau.gestordeudas.infrastructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaJpaRepository extends JpaRepository<PersonaEntity, Long>{
}
