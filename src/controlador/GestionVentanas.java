package controlador;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import vista.MenuA;

public class GestionVentanas {
    private static final List<JFrame> ventanasAbiertas = new ArrayList<>();
    
    public static void agregarVentana(JFrame ventana) {
        ventanasAbiertas.add(ventana);
    }
    
    public static void cerrarTodas() {
        for (JFrame ventana : ventanasAbiertas) {
            ventana.dispose();
        }
        ventanasAbiertas.clear();
    }

    public void reiniciarAplicacion() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Cerrar todas las ventanas abiertas
                GestionVentanas.cerrarTodas();
                
                // Crear una nueva instancia de la ventana de inicio y mostrarla
                MenuA menu = new MenuA();
                new EventosVentanaInicio(menu);
            }
        });
    }
}

