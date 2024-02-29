/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controlador.sockets.SalaDeEspera;
import modelo.Tablero.Tablero;
import vista.MenuA;
import vista.VistaTablero;

public class EventosVentanaInicio implements ActionListener {
    private final Jugador[] jugadores;

    public MenuA menuA;

    public EventosVentanaInicio(MenuA menuA) {
        this.menuA = menuA;

        this.menuA.botonIniciar.addActionListener(this);
        this.menuA.botonIniciarL.addActionListener(this);
        this.menuA.botonSalir.addActionListener(this);

        this.menuA.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.menuA.botonIniciar)) {
            this.menuA.setVisible(false);
            String nombreJ1 = JOptionPane.showInputDialog(null, "Por favor, ingresa el nombre del jugador 1:");
            if (nombreJ1 == null) {
                System.exit(0);
            }
            String nombreJ2 = JOptionPane.showInputDialog(null, "Por favor, ingresa el nombre del jugador 2:");
            if (nombreJ2 == null) {
                System.exit(0);
            }

            VistaTablero vistaTablero = new VistaTablero(nombreJ1, nombreJ2);

            Tablero tablero = new Tablero(nombreJ1, nombreJ2, vistaTablero);
            new EventosTablero(vistaTablero, tablero);

        }
        if (e.getSource().equals(this.menuA.botonIniciarL)) {
            String nombre = JOptionPane.showInputDialog(this, "Ingrese su nombre", "Nombre jugador",
                    JOptionPane.QUESTION_MESSAGE);

            Thread hilo = new Thread(new SalaDeEspera(this, false, nombre, jugadores));
            hilo.start();

        }
        if (e.getSource().equals(this.menuA.botonSalir)) {
            System.exit(0);
        }

    }

}