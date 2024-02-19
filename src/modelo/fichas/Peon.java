
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
            if (movio) {
                desplazamientos = new int[][] { { letraff - 1, numeroF } };
            } else {
                contadorMov++;
                desplazamientos = new int[][] { { letraff - 2, numeroF }, { letraff - 1, numeroF } };
            }
        } else if (tablero.getTurno() == 1) {
            if (movio) {
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
                    verificarOtrasFichas(tablero, nuevaLetra, nuevoNumero);
                    // listaDeMovimientos.add((char) nuevaLetra + " " + nuevoNumero);
                }
            } else if (letraff >= 98 && letraff <= 104 && tablero.getTurno() == 1) {
                // Verificar si el movimiento está dentro del tablero
                if (nuevaLetra >= 97 && nuevaLetra <= 104 && nuevoNumero >= 0 && nuevoNumero <= 7) {
                    verificarOtrasFichas(tablero, nuevaLetra, nuevoNumero);

                    // listaDeMovimientos.add((char) nuevaLetra + " " + nuevoNumero);
                }
            }
        }
        setLista(listaDeMovimientos);

    }

    public void verificarOtrasFichas(Tablero tablero, int nuevaLetra, int nuevoNumero) {
        // Verificar si hay una ficha en la casilla adyacente
        Fichas ficha = tablero.hayFicha(nuevaLetra - 'a', nuevoNumero, tablero.getTurno());
        if (ficha == null || !ficha.getColor().equals(this.getColor())) {
            listaDeMovimientos.add((char) nuevaLetra + " " + nuevoNumero);
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

    public void setDesplazamientos(int[][] desplazamientos) {
        this.desplazamientos = desplazamientos;
    }

    @Override
    public void setMovio(boolean movio) {
        this.movio = movio;
    }
}
