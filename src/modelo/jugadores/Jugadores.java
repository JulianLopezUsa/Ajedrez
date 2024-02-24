
package modelo.jugadores;

import modelo.fichas.Fichas;


import java.util.ArrayList;

/**
 *
 * @author Laura
 */
public class Jugadores {

    public String nombre;
    public ArrayList<Fichas> fichas;
    public int numFichas;

    public Jugadores(String nombre) {
        this.nombre = nombre;
        numFichas = 16;
        fichas = new ArrayList<>(numFichas);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Fichas> getFichas() {
        return fichas;
    }

    public void setFichas(ArrayList<Fichas> fichas) {
        this.fichas = fichas;
    }

    public int getNumFichas() {
        return numFichas;
    }

    public void setNumFichas(int numFichas) {
        this.numFichas = numFichas;
    }

}

