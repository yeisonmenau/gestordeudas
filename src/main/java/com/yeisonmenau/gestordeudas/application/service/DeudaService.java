package com.yeisonmenau.gestordeudas.application.service;

import com.yeisonmenau.gestordeudas.application.usecase.DeudaUseCase;
import com.yeisonmenau.gestordeudas.domain.deuda.model.Deuda;
import com.yeisonmenau.gestordeudas.domain.deuda.out.DeudaRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class DeudaService implements DeudaUseCase {
    private final DeudaRepository deudaRepository;

    @Override
    public Deuda crearDeuda(Deuda deuda) {
        return deudaRepository.crearDeuda(deuda);
    }

    @Override
    public List<Deuda> mostrarDeudas() {
        return deudaRepository.mostrarDeudas();
    }

    @Override
    public Deuda actualizarDeuda(Long idDeuda, Deuda deuda) {
        return deudaRepository.actualizarDeuda(idDeuda, deuda);
    }

    @Override
    public String eliminarDeuda(Long idDeuda) {
        return deudaRepository.eliminarDeuda(idDeuda);
    }

    @Override
    public String saldarDeuda(Long idDeuda) {
        return deudaRepository.saldarDeuda(idDeuda);
    }

    @Override
    public List<Deuda> mostrarDeudasPorPersona(Long idPersona) {
        return deudaRepository.mostrarDeudasPorPersona(idPersona);
    }
}
