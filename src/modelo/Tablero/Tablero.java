
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
import vista.VistaTablero;

/**
 *
 * @author Laura
 */
public class Tablero {

    public int turno;
    public Jugadores jugador1;
    public Jugadores jugador2;
    private VistaTablero vistaTablero;
    public ArrayList<Fichas> arregloFichasMovimiento;
    public Jugadores oponente;
    public boolean jaqueBlanco = false;
    public boolean jaqueNegro = false;


    public Tablero(String nombreJugador1, String nombreJugador2, VistaTablero vistaTablero) {
        this.vistaTablero = vistaTablero;
        this.jugador1 = new Jugadores(nombreJugador1);
        this.jugador2 = new Jugadores(nombreJugador2);
        inicializarFichasEquipo1();
        inicializarFichasEquipo2();
        turno = 0;
        arregloFichasMovimiento = new ArrayList<>();
    }

    public void inicializarFichasEquipo1() {
        for (int i = 0; i < 8; i++) {
            jugador1.fichas.add(new Peon(i, 1, "negro"));
        }

        jugador1.fichas.add(new Torre(0, 0, "negro"));
        jugador1.fichas.add(new Torre(7, 0, "negro"));
        jugador1.fichas.add(new Caballo(1, 0, "negro"));
        jugador1.fichas.add(new Caballo(6, 0, "negro"));
        jugador1.fichas.add(new Alfil(2, 0, "negro"));
        jugador1.fichas.add(new Alfil(5, 0, "negro"));
        jugador1.fichas.add(new Dama(3, 0, "negro"));
        jugador1.fichas.add(new Rey(4, 0, "negro"));
    }

    public void inicializarFichasEquipo2() {
        for (int i = 0; i < 8; i++) {
            jugador2.fichas.add(new Peon(i, 6, "blanco"));
        }
        jugador2.fichas.add(new Torre(0, 7, "blanco"));
        jugador2.fichas.add(new Torre(7, 7, "blanco"));
        jugador2.fichas.add(new Caballo(1, 7, "blanco"));
        jugador2.fichas.add(new Caballo(6, 7, "blanco"));
        jugador2.fichas.add(new Alfil(2, 7, "blanco"));
        jugador2.fichas.add(new Alfil(5, 7, "blanco"));
        jugador2.fichas.add(new Dama(3, 7, "blanco"));
        jugador2.fichas.add(new Rey(4, 7, "blanco"));
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

        // Cambiar el turno
        fichaSeleccionada.setMovio(true);
        
        if (fichaEnNuevaPosicion != null) {

            vistaTablero.imprimirJugada(fichaSeleccionada.getClass().getSimpleName()+" Ceno ", j, i);
        }else{
            vistaTablero.imprimirJugada(fichaSeleccionada.getClass().getSimpleName(), j, i);
        }

        arregloFichasMovimiento.add(fichaEnNuevaPosicion);
        arregloFichasMovimiento.add(fichaSeleccionada);


        return arregloFichasMovimiento;
    }
    public void eliminarFicha(Fichas fichaSeleccionada) {
        // Eliminar la ficha de su posición anterior
        if (turno == 1) {
            jugador1.fichas.remove(fichaSeleccionada);
        } else {
            jugador2.fichas.remove(fichaSeleccionada);
        }
    }
    public void crearFichaNueva(String nn, int x, int y) {
        if (turno == 0) {
            if (nn.equals("torre")) {
                jugador2.fichas.add(new Torre(x, y, "blanco"));
            } else if (nn.equals("dama")) {
                jugador2.fichas.add(new Dama(x, y, "blanco"));
            } else if (nn.equals("alfil")) {
                jugador2.fichas.add(new Alfil(x, y, "blanco"));
            } else if (nn.equals("caballo")) {
                jugador2.fichas.add(new Caballo(x, y, "blanco"));
            }
        } else {
            if (nn.equals("torre")) {
                jugador1.fichas.add(new Torre(x, y, "negro"));
            } else if (nn.equals("dama")) {
                jugador1.fichas.add(new Dama(x, y, "negro"));
            } else if (nn.equals("alfil")) {
                jugador1.fichas.add(new Alfil(x, y, "negro"));
            } else if (nn.equals("caballo")) {
                jugador1.fichas.add(new Caballo(x, y, "negro"));
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

    public boolean estaEnJaque(int turno) {
        // Obtener la posición del rey del oponente
        Fichas rey;
        if (turno == 1) {
            rey = obtenerRey(jugador2.getFichas());
        } else {
            rey = obtenerRey(jugador1.getFichas());
        }

        Jugadores oponente = (turno == 0) ? jugador2 : jugador1;
        for (Fichas ficha : oponente.fichas) {
            int i = ficha.getPosX();
            int j = ficha.getPosY();

            ficha.movimientoFicha((char) (j + 97) + " " + i, this, 3);

            ArrayList<String> movimientos = ficha.listaDeMovimientos;
            for (String movimiento : movimientos) {
                String[] pos = movimiento.split(" ");

                int newY = pos[0].charAt(0) - 'a';
                int newX = Integer.parseInt(pos[1]);

                if (newX == rey.getPosX() && newY == rey.getPosY()) {
                    System.out.println("FICHA QUE DICE QUE OCASIONA EL JAQUE:");
                    System.out.println(ficha.toString());
                    System.out.println(movimientos);
                    System.out.println();
                    if (turno == 1) {
                        jaqueNegro = true;
                    } else {
                        jaqueBlanco = true;
                    }
                    return true; // El rey está en jaque
                }
            }
        }

        jaqueBlanco = false;
        jaqueNegro = false;
        return false; // El rey no está en jaque
    }

    public Fichas SimulacionMoverFicha(Fichas fichaSeleccionada, Tablero tablero, int i, int j) {

        // SI ENCUENTRA UNA FICHA EN LA PSOCIÓN A LA QUE SE MUEVE LA BORRA NE LA COPIA
        // Fichas fichaEnNuevaPosicion = hayFicha2(i, j, 0);
        Fichas fichaEnNuevaPosicion = hayFicha(j, i, (tablero.getTurno() == 1) ? 0 : 1);

        if (fichaEnNuevaPosicion != null) {
            if (((tablero.getTurno() == 1) ? 0 : 1) == 1) {
                jugador1.fichas.remove(fichaEnNuevaPosicion);
            } else {
                jugador2.fichas.remove(fichaEnNuevaPosicion);
            }
        }

        // SE ALTERAN LOS VALORES DE LA COPIA DE LA FICHA POR LOS NUEVOS
        fichaSeleccionada.setPosX(i);
        fichaSeleccionada.setPosY(j);

        return fichaEnNuevaPosicion;
    }

    public Fichas SimulacionRetrocesoFicha(Fichas fichaEliminada, Fichas fichaSeleccionada, Tablero tablero, int i,
            int j) {

        // VER BIEN EL TRUNO DE ESTA
        if (fichaEliminada != null) {
            if (((tablero.getTurno() == 1) ? 0 : 1) == 1) {
                jugador1.fichas.add(fichaEliminada);
            } else {
                jugador2.fichas.add(fichaEliminada);
            }
        }

        fichaSeleccionada.setPosX(i);
        fichaSeleccionada.setPosY(j);

        return fichaSeleccionada;
    }

    public boolean estaEnJaque2(int turno) {
        // Obtener la posición del rey del oponente
        Fichas rey;
        if (turno == 1) {
            rey = obtenerRey(jugador2.getFichas());
        } else {
            rey = obtenerRey(jugador1.getFichas());
        }

        Jugadores oponente = (turno == 0) ? jugador2 : jugador1;

        for (Fichas ficha : oponente.fichas) {
            
            int i = ficha.getPosX();
            int j = ficha.getPosY();
            // ficha.movimientoFicha((char) (j + 97) + " " + i, this, 0);
            ficha.movimientoFicha((char) (j + 97) + " " + i, this, turno);

            ArrayList<String> movimientos = ficha.getListaDeMovimientos();
            
            for (String movimiento : movimientos) {
                String[] pos = movimiento.split(" ");

                int newY = pos[0].charAt(0) - 'a';
                int newX = Integer.parseInt(pos[1]);

                if (newY == rey.getPosY() && newX == rey.getPosX()) {
                    if (turno == 1) {
                        jaqueNegro = true;
                    } else {
                        jaqueBlanco = true;
                    }
                    return true; // El rey está en jaque
                }
            }
        }

        jaqueBlanco = false;
        jaqueNegro = false;
        return false; // El rey no está en jaque
    }

    public Fichas obtenerRey(ArrayList<Fichas> fichas) {
        for (Fichas ficha : fichas) {
            if (ficha instanceof Rey) {
                return ficha; // Devolver la instancia del rey
            }
        }
        return null; // Si no se encuentra el rey
    }

}