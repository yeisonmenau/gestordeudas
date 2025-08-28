package com.yeisonmenau.gestordeudas.domain.deuda.out;

import com.yeisonmenau.gestordeudas.domain.deuda.model.Deuda;
import com.yeisonmenau.gestordeudas.domain.persona.model.Persona;

import java.util.List;

public interface DeudaRepository {
    Deuda crearDeuda (Deuda deuda);
    List<Deuda> mostrarDeudas ();
    Deuda actualizarDeuda (Long idDeuda, Deuda deuda);
    String eliminarDeuda (Long idDeuda);
    String saldarDeuda (Long idDeuda);
    List<Deuda> mostrarDeudasPorPersona(Long idPersona);
    String totalDeudasPorPersona(Long idPersona);
}
