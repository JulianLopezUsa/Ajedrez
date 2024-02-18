/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.fichas;

import java.util.ArrayList;

/**
 *
 * @author Laura
 */
public class Alfil extends Fichas {
    public ArrayList<String> listaDeMovimientos = new ArrayList<>();

    public Alfil(int posX, int posY, String color) {
        super(posX, posY, color);
    }

    
    @Override
    public void movimientoFicha(String posicionActual, int turno) {
        String[] pos = posicionActual.split(" ");

        char letraF = pos[0].charAt(0);
        int numeroF = Integer.parseInt(pos[1]);

        // Calcular posibles movimientos en diagonal
        for (int i = -1; i <= 1; i += 2) {
            for (int j = -1; j <= 1; j += 2) {
                int nuevaLetra = letraF + i;
                int nuevoNumero = numeroF + j;
                while (nuevaLetra >= 'a' && nuevaLetra <= 'h' && nuevoNumero >= 1 && nuevoNumero <= 8) {
                    listaDeMovimientos.add((char) nuevaLetra + " " + nuevoNumero);
                    // Avanzar en diagonal
                    nuevaLetra += i;
                    nuevoNumero += j;
                }
            }
        }
        setLista(listaDeMovimientos);
        System.out.println(listaDeMovimientos);
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

