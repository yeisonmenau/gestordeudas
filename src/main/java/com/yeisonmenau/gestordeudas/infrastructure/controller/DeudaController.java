package com.yeisonmenau.gestordeudas.infrastructure.controller;

import com.yeisonmenau.gestordeudas.application.service.DeudaService;
import com.yeisonmenau.gestordeudas.domain.deuda.model.Deuda;
import com.yeisonmenau.gestordeudas.infrastructure.dto.request.DeudaRequestDTO;
import com.yeisonmenau.gestordeudas.infrastructure.dto.response.DeudaResponseDTO;
import com.yeisonmenau.gestordeudas.infrastructure.mapper.DeudaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    public ResponseEntity<List<DeudaResponseDTO>> mostrarDeudas() {
        List<Deuda> deudasDominio = deudaService.mostrarDeudas();
        List<DeudaResponseDTO> deudasResponse = deudasDominio.stream()
                .map(mapper::domainToDeudaResponse)
                .toList();
        return ResponseEntity.ok(deudasResponse);
    }
    @PutMapping("/{idDeuda}")
    public ResponseEntity<DeudaResponseDTO> actualizarDeuda(
            @PathVariable Long idDeuda,
            @RequestBody DeudaRequestDTO deudaRequestDTO) {
        Deuda deuda = mapper.requestToDomain(deudaRequestDTO);
        Deuda deudaActualizada = deudaService.actualizarDeuda(idDeuda, deuda);
        DeudaResponseDTO response = mapper.domainToDeudaResponse(deudaActualizada);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{idDeuda}")
    public ResponseEntity<String> eliminarDeuda(@PathVariable Long idDeuda) {
        String respuesta = deudaService.eliminarDeuda(idDeuda);
        return ResponseEntity.ok(respuesta);
    }
    @PutMapping("/saldar/{idDeuda}")
    public ResponseEntity<String> saldarDeuda(@PathVariable Long idDeuda) {
        String respuesta = deudaService.saldarDeuda(idDeuda);
        return ResponseEntity.ok(respuesta);
    }
    @GetMapping("/persona/{idPersona}")
    public ResponseEntity<List<DeudaResponseDTO>> mostrarDeudasPorPersona(@PathVariable Long idPersona) {
        List<Deuda> deudasPorPersona = deudaService.mostrarDeudasPorPersona(idPersona);
        List<DeudaResponseDTO> deudasResponse = deudasPorPersona.stream()
                .map(mapper::domainToDeudaResponse)
                .toList();
        return ResponseEntity.ok(deudasResponse);
    }
    @GetMapping("/total/{idPersona}")
    public ResponseEntity<String> totalDeudasPorPersona(@PathVariable Long idPersona) {
        String totalDeudas = deudaService.totalDeudasPorPersona(idPersona);
        return ResponseEntity.ok(totalDeudas);
    }


}
