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
    public void movimientoFicha(String posicionActual,  Tablero tablero) {
        listaDeMovimientos.clear();
        String[] pos = posicionActual.split(" ");

        char letraF = pos[0].charAt(0);
        int numeroF = Integer.parseInt(pos[1]);

        // Calcular movimientos del rey
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nuevaLetra = letraF + i;
                int nuevoNumero = numeroF + j;
                // Verificar si el movimiento está dentro del tablero y no es la misma posición actual
                if (nuevaLetra >= 'a' && nuevaLetra <= 'h' && nuevoNumero >= 0 && nuevoNumero <= 7
                        && (i != 0 || j != 0)) {
                    // Verificar si hay una ficha en la casilla adyacente
                    Fichas ficha = tablero.hayFicha(nuevaLetra - 'a',nuevoNumero,tablero.getTurno());
                    if (ficha == null || !ficha.getColor().equals(this.getColor())) {
                        listaDeMovimientos.add((char) nuevaLetra + " " + nuevoNumero);
                    }
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
