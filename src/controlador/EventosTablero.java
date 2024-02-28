package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Tablero.Tablero;
import modelo.fichas.Fichas;
import modelo.fichas.Peon;
import modelo.fichas.Rey;
import vista.VistaTablero;

public class EventosTablero implements ActionListener {

  private VistaTablero tablero;
  private Tablero tablero2;
  private Fichas fichaSeleccionada;
  Fichas fichaElegida;
  private int cacheX, cacheY;
  public boolean jaqueNegro = false, jaqueBlanco = false, banderaJaque = false;
  public ArrayList<Fichas> arrExtra = new ArrayList<>();

  public EventosTablero(VistaTablero tablero, Tablero tablero2) {
    this.tablero = tablero;
    this.tablero2 = tablero2;
    this.tablero.setVisible(true);
    this.tablero.agregarFichas();

    // Agregar ActionListener a cada botón en el tablero
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        tablero.cuadro[i][j].addActionListener(this);
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Verificar cuál botón se ha presionado
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (e.getSource() == this.tablero.cuadro[i][j]) {
          // Verificar si hay una ficha en el botón presionado
          Fichas f = tablero2.hayFicha(i, j, tablero2.getTurno());

          // Si ya hay una ficha seleccionada, intentar moverla al cuadro presionado
          if (tablero.cuadro[i][j].getBackground() == Color.YELLOW) {
            // Mover la ficha seleccionada al cuadro amarillo
            tablero.eliminarDeVista(cacheY, cacheX);
            arrExtra = tablero2.moverFicha(fichaSeleccionada, i, j);

            Fichas fichaX = arrExtra.get(0);
            if (fichaX != null) {
              tablero.eliminarDeVista(fichaX.getPosX(), fichaX.getPosY());
            }

            // Actualizar la vista para reflejar el movimiento
            actualizarVista();

            // Para coronación del peón
            if (fichaSeleccionada instanceof Peon) {
              if (((Peon) fichaSeleccionada).alcanzoExtremoTablero(i, j)) {
                tablero2.eliminarFicha(fichaSeleccionada);
                tablero.eliminarDeVista(
                  fichaSeleccionada.getPosX(),
                  fichaSeleccionada.getPosY()
                );

                String nn = tablero.coronacionPieza(
                  tablero2.getTurno(),
                  fichaSeleccionada.getPosY(),
                  fichaSeleccionada.getPosX()
                );
                tablero2.crearFichaNueva(
                  nn,
                  fichaSeleccionada.getPosX(),
                  fichaSeleccionada.getPosY()
                );
              }
            }

            if (tablero2.jaqueBlanco == false) {
              tablero.banderaJaque_blancaa = false;
            }
            if (tablero2.jaqueNegro == false) {
              tablero.banderaJaque_negras = false;
            }

            tablero.resetearColores();
            // Verificar Jaque
            boolean nn= verificarJaque(0);
            if (tablero2.jaqueBlanco == false) {
              tablero.banderaJaque_blancaa = false;
            }
            if (tablero2.jaqueNegro == false) {
              tablero.banderaJaque_negras = false;
            }
            tablero.resetearColores();
            boolean nn2 =verificarJaque(1);

            if(!nn && !nn2){
                banderaJaque = false;
            }
            // Cambiar turno
            tablero2.turno = (tablero2.turno + 1) % 2;

            // Limpiar la ficha seleccionada
            fichaSeleccionada = null;
          } else {
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
                if (esMovimientoValidoParaSalirDelJaque(f, i, j)) {
                  this.tablero.resaltarMovimientos(f);
                }
              } else {
                System.out.println("BANDERAAAAAAA JAQUEEEEE" + banderaJaque);
                if (!banderaJaque) {
                  // Obtener los posibles movimientos de la ficha en esa posición
                  f.movimientoFicha((char) (i + 97) + " " + j, tablero2, 3);
                  this.tablero.resaltarMovimientos(f);
                }
              }
              // Cambiar el color de los botones correspondientes a los movimientos válidos
              // this.tablero.resaltarMovimientos(f);
            }
          }
          return; // Salir del bucle cuando se encuentre el botón presionado
        }
      }
    }
  }

  public void actualizarVista() {
    // Actualizar la vista de acuerdo a la configuración actual del tablero
    tablero.resetearColores(); // Resetear los colores de los botones del tablero

    for (Fichas ficha : tablero2.jugador1.fichas) {
      int posX = ficha.getPosX();
      int posY = ficha.getPosY();
      tablero.actualizar(posX, posY, ficha);
    }
    for (Fichas ficha : tablero2.jugador2.fichas) {
      int posX = ficha.getPosX();
      int posY = ficha.getPosY();
      tablero.actualizar(posX, posY, ficha);
    }
  }

  public boolean verificarJaque(int turnoo) {
    if (tablero2.estaEnJaque(turnoo)) {
      if (turnoo == 1) {
        JOptionPane.showMessageDialog(null, "¡El rey negro está en jaque!");
        for (Fichas ficha : tablero2.jugador2.fichas) {
          if (ficha instanceof Rey) {
            fichaElegida = ficha; // Devolver la instancia del rey
          }
        }
        tablero.ponerJaque(
          tablero2.jaqueBlanco,
          tablero2.jaqueNegro,
          "negro",
          fichaElegida.getPosX(),
          fichaElegida.getPosY()
        );
        jaqueNegro = true;
        banderaJaque = true;
        return true;
      } else {
        JOptionPane.showMessageDialog(null, "¡El rey blanco está en jaque!");
        for (Fichas ficha : tablero2.jugador1.fichas) {
          if (ficha instanceof Rey) {
            fichaElegida = ficha; // Devolver la instancia del rey
          }
        }
        tablero.ponerJaque(
          tablero2.jaqueBlanco,
          tablero2.jaqueNegro,
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

  // Método para verificar si un movimiento saca al rey del jaque
  private boolean esMovimientoValidoParaSalirDelJaque(
    Fichas ficha,
    int i,
    int j
  ) {
    ficha.movimientoFicha((char) (i + 97) + " " + j, tablero2, 3);
    // Verificar si los movimientos de la ficha quitan el jaque o no
    System.out.println(ficha.getLista());
    ArrayList<String> movimientos = ficha.getLista();
    for (String movimiento : movimientos) {
      String[] pos = movimiento.split(" ");
      int moveX = pos[0].charAt(0) - 'a';
      int moveY = Integer.parseInt(pos[1]);

      // Guardar la ficha en la posición a la que se moverá para verificar si pone al
      // rey en jaque

      // Mover la ficha temporalmente
      int originalX = i;
      int originalY = j;

      Fichas fichaEliminada = tablero2.SimulacionMoverFicha(
        ficha,
        tablero2,
        moveY,
        moveX
      );

      System.out.println("\n"+tablero2.jugador1.getFichas());
      System.out.println("\n"+tablero2.jugador2.getFichas());

      // Verificar si el rey está en jaque después del movimiento
      boolean jaqueDespuesDeMovimiento = tablero2.estaEnJaque2((tablero2.getTurno()==1)? 0:1);

      System.out.println("Jaque?" + jaqueDespuesDeMovimiento);
      // Deshacer el movimiento temporal
      tablero2.SimulacionRetrocesoFicha(
        fichaEliminada,
        ficha,
        tablero2,
        originalY,
        originalX
      );
      // Si el movimiento no pone al rey en jaque, es un movimiento válido para salir
      // del jaque
      if (!jaqueDespuesDeMovimiento) {
        this.jaqueBlanco = true;
        return true;
      }
    }

    this.jaqueBlanco = false;
    return false;
  }
}
