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
    public void movimientoFicha(String posicionActual, int turno) {
        listaDeMovimientos.clear();
        String[] pos = posicionActual.split(" ");

        char letraF = pos[0].charAt(0);
        int numeroF = Integer.parseInt(pos[1]);

        // Se castea a número la letra para trabajar con ASCII
        int letraff = (int) letraF;

        // Array con los posibles desplazamientos que en este caso son solo derecho
        if (turno == 0) {
            if (contadorMov == 2) {
                desplazamientos = new int[][] { { letraff - 1, numeroF } };
            } else {
                contadorMov++;
                desplazamientos = new int[][] { { letraff - 2, numeroF }, { letraff - 1, numeroF } };
            }

        } else {
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

            if (letraff >= 97 && letraff <= 103 && turno==0) {
                // Verificar si el movimiento está dentro del tablero
                if (nuevaLetra >= 97 && nuevaLetra <= 104 && nuevoNumero >= 1 && nuevoNumero <= 8 ) {
                    listaDeMovimientos.add((char) nuevaLetra + " " + nuevoNumero);
                }
            }else  if (letraff >= 98 && letraff <= 104 && turno==1) {
                // Verificar si el movimiento está dentro del tablero
                if (nuevaLetra >= 97 && nuevaLetra <= 104 && nuevoNumero >= 1 && nuevoNumero <= 8 ) {
                    listaDeMovimientos.add((char) nuevaLetra + " " + nuevoNumero);
                }
            }
        }

        System.out.println(listaDeMovimientos);
    }


}
