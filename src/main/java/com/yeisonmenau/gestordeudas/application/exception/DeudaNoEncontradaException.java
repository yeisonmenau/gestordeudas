package com.yeisonmenau.gestordeudas.application.exception;

public class DeudaNoEncontradaException extends IllegalArgumentException{
    public DeudaNoEncontradaException(Long id) {
        super("Deuda con el ID " + id + " no encontrada.");
    }
}
