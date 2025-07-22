package com.yeisonmenau.gestordeudas.infrastructure.config;

import com.yeisonmenau.gestordeudas.application.service.DeudaService;
import com.yeisonmenau.gestordeudas.application.service.PersonaService;
import com.yeisonmenau.gestordeudas.domain.deuda.out.DeudaRepository;
import com.yeisonmenau.gestordeudas.domain.persona.out.PersonaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBean {
    @Bean
    public PersonaService personaService(PersonaRepository personaRepository) {
        return new PersonaService(personaRepository);
    }
    @Bean
    public DeudaService deudaService(DeudaRepository deudaRepository) {
        return new DeudaService(deudaRepository);
    }

}
