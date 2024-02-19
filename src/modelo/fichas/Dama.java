package modelo.fichas;

import java.util.ArrayList;

import modelo.Tablero.Tablero;

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
    public void movimientoFicha(String posicionActual, Tablero tablero) {
        listaDeMovimientos.clear();
        String[] pos = posicionActual.split(" ");

        char letraF = pos[0].charAt(0);
        int numeroF = Integer.parseInt(pos[1]);

        // Calcular movimientos hacia la derecha
        for (int i = letraF + 1; i <= 'h'; i++) {
            Fichas ficha = tablero.hayFicha(i - 'a',numeroF,tablero.getTurno());
            if (ficha != null && ficha.getColor().equals(this.getColor())) {
                break; // Si hay una ficha del mismo color, detener la exploración en esta dirección
            }
            listaDeMovimientos.add((char) i + " " + numeroF);
            if (ficha != null) {
                break; // Si hay una ficha de color diferente, detener la exploración en esta dirección después de agregar la posición
            }
        }

        // Calcular movimientos hacia la izquierda
        for (int i = letraF - 1; i >= 'a'; i--) {
            Fichas ficha = tablero.hayFicha( i - 'a',numeroF,tablero.getTurno());
            if (ficha != null && ficha.getColor().equals(this.getColor())) {
                break; // Si hay una ficha del mismo color, detener la exploración en esta dirección
            }
            listaDeMovimientos.add((char) i + " " + numeroF);
            if (ficha != null) {
                break; // Si hay una ficha de color diferente, detener la exploración en esta dirección después de agregar la posición
            }
        }

        // Calcular movimientos hacia arriba
        for (int i = numeroF + 1; i <= 7; i++) {
            Fichas ficha = tablero.hayFicha(letraF - 'a',i,tablero.getTurno());
            if (ficha != null && ficha.getColor().equals(this.getColor())) {
                break; // Si hay una ficha del mismo color, detener la exploración en esta dirección
            }
            listaDeMovimientos.add(letraF + " " + i);
            if (ficha != null) {
                break; // Si hay una ficha de color diferente, detener la exploración en esta dirección después de agregar la posición
            }
        }

        // Calcular movimientos hacia abajo
        for (int i = numeroF - 1; i >= 0; i--) {
            Fichas ficha = tablero.hayFicha( letraF - 'a',i,tablero.getTurno());
            if (ficha != null && ficha.getColor().equals(this.getColor())) {
                break; // Si hay una ficha del mismo color, detener la exploración en esta dirección
            }
            listaDeMovimientos.add(letraF + " " + i);
            if (ficha != null) {
                break; // Si hay una ficha de color diferente, detener la exploración en esta dirección después de agregar la posición
            }
        }

        // Calcular movimientos del alfil
        for (int i = -1; i <= 1; i += 2) {
            for (int j = -1; j <= 1; j += 2) {
                int nuevaLetra = letraF + i;
                int nuevoNumero = numeroF + j;
                boolean puedeAvanzar = true; // Variable para verificar si puede avanzar en diagonal
                while (nuevaLetra >= 'a' && nuevaLetra <= 'h' && nuevoNumero >= 0 && nuevoNumero <= 7 && puedeAvanzar) {
                    // Verificar si hay una ficha en la casilla adyacente
                    Fichas ficha = tablero.hayFicha(nuevaLetra - 'a',nuevoNumero,tablero.getTurno());
                    if (ficha != null && ficha.getColor().equals(this.getColor())) {
                        // Si la ficha en la casilla adyacente es del mismo color, no puede avanzar más en esta dirección
                        puedeAvanzar = false;
                    } else {
                        listaDeMovimientos.add((char) nuevaLetra + " " + nuevoNumero);
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
