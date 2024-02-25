
package modelo.Tablero;

import java.util.ArrayList;

import modelo.fichas.Alfil;
import modelo.fichas.Caballo;
import modelo.fichas.Dama;
import modelo.fichas.Fichas;
import modelo.fichas.Peon;
import modelo.fichas.Rey;
import modelo.fichas.Torre;

import modelo.jugadores.Jugadores;

/**
 *
 * @author Laura
 */
public class Tablero {

    public int turno;
    public Jugadores jugador1;
    public Jugadores jugador2;
    public ArrayList<Fichas> arregloFichasMovimiento;
    public Jugadores oponente;

    public Tablero(String nombreJugador1, String nombreJugador2) {
        this.jugador1 = new Jugadores(nombreJugador1);
        this.jugador2 = new Jugadores(nombreJugador2);
        inicializarFichasEquipo1();
        inicializarFichasEquipo2();
        turno = 0;
        arregloFichasMovimiento = new ArrayList<>();
    }

    public void inicializarFichasEquipo1() {
        for (int i = 0; i < 8; i++) {
            jugador1.fichas.add(new Peon(i, 1, "blanco"));
        }

        jugador1.fichas.add(new Torre(0, 0, "blanco"));
        jugador1.fichas.add(new Torre(7, 0, "blanco"));
        jugador1.fichas.add(new Caballo(1, 0, "blanco"));
        jugador1.fichas.add(new Caballo(6, 0, "blanco"));
        jugador1.fichas.add(new Alfil(2, 0, "blanco"));
        jugador1.fichas.add(new Alfil(5, 0, "blanco"));
        jugador1.fichas.add(new Dama(3, 0, "blanco"));
        jugador1.fichas.add(new Rey(4, 0, "blanco"));
    }

    public void inicializarFichasEquipo2() {
        for (int i = 0; i < 8; i++) {
            jugador2.fichas.add(new Peon(i, 6, "negro"));
        }
        jugador2.fichas.add(new Torre(0, 7, "negro"));
        jugador2.fichas.add(new Torre(7, 7, "negro"));
        jugador2.fichas.add(new Caballo(1, 7, "negro"));
        jugador2.fichas.add(new Caballo(6, 7, "negro"));
        jugador2.fichas.add(new Alfil(2, 7, "negro"));
        jugador2.fichas.add(new Alfil(5, 7, "negro"));
        jugador2.fichas.add(new Dama(3, 7, "negro"));
        jugador2.fichas.add(new Rey(4, 7, "negro"));
    }

    public Fichas hayFicha(int i, int j, int turno) {
        if (turno == 0) {
            for (Fichas ficha : jugador2.fichas) {
                if (ficha.getPosX() == j && ficha.getPosY() == i) {
                    return ficha;
                }
            }
        } else if (turno == 1) {
            for (Fichas ficha : jugador1.fichas) {
                if (ficha.getPosX() == j && ficha.getPosY() == i) {
                    return ficha;
                }
            }
        }
        return null;
    }

    public Fichas hayFicha2(int i, int j, int turno) {
        if (turno == 0) {

            for (Fichas ficha : jugador1.fichas) {
                if (ficha.getPosX() == j && ficha.getPosY() == i) {
                    return ficha;
                }
            }
        } else if (turno == 1) {
            for (Fichas ficha : jugador2.fichas) {
                if (ficha.getPosX() == j && ficha.getPosY() == i) {
                    return ficha;
                }
            }
        }
        return null;
    }

    public ArrayList<Fichas> moverFicha(Fichas fichaSeleccionada, int i, int j) {

        // Actualizar la posición de la ficha en el tablero
        // Si la ficha se mueve a una posición ocupada por una ficha del otro jugador,
        // eliminar esa ficha del otro jugador

        Fichas fichaEnNuevaPosicion = hayFicha2(i, j, turno);
        if (fichaEnNuevaPosicion != null) {
            if (turno == 0) {
                jugador1.fichas.remove(fichaEnNuevaPosicion);
            } else {
                jugador2.fichas.remove(fichaEnNuevaPosicion);
            }
        }

        fichaSeleccionada.setPosX(j);
        fichaSeleccionada.setPosY(i);

        // Eliminar la ficha de su posición anterior
        if (turno == 0) {
            jugador2.fichas.remove(fichaSeleccionada);
        } else {
            jugador1.fichas.remove(fichaSeleccionada);
        }

        // Agregar la ficha a su nueva posición
        if (turno == 0) {
            jugador2.fichas.add(fichaSeleccionada);
        } else {
            jugador1.fichas.add(fichaSeleccionada);
        }

        // Cambiar el turno
        fichaSeleccionada.setMovio(true);
        turno = (turno + 1) % 2;

        arregloFichasMovimiento.add(fichaEnNuevaPosicion);
        arregloFichasMovimiento.add(fichaSeleccionada);

        return arregloFichasMovimiento;
    }

    public void eliminarFicha(Fichas fichaSeleccionada) {
        // Eliminar la ficha de su posición anterior
        if (turno == 1) {
            jugador2.fichas.remove(fichaSeleccionada);
        } else {
            jugador1.fichas.remove(fichaSeleccionada);
        }
    }

    public void crearFichaNueva(String nn, int x, int y) {
        if (turno == 1) {
            if (nn.equals("torre")) {
                jugador2.fichas.add(new Torre(x, y, "negro"));
            } else if (nn.equals("dama")) {
                jugador2.fichas.add(new Dama(x, y, "negro"));
            } else if (nn.equals("alfil")) {
                jugador2.fichas.add(new Alfil(x, y, "negro"));
            } else if (nn.equals("caballo")) {
                jugador2.fichas.add(new Caballo(x, y, "negro"));
            }
        } else {
            if (nn.equals("torre")) {
                jugador1.fichas.add(new Torre(x, y, "blanco"));
            } else if (nn.equals("dama")) {
                jugador1.fichas.add(new Dama(x, y, "blanco"));
            } else if (nn.equals("alfil")) {
                jugador1.fichas.add(new Alfil(x, y, "blanco"));
            } else if (nn.equals("caballo")) {
                jugador1.fichas.add(new Caballo(x, y, "blanco"));
            }
        }
    }

    public int getTurno() {
        return turno;
    }

    public Jugadores getJugador1() {
        return jugador1;
    }

    public Jugadores getJugador2() {
        return jugador2;
    }

    public boolean estaEnJaque(int turno, Fichas ficha) {
        // Obtener la posición del rey del jugador en turno
        Fichas rey;
        if (turno == 0) {
            rey = obtenerRey(jugador2.getFichas());
        } else {
            rey = obtenerRey(jugador1.getFichas());
        }

        ArrayList<String> movimientos = ficha.listaDeMovimientos;
        for (String movimiento : movimientos) {
            String[] pos = movimiento.split(" ");
            int newX = pos[0].charAt(0) - 'a';
            int newY = Integer.parseInt(pos[1]);
            System.out.println("AM" + newX + " " + newY);
            if (newX == rey.getPosY() && newY == rey.getPosX()) {
                return true; // El rey está en jaque
            }
        }

        return false; // El rey no está en jaque
    }

    public Fichas obtenerRey(ArrayList<Fichas> fichas) {
        for (Fichas ficha : fichas) {
            if (ficha instanceof Rey) {
                System.out.println("REY" + ficha.getPosX() + " " + ficha.getPosY());
                return ficha; // Devolver la instancia del rey
            }
        }
        return null; // Si no se encuentra el rey
    }

}