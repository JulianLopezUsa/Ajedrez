
package modelo.Tablero;

import modelo.fichas.Alfil;
import modelo.fichas.Caballo;
import modelo.fichas.Dama;
import modelo.fichas.Fichas;
import modelo.fichas.Peon;
import modelo.fichas.Rey;
import modelo.fichas.Torre;

import modelo.jugadores.Jugadores;

public class Tablero {

    public int turno;
    public Jugadores jugador1;
    public Jugadores jugador2;
   

    public Tablero(String nombreJugador1, String nombreJugador2) {
        this.jugador1 = new Jugadores(nombreJugador1);
        this.jugador2 = new Jugadores(nombreJugador2);
        inicializarFichasEquipo1();
        inicializarFichasEquipo2();
        turno = 0;
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
        }else if(turno==1){
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
        }else if(turno==1){
            for (Fichas ficha : jugador2.fichas) {
                if (ficha.getPosX() == j && ficha.getPosY() == i) {
                    return ficha;
                }
            }
        }
        return null;
    }

    public Fichas moverFicha(Fichas fichaSeleccionada, int i, int j) {
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
    
        return fichaEnNuevaPosicion;
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

}