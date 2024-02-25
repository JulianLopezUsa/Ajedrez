package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.VistaTablero;
import vista.Rendir;

public class AccionRendir implements ActionListener {

    private VistaTablero vistaTablero;

    public AccionRendir(VistaTablero vistaTablero) {
        this.vistaTablero = vistaTablero;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaTablero.fin1) {
            System.out.println("Jugador 1 rendido!");
            Rendir rendir = new Rendir();
            rendir.setVisible(true); // Muestra el panel de rendición
        } else if (e.getSource() == vistaTablero.fin2) {
            System.out.println("Jugador 2 rendido!");
            Rendir rendir = new Rendir();
            rendir.setVisible(true); // Muestra el panel de rendición
        }
    }
}
