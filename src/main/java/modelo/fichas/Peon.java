package modelo.fichas;

import java.util.ArrayList;
import modelo.Tablero.Tablero;


public class Peon extends Fichas {

  public ArrayList<String> listaDeMovimientos = new ArrayList<>();
  public int contadorMov = 1;
  ArrayList<String> desplazamientos = new ArrayList<>();
  public ArrayList<String> movimeintoPeonAlPaso = new ArrayList<>();
  public boolean bandera = false, bandera2 = false, banderaPeonAlPaso= false;
  public int coronacion; // Variable para controlar la coronación del peón

  public Peon(int posX, int posY, String color) {
    super(posX, posY, color);
    this.coronacion = 0;
  }

  @Override
  public void movimientoFicha(String posicionActual, Tablero tablero, int turno, boolean banderaJaque, int primeraVez) {
    desplazamientos.clear();
    listaDeMovimientos.clear();
    movimeintoPeonAlPaso.clear();
    banderaPeonAlPaso=false;
    String[] pos = posicionActual.split(" ");
    char letraF = pos[0].charAt(0);
    int numeroF = Integer.parseInt(pos[1]);

    // Se castea a número la letra para trabajar con ASCII
    int letraff = (int) letraF;

    int tt;
    if (turno != 3) {
      tt = turno;
      // letraff=letraff-1;
    } else {
      tt = tablero.getTurno();
    }
    // Array con los posibles desplazamientos que en este caso son solo derecho
    // movimeinto de las de abajo
    if (tt == 0) {
      for (Fichas f : tablero.jugador1.fichas) {
        // SI hay alguien del otro equipo en mi esquina agregeuelo a posibles
        // movimientos
        if (f.getPosX() == numeroF - 1 && f.getPosY() == (letraff - 1) - 97) {
          desplazamientos.add((letraff - 1) + " " + (numeroF - 1));
        } else if (f.getPosX() == numeroF + 1 && f.getPosY() == (letraff - 1) - 97) {
          desplazamientos.add((letraff - 1) + " " + (numeroF + 1));
        }
        // verficiar si hay fichas del otro equipo al frente en una y dos casillas
        if (f.getPosX() == numeroF && f.getPosY() == (letraff - 1) - 97) {

          bandera = true;
        } else if (f.getPosX() == numeroF && f.getPosY() == (letraff - 2) - 97) {
          bandera2 = true;
        }
      }
      // Verificar si hay fichas de mismo equipo
      for (Fichas f : tablero.jugador2.fichas) {
        if (f.getPosX() == numeroF && f.getPosY() == (letraff - 1) - 97) {
          bandera = true;

        } else if (f.getPosX() == numeroF && f.getPosY() == (letraff - 2) - 97) {
          bandera2 = true;
        }
      }

      // SI no hay alguien al frente me meuvo
      if (!bandera) {
        desplazamientos.add((letraff - 1) + " " + numeroF);
        // Si no se ha movido y no hay nada en dos casillas, se mueve
        if (!movio && !bandera2) {
          desplazamientos.add((letraff - 2) + " " + numeroF);
        }

      }
      bandera2 = false;
      bandera = false;
    } else if (tt == 1) {
      for (Fichas f : tablero.jugador2.fichas) {
        if (f.getPosX() == numeroF - 1 && f.getPosY() == (letraff + 1) - 97) {
          desplazamientos.add((letraff + 1) + " " + (numeroF - 1));
        } else if (f.getPosX() == numeroF + 1 && f.getPosY() == (letraff + 1) - 97) {
          desplazamientos.add((letraff + 1) + " " + (numeroF + 1));
        }
        if (f.getPosX() == numeroF && f.getPosY() == (letraff + 1) - 97) {
          bandera = true;
        } else if (f.getPosX() == numeroF && f.getPosY() == (letraff + 2) - 97) {
          bandera2 = true;
        }
      }
      for (Fichas f : tablero.jugador1.fichas) {
        if (f.getPosX() == numeroF && f.getPosY() == (letraff + 1) - 97) {
          bandera = true;
        } else if (f.getPosX() == numeroF && f.getPosY() == (letraff + 2) - 97) {
          bandera2 = true;
        }
      }
      if (!bandera) {
        desplazamientos.add((letraff + 1) + " " + numeroF);
        if (!movio && !bandera2) {

          desplazamientos.add((letraff + 2) + " " + numeroF);
        }
      }
      bandera = false;
      bandera2 = false;
    }

    // Validar cada posible movimiento
    for (String movimiento : desplazamientos) {
      String[] pos1 = movimiento.split(" ");

      int nuevaLetra = Integer.parseInt(pos1[0]);

      int nuevoNumero = Integer.parseInt(pos1[1]);
      // int nuevaLetra = movimiento[0];
      // int nuevoNumero = movimiento[1];

      // VERIFICAR SI LA POSICION ACTUAL ESTÁ DENTRO DE LOS RANGOS
      if (letraff >= 97 && letraff <= 103 && tt == 0) {
        // Verificar si el movimiento está dentro del tablero
        if (nuevaLetra >= 97 &&
            nuevaLetra <= 104 &&
            nuevoNumero >= 0 &&
            nuevoNumero <= 7) {
          verificarOtrasFichas(tablero, nuevaLetra, nuevoNumero, tt);
          // listaDeMovimientos.add((char) nuevaLetra + " " + nuevoNumero);
        }
      } else if (letraff >= 98 && letraff <= 104 && tt == 1) {
        // Verificar si el movimiento está dentro del tablero
        if (nuevaLetra >= 97 &&
            nuevaLetra <= 104 &&
            nuevoNumero >= 0 &&
            nuevoNumero <= 7) {
          verificarOtrasFichas(tablero, nuevaLetra, nuevoNumero, tt);
          // listaDeMovimientos.add((char) nuevaLetra + " " + nuevoNumero);
        }
      }
    }

    // PEÓN AL PASO
    peonAlPaso(tablero);

    // PARA NEGAR MOVIMEINTOS QUE PRODUZCAN JAQUE AL MISMO EQUIPO
    ArrayList<String> Arr = new ArrayList<>();
    if (primeraVez == 0) {
      Arr = movimeintosNoProducenJaque(tablero);
    }
    for (String mov : Arr) {
      listaDeMovimientos.remove(mov);
    }
    setLista(listaDeMovimientos);
  }

  public void peonAlPaso(Tablero tablero) {
    // Si es turno del equipo blanco y la ficha está 3 casillas arriba
    if (this.getColor() == "blanco" && this.getPosY() == 3) {
      // Si el último movimiento fue del peon del otro equipo
      if (tablero.historialFichas.get(tablero.historialFichas.size() - 1) instanceof Peon) {
        Peon peon = (Peon) tablero.historialFichas.get(tablero.historialFichas.size() - 1);
        /// Si está en la mima fila que está la ficha y no se habia movido hasta el
        /// movimiento anterior
        if (peon.getPosY() == this.getPosY() && !tablero.historialJugadas.get(tablero.historialJugadas.size()-1)) {
          // Verificar si está a la derecha o a la izquierda
          if (peon.getPosX() < this.getPosX()) {
            // Izquierda
            listaDeMovimientos.add((char)((this.getPosY() - 1)+'a') + " " + (this.getPosX() - 1));
            movimeintoPeonAlPaso.add((this.getPosY() - 1)+" "+ (this.getPosX() - 1));
          } else {
            // Derecha
            listaDeMovimientos.add((char)((this.getPosY() - 1)+'a') + " " + (this.getPosX() + 1));
            movimeintoPeonAlPaso.add((this.getPosY() - 1)+" "+ (this.getPosX() + 1));
          }
          banderaPeonAlPaso=true;
          
        }
      }
      // Si es turno del equipo negro y la ficha está 4 casillas abajo
    } else if (this.getColor() == "negro" && this.getPosY() == 4) {
      // Si el último movimiento fue del peon del otro equipo
      if (tablero.historialFichas.get(tablero.historialFichas.size() - 1) instanceof Peon) {
        Peon peon = (Peon) tablero.historialFichas.get(tablero.historialFichas.size() - 1);
        /// Si está en la mima fila que está la ficha y no se habia movido hasta el
        /// movimiento anterior
        if (peon.getPosY() == this.getPosY() && !tablero.historialJugadas.get(tablero.historialJugadas.size()-1)) {
          // Verificar si está a la derecha o a la izquierda
          if (peon.getPosX() < this.getPosX()) {
            // Izquierda
            listaDeMovimientos.add((char)((this.getPosY() + 1)+'a') + " " + (this.getPosX() - 1));
            movimeintoPeonAlPaso.add((this.getPosY() + 1)+" "+ (this.getPosX() - 1));
          } else {
            // Derecha
            listaDeMovimientos.add((char)((this.getPosY() + 1)+'a') + " " + (this.getPosX() + 1));
            movimeintoPeonAlPaso.add((this.getPosY() + 1)+" "+ (this.getPosX() + 1));
          }
          banderaPeonAlPaso=true;
        }
      }
    }
  }

  public ArrayList<String> movimeintosNoProducenJaque(Tablero tablero2) {
    ArrayList<String> movimientosSegurosPeon = new ArrayList<>();
    for (String movimiento : listaDeMovimientos) {
      String[] pos = movimiento.split(" ");
      int moveX = pos[0].charAt(0) - 'a';
      int moveY = Integer.parseInt(pos[1]);

      // Mover la ficha temporalmente
      int originalX = this.getPosX();
      int originalY = this.getPosY();

      Fichas fichaEliminada = tablero2.verificaciones.SimulacionMoverFicha(
          this,
          tablero2,
          moveY,
          moveX);

      // Verificar si el rey está en jaque después del movimiento
      boolean jaqueDespuesDeMovimiento = tablero2.verificaciones.estaEnJaque3((tablero2.getTurno() == 1) ? 0 : 1,tablero2);
      // Deshacer el movimiento temporal
      tablero2.verificaciones.SimulacionRetrocesoFicha(
          fichaEliminada,
          this,
          tablero2,
          originalX,
          originalY);

      // Si el movimiento no pone al rey en jaque, es un movimiento válido para salir
      // del jaque
      if (jaqueDespuesDeMovimiento) {
        // aqui se agrega movimiento que quita jaque
        movimientosSegurosPeon.add(movimiento);
      }
    }
    return movimientosSegurosPeon;
  }

  public void verificarOtrasFichas(Tablero tablero, int nuevaLetra, int nuevoNumero, int tt) {
    // Verificar si hay una ficha en la casilla adyacente
    Fichas ficha = tablero.verificaciones.hayFicha(nuevaLetra - 'a', nuevoNumero, tt,tablero);
    if (ficha == null || !ficha.getColor().equals(this.getColor())) {
      listaDeMovimientos.add((char) (nuevaLetra) + " " + nuevoNumero);
    }
  }

  @Override
  public ArrayList<String> getLista() {
    return super.getLista();
  }

  @Override
  public void setLista(ArrayList<String> listaDeMovimientos) {
    super.setLista(listaDeMovimientos);
  }

  public void setListaDeMovimientos(ArrayList<String> listaDeMovimientos) {
    this.listaDeMovimientos = listaDeMovimientos;
  }

  public void setContadorMov(int contadorMov) {
    this.contadorMov = contadorMov;
  }

  @Override
  public void setMovio(boolean movio) {
    this.movio = movio;
  }

  // Getter y setter para coronacion
  public int getCoronacion() {
    return coronacion;
  }

  public void setCoronacion(int coronacion) {
    this.coronacion = coronacion;
  }

  public boolean alcanzoExtremoTablero(int i, int j) {
    return (i == 0 || i == 7);
  }

}