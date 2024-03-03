package modelo.fichas;

import java.util.ArrayList;
import modelo.Tablero.Tablero;

/**
 *
 * @author Laura
 */
public class Peon extends Fichas {

  public ArrayList<String> listaDeMovimientos = new ArrayList<>();
  public int contadorMov = 1;
  ArrayList<String> desplazamientos = new ArrayList<>();
  public boolean bandera = false, bandera2 = false;
  public int coronacion; // Variable para controlar la coronación del peón

  public Peon(int posX, int posY, String color) {
    super(posX, posY, color);
    this.coronacion = 0;
  }

  @Override
  public void movimientoFicha(String posicionActual, Tablero tablero, int turno, boolean banderaJaque) {
    desplazamientos.clear();
    listaDeMovimientos.clear();
    String[] pos = posicionActual.split(" ");
    char letraF = pos[0].charAt(0);
    int numeroF = Integer.parseInt(pos[1]);

    // Se castea a número la letra para trabajar con ASCII
    int letraff = (int) letraF;

    int tt;
    if (turno!=3){
      tt = turno;
      //letraff=letraff-1;
    }else{
      tt = tablero.getTurno();
    }
    // Array con los posibles desplazamientos que en este caso son solo derecho
    //movimeinto de las de abajo
    if (tt == 0) {
      for (Fichas f : tablero.jugador1.fichas) {
        // SI hay alguien del otro equipo en mi esquina agregeuelo a posibles movimientos
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
      //Verificar si hay fichas de mismo equipo
      for (Fichas f : tablero.jugador2.fichas) {
        if (f.getPosX() == numeroF && f.getPosY() == (letraff - 1) - 97) {
          bandera = true;
          
        } else if (f.getPosX() == numeroF && f.getPosY() == (letraff - 2) - 97) {
          bandera2 = true;
        }
      }
      
      //SI no hay alguien al frente me meuvo
      if (!bandera) {
        desplazamientos.add((letraff - 1) + " " + numeroF);
        //Si no se ha movido y no hay nada en dos casillas, se mueve
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

      //VERIFICAR SI LA POSICION ACTUAL ESTÁ DENTRO DE LOS RANGOS
      if (letraff >= 97 && letraff <= 103 && tt == 0) {
        // Verificar si el movimiento está dentro del tablero
        if (nuevaLetra >= 97 &&
            nuevaLetra <= 104 &&
            nuevoNumero >= 0 &&
            nuevoNumero <= 7) {
          verificarOtrasFichas(tablero, nuevaLetra, nuevoNumero,tt);
          // listaDeMovimientos.add((char) nuevaLetra + " " + nuevoNumero);
        }
      } else if (letraff >= 98 && letraff <= 104 && tt == 1) {
        // Verificar si el movimiento está dentro del tablero
        if (nuevaLetra >= 97 &&
            nuevaLetra <= 104 &&
            nuevoNumero >= 0 &&
            nuevoNumero <= 7) {
          verificarOtrasFichas(tablero, nuevaLetra, nuevoNumero,tt);
          // listaDeMovimientos.add((char) nuevaLetra + " " + nuevoNumero);
        }
      }
    }
    setLista(listaDeMovimientos);
  }

  public void verificarOtrasFichas(Tablero tablero,int nuevaLetra,int nuevoNumero, int tt) {
    // Verificar si hay una ficha en la casilla adyacente
    Fichas ficha = tablero.hayFicha(nuevaLetra - 'a',nuevoNumero,tt);
    
 
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