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
public class Torre extends Fichas {
    public ArrayList<String> listaDeMovimientos = new ArrayList<>();

    public Torre(int posX, int posY, String color) {
        super(posX, posY, color);
    }

    @Override
    public void movimientoFicha(String posicionActual, int turno) {
        String[] pos = posicionActual.split(" ");

        char letraF = pos[0].charAt(0);
        int numeroF = Integer.parseInt(pos[1]);

        // Se castea a número la letra para trabajar con ASCII
        int letraff = (int) letraF;

        // Moverse hacia derecha
        for (int i = numeroF + 1; i <= 8; i++) {
            listaDeMovimientos.add(letraF + " " + i);
        }

        // Moverse hacia izquierda
        for (int i = numeroF - 1; i >= 1; i--) {
            listaDeMovimientos.add(letraF + " " + i);
        }

        // Moverse hacia la arriba
        for (int i = letraff + 1; i <= 'h'; i++) {
            listaDeMovimientos.add((char) i + " " + numeroF);
        }

        // Moverse hacia la abajo
        for (int i = letraff - 1; i >= 'a'; i--) {
            listaDeMovimientos.add((char) i + " " + numeroF);
        }

        System.out.println(listaDeMovimientos);
    }
}
