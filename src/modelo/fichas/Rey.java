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
    public boolean jaque = false, enroque = false;
    ArrayList<String> listaMovimientosEquipoContrario = new ArrayList<>();
    ArrayList<String> movimientosSegurosRey = new ArrayList<>();

    public Rey(int posX, int posY, String color) {
        super(posX, posY, color);
    }

    @Override
    public void movimientoFicha(String posicionActual, Tablero tablero, int turno, boolean banderaJaque, int primeraVez) {
        movimientosSegurosRey.clear();
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
                    Fichas ficha = tablero.verificaciones.hayFicha(nuevaLetra - 'a', nuevoNumero, tt,tablero);

                    if (ficha == null || !ficha.getColor().equals(this.getColor())) {
                        listaDeMovimientos.add((char) nuevaLetra + " " + nuevoNumero);
                    }
                }
            }
        }

        movimeintoFichas(tt, tablero);

        // --------------------DEFINIR LO MOVIMIENTOS SEGUROS DEL REY

        // Iterar sobre los movimientos del rey
        for (String movimiento : listaDeMovimientos) {
            // Verificar si el movimiento del rey está seguro
            if (!listaMovimientosEquipoContrario.contains(movimiento)) {
                movimientosSegurosRey.add(movimiento); // Agregar el movimiento seguro a la lista
            }
        }
        setLista(movimientosSegurosRey);

        verificarEnroque(this, tablero, banderaJaque);

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

    public void verificarEnroque(Fichas f, Tablero tablero, boolean banderaJaque) {
        enroque = false;
        // VERIFICACIÓN ENROQUE CORTO
        Jugadores equipo = (tablero.getTurno() == 1) ? tablero.jugador1 : tablero.jugador2;
        for (Fichas fichas : equipo.getFichas()) {
            // VERIFICAR QUE NO ESTE EN JAQUE
            if (!banderaJaque) {
                if (fichas instanceof Torre) {
                    Torre tor = ((Torre) fichas);
                    // VERIFICAR QUE SEA SU PRIMER MOVIMIENTO DE LA TOORRE Y DEL REY Y QUE SEA LA DE
                    // LA DERECHA
                    if (!tor.movio && (tor.getPosX() == 7) && !f.movio) {
                        // 1. QUE NO HAYAN FICHAS INTERPONIENDOSE ENTRE EL REY Y LA TORRE
                        if ((tablero.verificaciones.hayFicha(f.getPosY(), (f.getPosX() + 1), tablero.getTurno(),tablero) == null)
                                && (tablero.verificaciones.hayFicha(f.getPosY(), (f.getPosX() + 2), tablero.getTurno(),tablero) == null)) {
                            if ((tablero.verificaciones.hayFicha2(f.getPosY(), (f.getPosX() + 1), tablero.getTurno(),tablero) == null)
                                    && (tablero.verificaciones.hayFicha2(f.getPosY(), (f.getPosX() + 2),
                                            tablero.getTurno(),tablero) == null)) {
                                // 2. VERIFICAR QUE NO ESTE CONTROLADA POR NINGUNA FICHA DEL OTRO EQUIPO Y QUE
                                // NO ME VA A OCASIONAR JAQUE
                                if (!listaMovimientosEquipoContrario
                                        .contains((char) ((f.getPosY() + 'a')) + " " + (f.getPosX() + 1))
                                        && !listaMovimientosEquipoContrario
                                                .contains((char) ((f.getPosY() + 'a')) + " " + (f.getPosX() + 2))) {
                                    movimientosSegurosRey.add(((char) ((f.getPosY() + 'a')) + " " + (f.getPosX() + 2)));
                                    enroque = true;
                                }
                            }
                        }
                    // VERIFICACIÓN ENROQUE LARGO
                    // VERIFICAR QUE SEA SU PRIMER MOVIMIENTO DE LA TORRE Y DEL REY Y QUE SEA LA DE
                    // LA IZQUIERDA
                    }else if (!tor.movio && (tor.getPosX() == 0) && !f.movio) {
                        // 1. QUE NO HAYAN FICHAS INTERPONIENDOSE ENTRE EL REY Y LA TORRE
                        if ((tablero.verificaciones.hayFicha(f.getPosY(), (f.getPosX() - 1), tablero.getTurno(),tablero) == null)
                                && (tablero.verificaciones.hayFicha(f.getPosY(), (f.getPosX() - 2), tablero.getTurno(),tablero) == null)
                                && (tablero.verificaciones.hayFicha(f.getPosY(), (f.getPosX() - 3), tablero.getTurno(),tablero) == null)) {
                            if ((tablero.verificaciones.hayFicha2(f.getPosY(), (f.getPosX() - 1), tablero.getTurno(),tablero) == null)
                                    && (tablero.verificaciones.hayFicha2(f.getPosY(), (f.getPosX() - 2),tablero.getTurno(),tablero) == null)
                                    && (tablero.verificaciones.hayFicha2(f.getPosY(), (f.getPosX() - 3),tablero.getTurno(),tablero) == null)) {
                                // 2. VERIFICAR QUE NO ESTE CONTROLADA POR NINGUNA FICHA DEL OTRO EQUIPO Y QUE
                                // NO ME VA A OCASIONAR JAQUE
                                if (!listaMovimientosEquipoContrario
                                        .contains((char) ((f.getPosY() + 'a')) + " " + (f.getPosX() - 1))
                                        && !listaMovimientosEquipoContrario
                                                .contains((char) ((f.getPosY() + 'a')) + " " + (f.getPosX() - 2))) {
                                    movimientosSegurosRey.add(((char) ((f.getPosY() + 'a')) + " " + (f.getPosX() - 2)));
                                    enroque = true;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void movimeintoFichas(int tt, Tablero tablero) {
        Jugadores oponente = (tt == 0) ? tablero.jugador1 : tablero.jugador2;
        for (Fichas ficha : oponente.fichas) {
            if (!(ficha instanceof Peon)) {
                ArrayList<String> movimientos = ficha.listaDeMovimientos;
                listaMovimientosEquipoContrario.addAll(movimientos); // Agregar los movimientos de la ficha a
                                                                     // listaMovimientos
            } else {
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
            }

            if (ficha instanceof Rey) {
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        listaMovimientosEquipoContrario
                                .add((char) ((ficha.getPosY()) + 'a' + i) + " " + (ficha.getPosX() + j));

                    }
                }
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
    }
}
