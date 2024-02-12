/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.Tablero;

import modelo.juego.Game;
import modelo.jugadores.Jugadores;

/**
 *
 * @author Laura
 */
public class Tablero {
    //Es la ventana que contiene el tablero.
    private Game juego;
    //Son los jugadores que juegan en el tablero.
    private Jugadores[] jugadores = null;
    //Son los cuadros del tablero.
    private final Cuadros[][] cuadros;
    //Es el turno de los jugadores.
    private int turno;
    
    public Tablero(Jugadores[] jugadores, Game juego) {
        this.jugadores = jugadores;
        this.juego = juego;
        //Se indica que son 64 cuadros en todo el tablero.
        cuadros = new Cuadros[8][8];
        turno = 0;
    }
    
}
