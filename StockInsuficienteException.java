package com.medimarket.excepciones;

/**
 * Excepción personalizada que se lanza cuando se intenta agregar
 * al carrito una cantidad mayor al stock disponible de un producto.
 */
public class StockInsuficienteException extends Exception {
    private static final long serialVersionUID = 1L;

    public StockInsuficienteException(String mensaje) {
        super(mensaje);
    }

    public StockInsuficienteException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
