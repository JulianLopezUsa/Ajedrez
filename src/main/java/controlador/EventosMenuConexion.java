package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import vista.MenuA;
import vista.VistaTablero;
import vistaConexion.MenuConexion;
import modelo.Tablero.Tablero;
import modelo.jugadores.Jugadores;
import sockets.SalaDeEspera;

public class EventosMenuConexion implements ActionListener {

    private final MenuConexion conexion;
    private final MenuA menuA;
    public String nombreJ1 = "";
    public String nombreJ2 = "";
    public Tablero tablero;
    //Jugadores que se agregaran.
    private final Jugadores[] jugadores;

    public EventosMenuConexion(MenuConexion conexion, MenuA menuA) {
        this.menuA = menuA;
        this.conexion = conexion;
        this.tablero = new Tablero(nombreJ2, nombreJ1);
        this.jugadores = new Jugadores[2];
        jugadores[0] = new Jugadores("0");
        jugadores[1] = new Jugadores("1");
        this.conexion.crear.addActionListener(this);
        this.conexion.unir.addActionListener(this);
        this.conexion.salir.addActionListener(this);
        this.conexion.setVisible(true);
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
        //conexion.setVisible(false);
        nombreJ1 = JOptionPane.showInputDialog(this.conexion, "Ingrese su nombre", "Nombre del jugador",
                JOptionPane.QUESTION_MESSAGE);
        Thread hilo = new Thread(new SalaDeEspera(this.conexion, false, nombreJ1, jugadores, tablero));
        hilo.start();
    }

    private void unir() {
        this.menuA.setVisible(false);
        //this.conexion.setVisible(false);
        nombreJ2 = JOptionPane.showInputDialog(this.conexion, "Ingrese su nombre", "Nombre jugador",
                JOptionPane.QUESTION_MESSAGE);

        new EventosCliente(jugadores, nombreJ1 ,new VistaTablero(nombreJ2, nombreJ1),tablero);
        
        //TableroCliente juego = new TableroCliente(nombre, jugadores);
        //juego.setVisible(true);

    }

    private void salir() {
        this.conexion.setVisible(false);
    }
}
