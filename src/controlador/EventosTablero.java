package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.VistaTablero;

public class EventosTablero implements ActionListener {

    public VistaTablero tablero;


    public EventosTablero(VistaTablero tablero) {
        this.tablero = tablero;

        //this.tablero.botonIniciar.addActionListener(this);
        
        this.tablero.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        
    }

}
