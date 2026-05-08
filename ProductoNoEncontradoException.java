package com.medimarket.excepciones;

/**
 * Excepción lanzada cuando un producto no es encontrado en el catálogo.
 */
public class ProductoNoEncontradoException extends Exception {
    private static final long serialVersionUID = 1L;

    public ProductoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
