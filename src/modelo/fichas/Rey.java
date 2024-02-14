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
public class Rey extends Fichas {
    public ArrayList<String> listaDeMovimientos = new ArrayList<>();

    public Rey(int posX, int posY, String color) {
        super(posX, posY, color);
    }

    @Override
    public void movimientoFicha(String posicionActual, int turno) {
        String[] pos = posicionActual.split(" ");

        char letraF = pos[0].charAt(0);
        int numeroF = Integer.parseInt(pos[1]);

        // Calcular movimientos del rey
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nuevaLetra = letraF + i;
                int nuevoNumero = numeroF + j;
                // Verificar si el movimiento está dentro del tablero y no es la misma posición actual
                if (nuevaLetra >= 'a' && nuevaLetra <= 'h' && nuevoNumero >= 1 && nuevoNumero <= 8
                        && (i != 0 || j != 0)) {
                    listaDeMovimientos.add((char) nuevaLetra + " " + nuevoNumero);
                }
            }
        }

        System.out.println(listaDeMovimientos);
    }
}

