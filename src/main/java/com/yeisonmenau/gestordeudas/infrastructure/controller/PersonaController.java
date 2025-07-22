package com.yeisonmenau.gestordeudas.infrastructure.controller;

import com.yeisonmenau.gestordeudas.application.service.PersonaService;
import com.yeisonmenau.gestordeudas.domain.persona.model.Persona;
import com.yeisonmenau.gestordeudas.infrastructure.dto.request.PersonaRequestDTO;
import com.yeisonmenau.gestordeudas.infrastructure.dto.response.PersonaResponseDTO;
import com.yeisonmenau.gestordeudas.infrastructure.mapper.PersonaMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonaService personaService;
    private final PersonaMapper mapper;

    @PostMapping
    public ResponseEntity<PersonaResponseDTO> crearPersona (
            @Valid @RequestBody PersonaRequestDTO personaRequestDTO){

        Persona persona = mapper.requestToDomain(personaRequestDTO);
        Persona personaCreada = personaService.crearPersona(persona);
        PersonaResponseDTO response = mapper.domainToPersonaResponse(personaCreada);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    public ResponseEntity<List<PersonaResponseDTO>> mostrarPersonas () {
        List<Persona> personasDominio = personaService.mostrarPersonas();
        List<PersonaResponseDTO> personasResponse = personasDominio.stream()
                .map(mapper::domainToPersonaResponse)
                .toList();
        return ResponseEntity.ok(personasResponse);
    }
    @PutMapping("/{idPersona}")
    public ResponseEntity<PersonaResponseDTO> actualizarPersona (
            @PathVariable Long idPersona,
            @Valid @RequestBody PersonaRequestDTO personaRequestDTO){

        Persona persona = mapper.requestToDomain(personaRequestDTO);
        Persona personaActualizada = personaService.actualizarPersona(idPersona, persona);
        PersonaResponseDTO response = mapper.domainToPersonaResponse(personaActualizada);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{idPersona}")
    public ResponseEntity<String> eliminarPersona(@PathVariable Long idPersona) {
        String respuesta = personaService.eliminarPersona(idPersona);
        return ResponseEntity.ok(respuesta);
    }
}
