package com.medimarket.excepciones;

/**
 * Excepción lanzada cuando los datos ingresados por el usuario
 * no cumplen con las reglas de validación.
 */
public class DatosInvalidosException extends Exception {
    private static final long serialVersionUID = 1L;

    public DatosInvalidosException(String mensaje) {
        super(mensaje);
    }
}
