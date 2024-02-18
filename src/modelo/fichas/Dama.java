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
public class Dama extends Fichas {
    public ArrayList<String> listaDeMovimientos = new ArrayList<>();

    public Dama(int posX, int posY, String color) {
        super(posX, posY, color);
    }

    @Override
    public void movimientoFicha(String posicionActual, int turno) {
        String[] pos = posicionActual.split(" ");

        char letraF = pos[0].charAt(0);
        int numeroF = Integer.parseInt(pos[1]);

        for (int i = numeroF + 1; i <= 7; i++) {
            listaDeMovimientos.add(letraF + " " + i); // Movimientos hacia derecha
        }
        for (int i = numeroF - 1; i >= 0; i--) {
            listaDeMovimientos.add(letraF + " " + i); // Movimientos hacia izquierda
        }
        for (char letra = (char) (letraF + 1); letra <= 'h'; letra++) {
            listaDeMovimientos.add(letra + " " + numeroF); // Movimientos hacia la arriba
        }
        for (char letra = (char) (letraF - 1); letra >= 'a'; letra--) {
            listaDeMovimientos.add(letra + " " + numeroF); // Movimientos hacia la abajo
        }

        // Calcular movimientos del alfil
        for (int i = -1; i <= 1; i += 2) {
            for (int j = -1; j <= 1; j += 2) {
                int nuevaLetra = letraF + i;
                int nuevoNumero = numeroF + j;
                while (nuevaLetra >= 'a' && nuevaLetra <= 'h' && nuevoNumero >= 0 && nuevoNumero <= 7) {
                    listaDeMovimientos.add((char) nuevaLetra + " " + nuevoNumero);
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

