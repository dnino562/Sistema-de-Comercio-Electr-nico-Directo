package com.medimarket;

import com.medimarket.controlador.InicializadorDatos;
import com.medimarket.controlador.TiendaControlador;
import com.medimarket.vista.VentanaPrincipal;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Clase principal de la aplicación MediMarket.
 * Punto de entrada que inicializa los datos de demostración
 * y lanza la ventana principal de la tienda virtual.
 */
public class Main {

    public static void main(String[] args) {
        // Inicializar datos de demostración (productos y usuarios) si no existen
        InicializadorDatos.inicializarSiVacio();

        // Lanzar la interfaz gráfica en el Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // Si falla, se usa el Look & Feel por defecto de Swing
            }

            TiendaControlador controlador = new TiendaControlador();
            VentanaPrincipal ventana = new VentanaPrincipal(controlador);
            ventana.setVisible(true);
        });
    }
}
