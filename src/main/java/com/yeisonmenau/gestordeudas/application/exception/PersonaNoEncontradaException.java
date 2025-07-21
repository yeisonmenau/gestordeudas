package com.yeisonmenau.gestordeudas.application.exception;

public class PersonaNoEncontradaException extends RuntimeException{
    public PersonaNoEncontradaException(Long id) {
        super("Persona con el ID " + id + " no encontrada.");
    }
}
