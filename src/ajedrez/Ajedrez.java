package ajedrez;

import javax.swing.SwingUtilities;

import controlador.EventosVentanaInicio;
import vista.MenuA;

public class Ajedrez {

    /**
     * @param args
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EventosVentanaInicio(new MenuA());
            }
        });
    }
}