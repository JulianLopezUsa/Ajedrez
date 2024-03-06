package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import controlador.sockets.SalaDeEspera;
import vista.MenuA;
import vistaConexion.Conexion;
import vistaConexion.TableroCliente;
import modelo.jugadores.Jugadores;

public class VentanaLan implements ActionListener {

    private final Conexion conexion;
    private final MenuA menuA;
    private final Jugadores jugadores;
    public String nombre = "";

    public VentanaLan(Conexion conexion, MenuA menuA, Jugadores jugadores) {
        this.menuA = menuA;
        this.conexion = conexion;
        this.conexion.crear.addActionListener(this);
        this.conexion.unir.addActionListener(this);
        this.conexion.salir.addActionListener(this);
        this.conexion.setVisible(true);
        this.jugadores = jugadores;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.conexion.crear)) {
            crear();
        } else if (e.getSource().equals(this.conexion.unir)) {
            unir();
        } else if (e.getSource().equals(this.conexion.salir)) {
            salir();
        }
    }

    private void crear() {
        this.menuA.setVisible(false);
        nombre = JOptionPane.showInputDialog(this.conexion, "Ingrese su nombre", "Nombre del jugador",
                JOptionPane.QUESTION_MESSAGE);
        Thread hilo = new Thread(new SalaDeEspera(this, false, nombre, jugadores));
        hilo.start();
    }

    private void unir() {
        nombre = JOptionPane.showInputDialog(this.conexion, "Ingrese su nombre", "Nombre jugador",
                JOptionPane.QUESTION_MESSAGE);

        TableroCliente juego = new TableroCliente(nombre, jugadores);
        juego.setVisible(true);

    }

    private void salir() {
        this.conexion.setVisible(false);
    }
}
