package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.Rendir;

public class BotonRendir implements ActionListener {

    private Rendir rendir;

    public BotonRendir(Rendir rendir) {
        this.rendir = rendir;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Aceptar")) {
            rendir.cerrarVentana();
        } else if (e.getActionCommand().equals("Rechazar")) {
            // Lógica cuando se presiona el botón Rechazar
            rendir.ocultarVentana(); // Oculta la ventana actual
        }
    }
}
