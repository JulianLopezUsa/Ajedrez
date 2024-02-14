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
                // Caballo nuevo = new Caballo(0, 0, "Negro");
                // nuevo.movimientoFicha("h 8");
                
                /*Peon nuevo1 = new Peon(0, 0, "Negro");
                nuevo1.movimientoFicha("g 2", 0);
                nuevo1.movimientoFicha("f 2 ", 0);
                nuevo1.movimientoFicha("e 2 ", 0);  */

                /*Alfil nuevo = new Alfil(0, 0, "Negro");
                nuevo.movimientoFicha("e 6",0);*/

                /*Torre nuevo = new Torre(0, 0, "Negro");
                nuevo.movimientoFicha("d 5",0);*/

                /*Dama nuevo = new Dama(0, 0, "Negro");
                nuevo.movimientoFicha("a 4",0);*/

                /*Rey nuevo = new Rey(0, 0, "Negro");
                nuevo.movimientoFicha("b 5",0);*/

                new EventosVentanaInicio(new MenuA());

            }
        });

    }

}
