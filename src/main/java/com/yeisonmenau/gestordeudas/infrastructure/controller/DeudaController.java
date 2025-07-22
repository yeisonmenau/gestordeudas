package com.yeisonmenau.gestordeudas.infrastructure.controller;

import com.yeisonmenau.gestordeudas.application.service.DeudaService;
import com.yeisonmenau.gestordeudas.domain.deuda.model.Deuda;
import com.yeisonmenau.gestordeudas.infrastructure.dto.request.DeudaRequestDTO;
import com.yeisonmenau.gestordeudas.infrastructure.dto.response.DeudaResponseDTO;
import com.yeisonmenau.gestordeudas.infrastructure.mapper.DeudaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deuda")
@RequiredArgsConstructor
public class DeudaController {
    private final DeudaService deudaService;
    private final DeudaMapper mapper;

    @PostMapping
    public ResponseEntity<DeudaResponseDTO> crearDeuda(DeudaRequestDTO deudaRequestDTO) {
        Deuda deuda = mapper.requestToDomain(deudaRequestDTO);
        Deuda deudaCreada = deudaService.crearDeuda(deuda);
        DeudaResponseDTO response = mapper.domainToDeudaResponse(deudaCreada);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
