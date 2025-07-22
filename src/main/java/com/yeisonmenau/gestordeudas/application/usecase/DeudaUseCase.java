package com.yeisonmenau.gestordeudas.application.usecase;

import com.yeisonmenau.gestordeudas.domain.deuda.model.Deuda;

import java.util.List;

public interface DeudaUseCase {
    Deuda crearDeuda (Deuda deuda);
    List<Deuda> mostrarDeudas ();
    Deuda actualizarDeuda (Long idDeuda, Deuda deuda);
    String eliminarDeuda (Long idDeuda);
    String saldarDeuda (Long idDeuda);
    List<Deuda> mostrarDeudasPorPersona(Long idPersona);
}
