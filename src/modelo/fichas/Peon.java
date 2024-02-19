/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    int[][] desplazamientos;

    public Peon(int posX, int posY, String color) {
        super(posX, posY, color);
    }

    @Override
    public void movimientoFicha(String posicionActual, Tablero tablero) {
        listaDeMovimientos.clear();
        String[] pos = posicionActual.split(" ");

        char letraF = pos[0].charAt(0);
        int numeroF = Integer.parseInt(pos[1]);

        // Se castea a número la letra para trabajar con ASCII
        int letraff = (int) letraF;

        // Array con los posibles desplazamientos que en este caso son solo derecho
        if (tablero.getTurno() == 0) {
            if (contadorMov == 2) {
                desplazamientos = new int[][] { { letraff - 1, numeroF } };
            } else {
                contadorMov++;
                desplazamientos = new int[][] { { letraff - 2, numeroF }, { letraff - 1, numeroF } };
            }
        } else if(tablero.getTurno()==1){
            if (contadorMov == 2) {
                desplazamientos = new int[][] { { letraff + 1, numeroF } };
            } else {
                contadorMov++;
                desplazamientos = new int[][] { { letraff + 2, numeroF }, { letraff + 1, numeroF } };
            }
        }

        // Validar cada posible movimiento
        for (int[] movimiento : desplazamientos) {
            int nuevaLetra = movimiento[0];
            int nuevoNumero = movimiento[1];

            if (letraff >= 97 && letraff <= 103 && tablero.getTurno() == 0) {
                // Verificar si el movimiento está dentro del tablero
                if (nuevaLetra >= 97 && nuevaLetra <= 104 && nuevoNumero >= 0 && nuevoNumero <= 7) {
                    // verificarOtrasFichas(tablero, nuevaLetra, numeroF);
                    listaDeMovimientos.add((char) nuevaLetra + " " + nuevoNumero);
                }
            } else if (letraff >= 98 && letraff <= 104 && tablero.getTurno() == 1) {
                // Verificar si el movimiento está dentro del tablero
                if (nuevaLetra >= 97 && nuevaLetra <= 104 && nuevoNumero >= 0 && nuevoNumero <= 7) {
                    // verificarOtrasFichas(tablero, nuevaLetra, numeroF);
                    listaDeMovimientos.add((char) nuevaLetra + " " + nuevoNumero);
                }
            }
        }
        setLista(listaDeMovimientos);
        System.out.println(listaDeMovimientos);
    }

    public void verificarOtrasFichas(Tablero tablero, int nuevaLetra, int numeroF) {
        // Verificar si no hay una ficha en la casilla delante del peón
        boolean noHayFichaDelante = true;
        // Revisar si hay una ficha en la posición delante del peón
        if (tablero.getTurno() == 0) {
            for (Fichas ficha : tablero.jugador1.fichas) {
                if (ficha.getPosX() == nuevaLetra && ficha.getPosY() == numeroF) {
                    noHayFichaDelante = false;
                    break;
                }
            }
        }else if(tablero.getTurno() == 1){
            for (Fichas ficha : tablero.jugador2.fichas) {
                if (ficha.getPosX() == nuevaLetra && ficha.getPosY() == numeroF) {
                    noHayFichaDelante = false;
                    break;
                }
            }
        }
        // Si no hay una ficha delante, agregar el movimiento a la lista
        if (noHayFichaDelante) {
            listaDeMovimientos.add((char) nuevaLetra + " " + numeroF);
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
}
