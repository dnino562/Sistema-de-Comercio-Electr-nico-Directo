package com.medimarket.excepciones;

/**
 * Excepción lanzada cuando hay errores de autenticación
 * o el usuario/contraseña no son correctos.
 */
public class AutenticacionException extends Exception {
    private static final long serialVersionUID = 1L;

    public AutenticacionException(String mensaje) {
        super(mensaje);
    }
}
