package com.yeisonmenau.gestordeudas.infrastructure.config;

import com.yeisonmenau.gestordeudas.application.service.PersonaService;
import com.yeisonmenau.gestordeudas.domain.persona.out.PersonaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBean {
    @Bean
    public PersonaService personaService(PersonaRepository personaRepository) {
        return new PersonaService(personaRepository);
    }


}
