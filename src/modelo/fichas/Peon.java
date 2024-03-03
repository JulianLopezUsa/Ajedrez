package modelo.fichas;

import java.util.ArrayList;
import modelo.Tablero.Tablero;
import modelo.jugadores.Jugadores;

/**
 * Clase que representa un peón en el juego de ajedrez.
 */
public class Peon extends Fichas {

  // Lista de movimientos posibles para el peón
  public ArrayList<String> listaDeMovimientos = new ArrayList<>();
  // Contador de movimientos
  public int contadorMov = 1;
  // Lista de desplazamientos
  ArrayList<String> desplazamientos = new ArrayList<>();
  // Variables de bandera para controlar ciertos movimientos
  public boolean bandera = false, bandera2 = false;
  // Variable para controlar la coronación del peón
  public int coronacion;

  /**
   * Constructor de la clase Peon.
   */
  public Peon(int posX, int posY, String color) {
    super(posX, posY, color);
    this.coronacion = 0;
  }

  /**
   * Método para calcular los movimientos posibles del peón.
   */
  @Override
  public void movimientoFicha(String posicionActual, Tablero tablero) {
    // Se limpian las listas de movimientos y desplazamientos
    desplazamientos.clear();
    listaDeMovimientos.clear();
    // Se obtiene la posición actual del peón
    String[] pos = posicionActual.split(" ");
    char letraF = pos[0].charAt(0);
    int numeroF = Integer.parseInt(pos[1]);
    int letraff = (int) letraF;

    // Se determinan los movimientos posibles según el turno del jugador
    if (tablero.getTurno() == 0) {
      // Movimientos para el jugador 1 (blancas)
      for (Fichas f : tablero.jugador1.fichas) {
        if (f.getPosX() == numeroF - 1 && f.getPosY() == (letraff - 1) - 97) {
          desplazamientos.add((letraff - 1) + " " + (numeroF - 1));
        } else if (f.getPosX() == numeroF + 1 && f.getPosY() == (letraff - 1) - 97) {
          desplazamientos.add((letraff - 1) + " " + (numeroF + 1));
        }
        if (f.getPosX() == numeroF && f.getPosY() == (letraff - 1) - 97) {
          bandera = true;
        } else if (f.getPosX() == numeroF && f.getPosY() == (letraff - 2) - 97) {
          bandera2 = true;
        }
      }
      for (Fichas f : tablero.jugador2.fichas) {
        if (f.getPosX() == numeroF && f.getPosY() == (letraff - 1) - 97) {
          bandera = true;
        } else if (f.getPosX() == numeroF && f.getPosY() == (letraff - 2) - 97) {
          bandera2 = true;
        }
      }

      if (!bandera) {
        desplazamientos.add((letraff - 1) + " " + numeroF);
        if (!movio && !bandera2) {
          desplazamientos.add((letraff - 2) + " " + numeroF);
        }
      }
      bandera2 = false;
      bandera = false;
    } else if (tablero.getTurno() == 1) {
      // Movimientos para el jugador 2 (negras)
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

    // Se verifican los movimientos posibles y se agregan a la lista
    for (String movimiento : desplazamientos) {
      String[] pos1 = movimiento.split(" ");
      int nuevaLetra = Integer.parseInt(pos1[0]);
      int nuevoNumero = Integer.parseInt(pos1[1]);
      if (letraff >= 97 && letraff <= 103 && tablero.getTurno() == 0) {
        if (nuevaLetra >= 97 &&
            nuevaLetra <= 104 &&
            nuevoNumero >= 0 &&
            nuevoNumero <= 7) {
          verificarOtrasFichas(tablero, nuevaLetra, nuevoNumero);
        }
      } else if (letraff >= 98 && letraff <= 104 && tablero.getTurno() == 1) {
        if (nuevaLetra >= 97 &&
            nuevaLetra <= 104 &&
            nuevoNumero >= 0 &&
            nuevoNumero <= 7) {
          verificarOtrasFichas(tablero, nuevaLetra, nuevoNumero);
        }
      }
    }
    // Se establece la lista de movimientos del peón y se verifica si hay un peón al paso
    setLista(listaDeMovimientos);
    verificarPeonAlPaso(tablero);
  }

  /**
   * Método para verificar la posibilidad de realizar el movimiento de peón al paso.
   */
  public void verificarPeonAlPaso(Tablero tablero) {

    // Para fichas blancas
    if(tablero.turno == 0){
      Jugadores oponente = tablero.jugador1;
   

      for (Fichas fichaOp : oponente.fichas){
        if (fichaOp instanceof Peon && fichaOp.getPosY()==3 && fichaOp.getContador()==1){
          if (this.getPosY()==3){
            System.out.println("Peon x: "+ fichaOp.getPosX()+" y:"+fichaOp.getPosY() +" cont:"+fichaOp.getContador());
            System.out.println("Peon seleccionado x: "+ this.getPosX()+" y:"+this.getPosY() +" cont:"+this.getContador());
  
            if ( fichaOp.getPosX() - this.getPosX() ==-1 ){
              listaDeMovimientos.add(((char)(this.getPosY()+'a'-1))+" "+(fichaOp.getPosX()));
            }
            if ( fichaOp.getPosX() - this.getPosX() ==1){
              System.out.println("RESTA = 1");
              System.out.println("LISTA MOV"+ listaDeMovimientos);
              System.out.println(((char)(this.getPosY()+'a'-1))+" "+(fichaOp.getPosX()));
              listaDeMovimientos.add(((char)(this.getPosY()+'a'-1))+" "+(fichaOp.getPosX()));
            }

          }
        }
      }
      
    }

    // Para fichas negras
    if(tablero.turno == 1){
      Jugadores oponente = tablero.jugador2;
   

      for (Fichas fichaOp : oponente.fichas){
        if (fichaOp instanceof Peon && fichaOp.getPosY()==4 && fichaOp.getContador()==1){
          if (this.getPosY()==4){
  
            if ( fichaOp.getPosX() - this.getPosX() ==-1 ){
              listaDeMovimientos.add(((char)(this.getPosY()+'a'+1))+" "+(fichaOp.getPosX()));
            }
            if ( fichaOp.getPosX() - this.getPosX() ==1){
              listaDeMovimientos.add(((char)(this.getPosY()+'a'+1))+" "+(fichaOp.getPosX()));
            }

          }
        }
      }
      
    }
  }

  /**
   * Método para verificar si hay otras fichas en la casilla adyacente.
   */
  public void verificarOtrasFichas(
      Tablero tablero,
      int nuevaLetra,
      int nuevoNumero) {
    Fichas ficha = tablero.hayFicha(
        nuevaLetra - 'a',
        nuevoNumero,
        tablero.getTurno());
    if (ficha == null || !ficha.getColor().equals(this.getColor())) {
      listaDeMovimientos.add((char) nuevaLetra + " " + nuevoNumero);
    }
  }

  // Getter y setter para la lista de movimientos
  @Override
  public ArrayList<String> getLista() {
    return super.getLista();
  }

  @Override
  public void setLista(ArrayList<String> listaDeMovimientos) {
    super.setLista(listaDeMovimientos);
  }

  // Getter y setter para la lista de desplazamientos
  public void setListaDeMovimientos(ArrayList<String> listaDeMovimientos) {
    this.listaDeMovimientos = listaDeMovimientos;
  }

  // Getter y setter para el contador de movimientos
  public void setContadorMov(int contadorMov) {
    this.contadorMov = contadorMov;
  }

  // Getter y setter para la coronación del peón
  public int getCoronacion() {
    return coronacion;
  }

  public void setCoronacion(int coronacion) {
    this.coronacion = coronacion;
  }
  
  // Método para verificar si el peón ha alcanzado el extremo del tablero
  public boolean alcanzoExtremoTablero(int i, int j) {
    return (i == 0 || i == 7);
  }

}
