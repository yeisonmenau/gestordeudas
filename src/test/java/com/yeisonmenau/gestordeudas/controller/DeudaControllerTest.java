package com.yeisonmenau.gestordeudas.controller;

import com.yeisonmenau.gestordeudas.application.service.DeudaService;
import com.yeisonmenau.gestordeudas.domain.deuda.model.Deuda;
import com.yeisonmenau.gestordeudas.infrastructure.controller.DeudaController;
import com.yeisonmenau.gestordeudas.infrastructure.dto.request.DeudaRequestDTO;
import com.yeisonmenau.gestordeudas.infrastructure.dto.response.DeudaResponseDTO;
import com.yeisonmenau.gestordeudas.infrastructure.mapper.DeudaMapper;
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

class DeudaControllerTest {

    @InjectMocks
    private DeudaController deudaController;

    @Mock
    private DeudaService deudaService;

    @Mock
    private DeudaMapper deudaMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearDeuda_deberiaRetornarDeudaCreada() {
        // Arrange
        DeudaRequestDTO requestDTO = new DeudaRequestDTO();
        Deuda deuda = new Deuda();
        DeudaResponseDTO responseDTO = new DeudaResponseDTO();
        when(deudaMapper.requestToDomain(any(DeudaRequestDTO.class))).thenReturn(deuda);
        when(deudaService.crearDeuda(any(Deuda.class))).thenReturn(deuda);
        when(deudaMapper.domainToDeudaResponse(any(Deuda.class))).thenReturn(responseDTO);

        // Act
        ResponseEntity<DeudaResponseDTO> response = deudaController.crearDeuda(requestDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void mostrarDeudas_deberiaRetornarListaDeudas() {
        // Arrange
        List<Deuda> deudas = List.of(new Deuda());
        List<DeudaResponseDTO> responseDTOs = List.of(new DeudaResponseDTO());
        when(deudaService.mostrarDeudas()).thenReturn(deudas);
        when(deudaMapper.domainToDeudaResponse(any(Deuda.class))).thenReturn(new DeudaResponseDTO());

        // Act
        ResponseEntity<List<DeudaResponseDTO>> response = deudaController.mostrarDeudas();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTOs.size(), response.getBody().size());
    }

    @Test
    void actualizarDeuda_deberiaRetornarDeudaActualizada() {
        // Arrange
        Long idDeuda = 1L;
        DeudaRequestDTO requestDTO = new DeudaRequestDTO();
        Deuda deuda = new Deuda();
        DeudaResponseDTO responseDTO = new DeudaResponseDTO();
        when(deudaMapper.requestToDomain(any(DeudaRequestDTO.class))).thenReturn(deuda);
        when(deudaService.actualizarDeuda(any(Long.class), any(Deuda.class))).thenReturn(deuda);
        when(deudaMapper.domainToDeudaResponse(any(Deuda.class))).thenReturn(responseDTO);

        // Act
        ResponseEntity<DeudaResponseDTO> response = deudaController.actualizarDeuda(idDeuda, requestDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void eliminarDeuda_deberiaRetornarMensajeConfirmacion() {
        // Arrange
        Long idDeuda = 1L;
        when(deudaService.eliminarDeuda(idDeuda)).thenReturn("Deuda eliminada");

        // Act
        ResponseEntity<String> response = deudaController.eliminarDeuda(idDeuda);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deuda eliminada", response.getBody());
    }

    @Test
    void saldarDeuda_deberiaRetornarMensajeConfirmacion() {
        // Arrange
        Long idDeuda = 1L;
        when(deudaService.saldarDeuda(idDeuda)).thenReturn("Deuda saldada");

        // Act
        ResponseEntity<String> response = deudaController.saldarDeuda(idDeuda);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deuda saldada", response.getBody());
    }

    @Test
    void mostrarDeudasPorPersona_deberiaRetornarListaDeudas() {
        // Arrange
        Long idPersona = 1L;
        List<Deuda> deudas = List.of(new Deuda());
        List<DeudaResponseDTO> responseDTOs = List.of(new DeudaResponseDTO());
        when(deudaService.mostrarDeudasPorPersona(idPersona)).thenReturn(deudas);
        when(deudaMapper.domainToDeudaResponse(any(Deuda.class))).thenReturn(new DeudaResponseDTO());

        // Act
        ResponseEntity<List<DeudaResponseDTO>> response = deudaController.mostrarDeudasPorPersona(idPersona);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTOs.size(), response.getBody().size());
    }
}
