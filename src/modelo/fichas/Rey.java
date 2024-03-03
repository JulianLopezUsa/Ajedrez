package modelo.fichas;

import java.util.ArrayList;

import modelo.Tablero.Tablero;
import modelo.jugadores.Jugadores;

/**
 *
 * @author Laura
 */
public class Rey extends Fichas {
    public ArrayList<String> listaDeMovimientos = new ArrayList<>();
    public boolean jaque = false;

    public Rey(int posX, int posY, String color) {
        super(posX, posY, color);
    }

    @Override
    public void movimientoFicha(String posicionActual, Tablero tablero, int turno) {
        listaDeMovimientos.clear();
        int tt;
        if (turno != 3) {
            tt = turno;
            // letraff=letraff-1;
        } else {
            tt = tablero.getTurno();
        }

        String[] pos = posicionActual.split(" ");

        char letraF = pos[0].charAt(0);
        int numeroF = Integer.parseInt(pos[1]);

        // Calcular movimientos del rey
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nuevaLetra = letraF + i;
                int nuevoNumero = numeroF + j;
                // Verificar si el movimiento está dentro del tablero y no es la misma posición
                // actual
                if (nuevaLetra >= 'a' && nuevaLetra <= 'h' && nuevoNumero >= 0 && nuevoNumero <= 7
                        && (i != 0 || j != 0)) {
                    // Verificar si hay una ficha en la casilla adyacente
                    Fichas ficha = tablero.hayFicha(nuevaLetra - 'a', nuevoNumero, tt);

                    if (ficha == null || !ficha.getColor().equals(this.getColor())) {
                        listaDeMovimientos.add((char) nuevaLetra + " " + nuevoNumero);
                    }
                }
            }
        }

        Jugadores oponente = (tt == 0) ? tablero.jugador1 : tablero.jugador2;
        ArrayList<String> listaMovimientosEquipoContrario = new ArrayList<>();
        for (Fichas ficha : oponente.fichas) {
            if (!(ficha instanceof Peon)) {
                ArrayList<String> movimientos = ficha.listaDeMovimientos;
                listaMovimientosEquipoContrario.addAll(movimientos); // Agregar los movimientos de la ficha a
                                                                    // listaMovimientos
            }
        }

        // -------------------------- CASO ESPECIFICO DEL PEON----------------------
        for (Fichas ficha : oponente.fichas) {
            if (ficha instanceof Peon) {
                if (ficha.getColor().contains("negro")) {

                    listaMovimientosEquipoContrario
                            .add((char) ((ficha.getPosY()) + 'a' + 1) + " " + (ficha.getPosX() + 1));
                    listaMovimientosEquipoContrario
                            .add((char) ((ficha.getPosY()) + 'a' + 1) + " " + (ficha.getPosX() - 1));
                } else {
                    listaMovimientosEquipoContrario
                            .add((char) ((ficha.getPosY()) + 'a' - 1) + " " + (ficha.getPosX() + 1));
                    listaMovimientosEquipoContrario
                            .add((char) ((ficha.getPosY()) + 'a' - 1) + " " + (ficha.getPosX() - 1));

                }
                // .add ( (char) letraff(x) +" "+ numero)
            }

            if (ficha instanceof Rey) {
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        listaMovimientosEquipoContrario
                                .add((char) ((ficha.getPosY()) + 'a' + i) + " " + (ficha.getPosX() + j));

                    }
                }
            }

        }

        // --------------------DEFINIR LO MOVIMIENTOS SEGUROS DEL REY

        ArrayList<String> movimientosSegurosRey = new ArrayList<>();
        // Iterar sobre los movimientos del rey
        for (String movimiento : listaDeMovimientos) {
            // Verificar si el movimiento del rey está seguro
            if (!listaMovimientosEquipoContrario.contains(movimiento)) {
                movimientosSegurosRey.add(movimiento); // Agregar el movimiento seguro a la lista
            }
        }
        setLista(movimientosSegurosRey);
    }

    @Override
    public ArrayList<String> getLista() {
        return super.getLista();
    }

    @Override
    public void setLista(ArrayList<String> listaDeMovimientos) {
        super.setLista(listaDeMovimientos);
    }

    public void estaJaque() {
        this.jaque = true;
    }
}
