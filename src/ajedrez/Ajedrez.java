package ajedrez;

import javax.swing.SwingUtilities;
import controlador.EventosVentanaInicio;
import vista.MenuA;

public class Ajedrez {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MenuA menu = new MenuA();
                new EventosVentanaInicio(menu, null);
            }
        });
    }
}