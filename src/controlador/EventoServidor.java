package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import modelo.Tablero.Tablero;
import modelo.fichas.Fichas;
import modelo.fichas.Peon;
import modelo.fichas.Rey;
import modelo.fichas.Torre;
import modelo.jugadores.Jugadores;
import sockets.Servidor;
import vistaConexion.TableroServidor;

public class EventoServidor implements ActionListener, Runnable {

  private TableroServidor vistaTablero;
  private Tablero tablero;
  private Fichas fichaSeleccionada;
  Fichas fichaElegida;
  private int cacheX, cacheY;
  private int fsX, fsY;
  public boolean nn = false, nn2 = false;
  public boolean jaqueNegro = false, jaqueBlanco = false, banderaJaque = false;

  public ArrayList<Fichas> arrExtra = new ArrayList<>();

  public Servidor servidor;

  public EventoServidor(
    TableroServidor vistaTablero,
    Tablero tablero,
    Servidor servidor
  ) {
    this.vistaTablero = vistaTablero;
    this.tablero = tablero;
    this.servidor = servidor;
    this.vistaTablero.setVisible(true);
    this.vistaTablero.agregarFichas();
    this.vistaTablero.setFocusable(true);
    this.servidor.limpiarSalida();

    // Agregar ActionListener a cada botón en el tablero
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        vistaTablero.cuadro[i][j].addActionListener(this);
      }
    }
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
            tablero
          );

          // Si ya hay una ficha seleccionada, intentar moverla al cuadro presionado
          if (vistaTablero.cuadro[i][j].getBackground() == Color.YELLOW) {
            //Envio datos con turno 0
            servidor.enviarDatoCliente(
              i + " " + j + " " + tablero.turno + " " + fsX + " " + fsY
            );
            //cambia el turno a 1
            verificarMovimientoCuadroAmarillo(i, j);
            //limpia
            servidor.limpiarSalida();
          } else {
            if (tablero.getTurno() == 0) {
              verificarFichaPresionada(f, i, j);
              fsX = i;
              fsY = j;
            }
          }
          return; // Salir del bucle cuando se encuentre el botón presionado
        }
      }
    }
  }

  public void actualizarVista() {
    // Actualizar la vista de acuerdo a la configuración actual del tablero
    vistaTablero.resetearColores(); // Resetear los colores de los botones del tablero

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

  public boolean verificarJaque(int turnoo) {
    if (tablero.verificaciones.estaEnJaque(turnoo, tablero)) {
      if (turnoo == 1) {
        JOptionPane.showMessageDialog(null, "¡El rey Blanco está en jaque!");
        for (Fichas ficha : tablero.jugador2.fichas) {
          if (ficha instanceof Rey) {
            fichaElegida = ficha; // Devolver la instancia del rey
          }
        }
        vistaTablero.ponerJaque(
          tablero.jaqueBlanco,
          tablero.jaqueNegro,
          "blanco",
          fichaElegida.getPosX(),
          fichaElegida.getPosY()
        );
        vistaTablero.ponerJaque(
          tablero.jaqueBlanco,
          tablero.jaqueNegro,
          "negro",
          fichaElegida.getPosX(),
          fichaElegida.getPosY()
        );
        jaqueNegro = true;
        banderaJaque = true;
        return true;
      } else {
        JOptionPane.showMessageDialog(null, "¡El rey Negro está en jaque!");
        for (Fichas ficha : tablero.jugador1.fichas) {
          if (ficha instanceof Rey) {
            fichaElegida = ficha; // Devolver la instancia del rey
          }
        }
        vistaTablero.ponerJaque(
          tablero.jaqueBlanco,
          tablero.jaqueNegro,
          "negro",
          fichaElegida.getPosX(),
          fichaElegida.getPosY()
        );
        vistaTablero.ponerJaque(
          tablero.jaqueBlanco,
          tablero.jaqueNegro,
          "blanco",
          fichaElegida.getPosX(),
          fichaElegida.getPosY()
        );
        jaqueBlanco = true;
        banderaJaque = true;
        return true;
      }
    }
    return false;
  }

  public boolean cambioVistaEnroque(
    Fichas fichaSeleccionada,
    int i,
    int j,
    TableroServidor vistaTablero
  ) {
    Jugadores equipo = (tablero.getTurno() == 1)
      ? tablero.jugador1
      : tablero.jugador2;
    if (fichaSeleccionada instanceof Rey) {
      Rey rey = (Rey) fichaSeleccionada;
      if (rey.enroque == true) {
        // Eroque largo
        if (j < (rey.getPosX() - 1) && i == rey.getPosY()) {
          int cachexx = rey.getPosX();
          int cacheyy = rey.getPosY();
          vistaTablero.eliminarDeVista(cacheyy, cachexx);
          tablero.moverFicha(fichaSeleccionada, i, j);
          actualizarVista();
          for (Fichas fichas : equipo.getFichas()) {
            if (fichas instanceof Torre) {
              Torre tor = ((Torre) fichas);
              if ((tor.getPosX() == 0)) {
                cachexx = tor.getPosX();
                cacheyy = tor.getPosY();
                vistaTablero.eliminarDeVista(cachexx, cacheyy);
                tablero.moverFicha(tor, i, tor.getPosX() + 3);
                return true;
              }
            }
          }
        }
        // Eroque corto
        else if (j > (rey.getPosX() + 1) && i == rey.getPosY()) {
          int cachexx = rey.getPosX();
          int cacheyy = rey.getPosY();
          vistaTablero.eliminarDeVista(cacheyy, cachexx);
          tablero.moverFicha(fichaSeleccionada, i, j);
          actualizarVista();
          for (Fichas fichas : equipo.getFichas()) {
            if (fichas instanceof Torre) {
              Torre tor = ((Torre) fichas);
              if ((tor.getPosX() == 7)) {
                cachexx = tor.getPosX();
                cacheyy = tor.getPosY();
                vistaTablero.eliminarDeVista(cachexx, cacheyy);
                tablero.moverFicha(tor, i, tor.getPosX() - 2);
                return true;
              }
            }
          }
        }
        actualizarVista();
      }
    }
    return false;
  }

  public void verificarMovimientoCuadroAmarillo(int i, int j) {
    // Mover la ficha seleccionada al cuadro amarillo
    vistaTablero.eliminarDeVista(cacheY, cacheX);
    // CAMBIO DE VISTA ENROQUE
    if (fichaSeleccionada instanceof Rey) {
      if (!cambioVistaEnroque(fichaSeleccionada, i, j, vistaTablero)) {
        vistaTablero.eliminarDeVista(cacheY, cacheX);
        arrExtra = tablero.moverFicha(fichaSeleccionada, i, j);
        actualizarVista();
      }
    } else if (fichaSeleccionada instanceof Peon) {
      Peon peon = (Peon) fichaSeleccionada;
      if (peon.banderaPeonAlPaso) {
        String[] pos = peon.movimeintoPeonAlPaso.get(0).split(" ");
        int x = Integer.parseInt(pos[0]);
        int y = Integer.parseInt(pos[1]);
        if (x == i && y == j) {
          Peon peonsito = (Peon) tablero.historialFichas.get(
            tablero.historialFichas.size() - 1
          );
          if (tablero.getTurno() == 0) {
            tablero.jugador1.fichas.remove(peonsito);
          } else {
            tablero.jugador2.fichas.remove(peonsito);
          }
          vistaTablero.eliminarDeVista(peonsito.getPosX(), peonsito.getPosY());
        }
      }
      arrExtra = tablero.moverFicha(fichaSeleccionada, i, j);
    } else {
      arrExtra = tablero.moverFicha(fichaSeleccionada, i, j);
    }
    fichaSeleccionada.movio = true;

    Fichas fichaX = arrExtra.get(0);
    if (fichaX != null) {
      vistaTablero.eliminarDeVista(fichaX.getPosX(), fichaX.getPosY());
    }

    vistaTablero.banderaJaque_blancaa = false;
    vistaTablero.banderaJaque_negras = false;
    vistaTablero.quitarJaque();

    // Actualizar la vista para reflejar el movimiento
    actualizarVista();

    // Para coronación del peón
    if (fichaSeleccionada instanceof Peon) {
      if (((Peon) fichaSeleccionada).alcanzoExtremoTablero(i, j)) {
        tablero.eliminarFicha(fichaSeleccionada);
        vistaTablero.eliminarDeVista(
          fichaSeleccionada.getPosX(),
          fichaSeleccionada.getPosY()
        );

        String nn = vistaTablero.coronacionPieza(
          tablero.getTurno(),
          fichaSeleccionada.getPosY(),
          fichaSeleccionada.getPosX()
        );
        tablero.crearFichaNueva(
          nn,
          fichaSeleccionada.getPosX(),
          fichaSeleccionada.getPosY()
        );
      }
    }

    if (tablero.getTurno() == 1) {
      if (tablero.jaqueBlanco == false) {
        vistaTablero.banderaJaque_blancaa = false;
      }
      if (tablero.jaqueNegro == false) {
        vistaTablero.banderaJaque_negras = false;
      }

      vistaTablero.resetearColores();
      // Verificar Jaque
      nn = verificarJaque(1);
    } else {
      if (tablero.jaqueBlanco == false) {
        vistaTablero.banderaJaque_blancaa = false;
      }
      if (tablero.jaqueNegro == false) {
        vistaTablero.banderaJaque_negras = false;
      }

      vistaTablero.resetearColores();
      nn2 = verificarJaque(0);
    }
    if (!nn && !nn2) {
      banderaJaque = false;
      vistaTablero.resetearColores();
    }
    // Actualizar historial y Cambiar turno
    vistaTablero.mostrarHistorialPartida(tablero.historialPartida);
    //if (tablero.getTurno() == 0) {
    tablero.turno = (tablero.turno + 1) % 2;

    //}

    // SE VERIFICA JAQUE MATE
    if (banderaJaque) {
      if (tablero.verificaciones.detectarJaqueMate(tablero).isEmpty()) {
        if (tablero.getTurno() == 0) {
          JOptionPane.showMessageDialog(
            null,
            "Jaque mate al equipo blanco. Gana el equipo negro"
          );
        } else {
          JOptionPane.showMessageDialog(
            null,
            "Jaque mate al equipo negro. Gana el equipo blanco"
          );
        }
        System.exit(0);
      }
    }
    // Limpiar la ficha seleccionada
    fichaSeleccionada = null;

    if (tablero.turno == 1) {
      new Thread(this).start();
    }
  }

  public void verificarFichaPresionada(Fichas f, int i, int j) {
    // Si no hay una ficha seleccionada, seleccionar la ficha presionada
    if (f != null) {
      actualizarVista();
      fichaSeleccionada = f;
      cacheX = i;
      cacheY = j;

      // Se tiene que verificar si no está en jaque la ficha entonces permitir
      // movimeinto
      // Si sí se encuentra en jaque entonces no permite movimiento
      if (banderaJaque) {
        if (
          tablero.verificaciones.esMovimientoValidoParaSalirDelJaque(
            f,
            i,
            j,
            tablero
          )
        ) {
          this.vistaTablero.resaltarMovimientos(
              tablero.fichasValidasSalvarJaque
            );
        }
      } else {
        if (!banderaJaque) {
          // Obtener los posibles movimientos de la ficha en esa posición
          f.movimientoFicha(
            (char) (i + 97) + " " + j,
            tablero,
            3,
            banderaJaque,
            0
          );
          this.vistaTablero.resaltarMovimientos(f.getLista());
        }
      }
    }
  }

  /**
   * Se ejecuta mientras el contrincante mueve las fichas.
   */
  private void esperarTurno() {
    while (true) {
      try {
        StringTokenizer separador = new StringTokenizer(
          servidor.leerDatosCliente()
        );
        int x = Integer.parseInt(separador.nextToken());
        int y = Integer.parseInt(separador.nextToken());
        int turno = Integer.parseInt(separador.nextToken());
        int Fx = Integer.parseInt(separador.nextToken());
        int Fy = Integer.parseInt(separador.nextToken());

        Fichas f = null;
        // Si el turno es 1 es que debe borrar algo del otro equipo
        if (turno == 1) {
          for (Fichas fichas : tablero.jugador1.fichas) {
            if (fichas.getPosY() == Fx && fichas.getPosX() == Fy) {
              f = fichas;
            }
          }
        }
        verificarFichaPresionada(f, Fx, Fy);
        verificarMovimientoCuadroAmarillo(x, y);

        //tablero.turno = turno;
        if (tablero.getTurno() == 0) {
          activarCuadros();
          break;
        }
      } catch (NumberFormatException e) {
        System.out.println("No hay dato todavía");
      }
    }
  }

  /**
   * Activa los cuadros despues del movimiento del contrincante.
   */
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
