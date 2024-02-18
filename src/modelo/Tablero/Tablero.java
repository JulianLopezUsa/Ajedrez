/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.Tablero;

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

    public Fichas hayFicha(int i, int j){
        for (Fichas ficha : jugador2.fichas) {
            if (ficha.getPosX() == j && ficha.getPosY() == i) {
                return ficha;
            }
        }
        return null;
    }

}
