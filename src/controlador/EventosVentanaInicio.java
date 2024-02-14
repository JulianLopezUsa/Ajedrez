/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.MenuA;
import vista.VistaTablero;

/**
 *
 * @author Laura
 */
public class EventosVentanaInicio implements ActionListener {

    public MenuA menuA;


    public EventosVentanaInicio(MenuA menuA) {
        this.menuA = menuA;

        this.menuA.botonIniciar.addActionListener(this);
        this.menuA.botonSalir.addActionListener(this);
        
        this.menuA.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.menuA.botonIniciar)) {
            new EventosTablero(new VistaTablero());
            this.menuA.setVisible(false);
        }

        if (e.getSource().equals(this.menuA.botonSalir)) {
            System.exit(0);
        }
        
    }

}
