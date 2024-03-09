package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.MenuA;
import vista.Rendir;
import vista.VistaTablero;

public class EventosVentanita implements ActionListener {

    private Rendir rendir;
    private VistaTablero vistaTablero;

    public EventosVentanita(Rendir rendir, VistaTablero vistaTablero) {
        this.rendir = rendir;
        this.vistaTablero = vistaTablero;
        this.rendir.btnAceptar.addActionListener(this);
        this.rendir.btnRechazar.addActionListener(this);
        this.rendir.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.rendir.btnAceptar)) {
            this.rendir.setVisible(false);
            this.vistaTablero.setVisible(false);
            MenuA menu = new MenuA();
            new EventosVentanaInicio(menu);
        } else if (e.getSource().equals(this.rendir.btnRechazar)) {
            this.rendir.setVisible(false);
        }
    }
}