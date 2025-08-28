package com.yeisonmenau.gestordeudas.controller;
import com.yeisonmenau.gestordeudas.application.service.PersonaService;
import com.yeisonmenau.gestordeudas.domain.persona.model.Persona;
import com.yeisonmenau.gestordeudas.infrastructure.controller.PersonaController;
import com.yeisonmenau.gestordeudas.infrastructure.dto.request.PersonaRequestDTO;
import com.yeisonmenau.gestordeudas.infrastructure.dto.response.PersonaResponseDTO;
import com.yeisonmenau.gestordeudas.infrastructure.mapper.PersonaMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PersonaControllerTest {
    @InjectMocks
    private PersonaController personaController;

    @Mock
    private PersonaService personaService;

    @Mock
    private PersonaMapper personaMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearPersona_deberiaRetornarPersonaCreada() {
        // Arrange
        PersonaRequestDTO requestDTO = new PersonaRequestDTO();
        Persona persona = new Persona();
        PersonaResponseDTO responseDTO = new PersonaResponseDTO();
        when(personaMapper.requestToDomain(any(PersonaRequestDTO.class))).thenReturn(persona);
        when(personaService.crearPersona(any(Persona.class))).thenReturn(persona);
        when(personaMapper.domainToPersonaResponse(any(Persona.class))).thenReturn(responseDTO);

        // Act
        ResponseEntity<PersonaResponseDTO> response = personaController.crearPersona(requestDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void mostrarPersonas_deberiaRetornarListaPersonas() {
        // Arrange
        List<Persona> personas = List.of(new Persona());
        List<PersonaResponseDTO> responseDTOs = List.of(new PersonaResponseDTO());
        when(personaService.mostrarPersonas()).thenReturn(personas);
        when(personaMapper.domainToPersonaResponse(any(Persona.class))).thenReturn(new PersonaResponseDTO());

        // Act
        ResponseEntity<List<PersonaResponseDTO>> response = personaController.mostrarPersonas();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTOs.size(), response.getBody().size());
    }

    @Test
    void actualizarPersona_deberiaRetornarPersonaActualizada() {
        // Arrange
        Long idPersona = 1L;
        PersonaRequestDTO requestDTO = new PersonaRequestDTO();
        Persona persona = new Persona();
        PersonaResponseDTO responseDTO = new PersonaResponseDTO();
        when(personaMapper.requestToDomain(any(PersonaRequestDTO.class))).thenReturn(persona);
        when(personaService.actualizarPersona(any(Long.class), any(Persona.class))).thenReturn(persona);
        when(personaMapper.domainToPersonaResponse(any(Persona.class))).thenReturn(responseDTO);

        // Act
        ResponseEntity<PersonaResponseDTO> response = personaController.actualizarPersona(idPersona, requestDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

//    @Test
//    void eliminarPersona_deberiaRetornarMensajeConfirmacion() {
//        // Arrange
//        Long idPersona = 1L;
//        when(personaService.eliminarPersona(idPersona)).thenReturn(<T>);
//
//        // Act
//        ResponseEntity<String> response = personaController.eliminarPersona(idPersona);
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Persona eliminada", response.getBody());
//    }
}
