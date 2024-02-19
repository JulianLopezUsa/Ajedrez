package modelo.fichas;

import java.util.ArrayList;

import modelo.Tablero.Tablero;

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
    public void movimientoFicha(String posicionActual, Tablero tablero) {
        listaDeMovimientos.clear();
        String[] pos = posicionActual.split(" ");

        char letraF = pos[0].charAt(0);
        int numeroF = Integer.parseInt(pos[1]);

        // Calcular posibles movimientos en diagonal
        for (int i = -1; i <= 1; i += 2) {
            for (int j = -1; j <= 1; j += 2) {
                int nuevaLetra = letraF + i;
                int nuevoNumero = numeroF + j;
                boolean puedeAvanzar = true; // Variable para verificar si puede avanzar en diagonal
                while (nuevaLetra >= 'a' && nuevaLetra <= 'h' && nuevoNumero >= 0 && nuevoNumero <= 7 && puedeAvanzar) {
                    // Verificar si hay una ficha en la casilla adyacente
                    Fichas ficha = tablero.hayFicha( nuevaLetra - 'a',nuevoNumero, tablero.getTurno());
                    
                    if (ficha != null && ficha.getColor().equals(this.getColor())) {
                        // Si la ficha en la casilla adyacente es del mismo color, no puede avanzar más en esta dirección
                        puedeAvanzar = false;
                    } else {
                        listaDeMovimientos.add((char) nuevaLetra + " " + nuevoNumero);
                        puedeAvanzar = true;
                    }
                    // Avanzar en diagonal
                    nuevaLetra += i;
                    nuevoNumero += j;
                }
            }
        }
        setLista(listaDeMovimientos);

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
