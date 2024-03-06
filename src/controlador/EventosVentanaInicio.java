package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Tablero.Tablero;
import modelo.jugadores.Jugadores;
import vista.MenuA;
import vista.VistaTablero;
import vistaConexion.Conexion;

public class EventosVentanaInicio implements ActionListener {
    // private final Jugador[] jugadores;

    private final MenuA menuA;
    private final Jugadores jugadores;

    public EventosVentanaInicio(MenuA menuA , Jugadores jugadores) {
        this.menuA = menuA;
        this.jugadores = jugadores;
        this.menuA.botonIniciar.addActionListener(this);
        this.menuA.botonIniciarL.addActionListener(this);
        this.menuA.botonSalir.addActionListener(this);
        this.menuA.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.menuA.botonIniciar)) {
            iniciarJuego();
        }else if (e.getSource().equals(this.menuA.botonIniciarL)) {
            iniciarLinea();
        }else if (e.getSource().equals(this.menuA.botonSalir)) {
            salirDelJuego();
        }
    }

    private void iniciarJuego() {
        this.menuA.setVisible(false);
        String nombreJ1 = pedirNombreJugador("Por favor, ingresa el nombre del jugador 1:");
        if (nombreJ1 == null) {
            System.exit(0);
        }
        String nombreJ2 = pedirNombreJugador("Por favor, ingresa el nombre del jugador 2:");
        if (nombreJ2 == null) {
            System.exit(0);
        }

        VistaTablero vistaTablero = new VistaTablero(nombreJ1, nombreJ2);
        Tablero tablero = new Tablero(nombreJ1, nombreJ2);
        new EventosTablero(vistaTablero, tablero);
    }

    private String pedirNombreJugador(String mensaje) {
        return JOptionPane.showInputDialog(null, mensaje);
    }

    private void iniciarLinea(){
        Conexion conexion = new Conexion();
        new VentanaLan(conexion,menuA,jugadores);
    }

    private void salirDelJuego() {
        System.exit(0);
    }
}
