package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.VistaTablero;

public class AccionRendir implements ActionListener {

    public VistaTablero vistaTablero;

    public AccionRendir(VistaTablero vistaTablero) {
        this.vistaTablero = vistaTablero;

        this.vistaTablero.fin1.addActionListener(this);
        this.vistaTablero.fin2.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("pruestar");

        if (e.getSource().equals(this.vistaTablero.fin1)) {
            System.out.println("Prueba boton 1");
            System.exit(0);
        }
        if (e.getSource().equals(this.vistaTablero.fin2)) {
            System.out.println("Prueba boton 2");            
        }

    }

}
