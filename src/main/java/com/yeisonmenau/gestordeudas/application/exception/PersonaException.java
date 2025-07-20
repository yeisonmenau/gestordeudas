package com.yeisonmenau.gestordeudas.application.exception;

import com.yeisonmenau.gestordeudas.domain.persona.model.Persona;
import org.springframework.stereotype.Component;

@Component
public class PersonaException {
    public void validarDatos (Persona persona){
        if (persona.getPersonaNombre() == null || persona.getPersonaNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        if (persona.getPersonaCedula() == null || persona.getPersonaCedula().isBlank()) {
            throw new IllegalArgumentException("La cedula no puede estar vacia.");
        }
        if (persona.getPersonaFechaNacimiento() == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede estar vacía.");
        }
    }
}
