package com.yeisonmenau.gestordeudas.infrastructure.controller;

import com.yeisonmenau.gestordeudas.application.service.PersonaService;
import com.yeisonmenau.gestordeudas.domain.persona.model.Persona;
import com.yeisonmenau.gestordeudas.infrastructure.dto.request.PersonaRequestDTO;
import com.yeisonmenau.gestordeudas.infrastructure.dto.response.PersonaResponseDTO;
import com.yeisonmenau.gestordeudas.infrastructure.mapper.PersonaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persona")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonaService personaService;
    private final PersonaMapper mapper;

    @PostMapping
    public ResponseEntity<PersonaResponseDTO> crearPersona (PersonaRequestDTO personaRequestDTO){
        Persona persona = mapper.requestToDomain(personaRequestDTO);
        Persona personaCreada = personaService.crearPersona(persona);
        PersonaResponseDTO response = mapper.domainToPersonaResponse(personaCreada);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
