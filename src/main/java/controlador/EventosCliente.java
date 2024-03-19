package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Tablero.Cuadro;
import modelo.Tablero.Tablero;
import modelo.fichas.Fichas;
import modelo.jugadores.Jugadores;
import sockets.Cliente;
import vista.VistaTablero;

public class EventosCliente implements ActionListener, Runnable, Eventos {

  public VistaTablero vistaTablero;
  public Tablero tablero;
  public Cuadro cuadroSeleccionado;
  private int fsX, fsY;
  public Cliente cliente;

  public EventosCliente(Jugadores[] jugadores, String nombre, VistaTablero vistaTablero, Tablero tablero) {
    this.vistaTablero = vistaTablero;
    this.tablero = tablero;
    try {
      this.cliente = new Cliente();
    } catch (IOException ex) {
      Logger.getLogger("nn").log(Level.SEVERE, null, ex);
    }
    cliente.enviarDatosServidor(nombre);
    String datos = cliente.leerDatosServidor();
    jugadores[0].setNombre(datos);
    jugadores[1].setNombre(nombre);

    this.vistaTablero.agregarFichas();
    this.tablero.inicializarCuadros();

    this.vistaTablero.setVisible(true);
    this.vistaTablero.setFocusable(true);
    this.cliente.limpiarSalida();

    darAccionBotones();

    new Thread(this).start();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (e.getSource() == this.vistaTablero.cuadro[i][j]) {
          // Verificar si hay una ficha en el botón presionado
          Fichas f = tablero.verificaciones.hayFicha(
              i,
              j,
              tablero.getTurno(),
              tablero);

          // Encontrar cuadro
          for (Cuadro cuadro : tablero.listaDeCuadros) {
            if (cuadro.getX() == i && cuadro.getY() == j) {
              cuadroSeleccionado = cuadro;
            }
          }

          // Si ya hay una ficha seleccionada, intentar moverla al cuadro presionado
          if (cuadroSeleccionado.getColor() == Color.YELLOW) {
            System.out.println("Cliente envía:" + i + " " + j + " " + tablero.turno + " " + fsX + " " + fsY);

            // Envio datos con turno 1
            cliente.enviarDatosServidor(
                i + " " + j + " " + tablero.turno + " " + fsX + " " + fsY);
            // Cambia a turno a 0
            tablero.verificaciones.verificarMovimientoAmarillo(i, j, this, tablero, "cliente");
            // new Thread(this).start();
            // limpia
            cliente.limpiarSalida();
          } else {
            if (f != null) {
              if (tablero.getTurno() == 1) {
                tablero.verificaciones.VerificarPosiblesMovimientos(f, i, j, tablero, this, "cliente");
                fsX = i;
                fsY = j;
              }
            }
          }
          return; // Salir del bucle cuando se encuentre el botón presionado
        }
      }
    }
  }

  public void eliminarDeVista(int x, int y) {
    vistaTablero.eliminarDeVista(x, y);
  }

  public void quitarJaqueVista() {
    vistaTablero.quitarJaque();
  }

  public String coronacionFichaVista(int turno, int x, int y) {
    String nn = vistaTablero.coronacionPieza(turno, x, y);
    return nn;
  }

  public void cambiarbanderaB() {
    vistaTablero.cambiarBanderaB();
  }

  public void cambiarbanderaN() {
    vistaTablero.cambiarBanderaN();
  }

  public void resetearColoresVista() {
    vistaTablero.resetearColores();
  }

  public void ponerJaqueVista() {
    vistaTablero.ponerJaque(tablero.jaqueBlanco, tablero.jaqueNegro, "blanco",
        tablero.verificaciones.fichaElegida.getPosX(), tablero.verificaciones.fichaElegida.getPosY());
    vistaTablero.ponerJaque(tablero.jaqueBlanco, tablero.jaqueNegro, "negro",
        tablero.verificaciones.fichaElegida.getPosX(), tablero.verificaciones.fichaElegida.getPosY());
    tablero.resetearColores();
    vistaTablero.resetearColores();
  }

  public void mostrarHistorialVista() {
    vistaTablero.mostrarHistorialPartida(tablero.historialPartida);
  }

  public void darAccionBotones() {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        vistaTablero.cuadro[i][j].addActionListener(this);
      }
    }
  }

  public void actualizarVista() {
    // Actualizar la vista de acuerdo a la configuración actual del tablero
    vistaTablero.resetearColores(); // Resetear los colores de los botones del tablero
    tablero.resetearColores();
    for (Fichas ficha : tablero.jugador1.fichas) {
      int posX = ficha.getPosX();
      int posY = ficha.getPosY();
      vistaTablero.actualizar(posX, posY, ficha);
    }
    for (Fichas ficha : tablero.jugador2.fichas) {
      int posX = ficha.getPosX();
      int posY = ficha.getPosY();
      vistaTablero.actualizar(posX, posY, ficha);
    }
  }

  public void resaltarMovimientos(ArrayList<String> r) {
    vistaTablero.resaltarMovimientos(r);
  }

  public void esperarTurno() {
    while (true) {
      try {
        StringTokenizer separador = new StringTokenizer(
            cliente.leerDatosServidor());
        int x = Integer.parseInt(separador.nextToken());
        int y = Integer.parseInt(separador.nextToken());
        // Recibe que el turno es 0
        int turno = Integer.parseInt(separador.nextToken());
        int Fx = Integer.parseInt(separador.nextToken());
        int Fy = Integer.parseInt(separador.nextToken());
        System.out.println("cliente recibe:" + x + y + turno + Fx + Fy);

        Fichas f = null;
        // Si el turno es 0 es que debe borrar algo del otro equipo
        if (turno == 0) {
          for (Fichas fichas : tablero.jugador2.fichas) {
            if (fichas.getPosY() == Fx && fichas.getPosX() == Fy) {
              f = fichas;
            }
          }
        }
        tablero.verificaciones.VerificarPosiblesMovimientos(f, Fx, Fy, tablero, this, "cliente");
        // verificarFichaPresionada(f, Fx, Fy);
        tablero.verificaciones.verificarMovimientoAmarillo(x, y, this, tablero, "cliente");
        // SE SUPONE QUE YA DEBIO CAMBIAR EL TURNO
        if (tablero.getTurno() == 1) {
          activarCuadros();
          break;
        }
      } catch (Exception e) {
        System.out.println("No hay dato todavía");
      }
    }
  }

  private void activarCuadros() {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        vistaTablero.cuadro[i][j].setEnabled(true);
      }
    }
  }

  @Override
  public void run() {
    esperarTurno();
  }
}
