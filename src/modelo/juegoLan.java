package modelo;
import java.util.ArrayList;

import modelo.Tablero.VerificacionesLan;
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
public class juegoLan {

    public Verificaciones verificaciones;
    public int turno;
    public Jugadores jugador1;
    public Jugadores jugador2;

    public ArrayList<String> historialPartida = new ArrayList<>();

    public ArrayList<Fichas> historialFichas = new ArrayList<>();
    public ArrayList<Boolean> historialJugadas = new ArrayList<>();
    public ArrayList<Fichas> arregloFichasMovimiento;

    public ArrayList<String> fichasValidasSalvarJaque = new ArrayList<>();
    public ArrayList<String> fichasValidasJaqueMate = new ArrayList<>();


    public Jugadores oponente;
    public boolean jaqueBlanco = false;
    public boolean jaqueNegro = false;


    public juegoLan(String nombreJugador1, String nombreJugador2) {
        this.jugador1 = new Jugadores(nombreJugador1);
        this.jugador2 = new Jugadores(nombreJugador2);
        verificaciones = new Verificaciones();
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

    public ArrayList<Fichas> moverFicha(Fichas fichaSeleccionada, int i, int j) {

        // Actualizar la posición de la ficha en el tablero

        // Si la ficha se mueve a una posición ocupada por una ficha del otro jugador,
        // eliminar esa ficha del otro jugador

        Fichas fichaEnNuevaPosicion = VerificacionesLan.hayFicha2(i, j, turno, this);
        if (fichaEnNuevaPosicion != null) {
            if (turno == 0) {
                jugador1.fichas.remove(fichaEnNuevaPosicion);
            } else {
                jugador2.fichas.remove(fichaEnNuevaPosicion);
            }
        }

        fichaSeleccionada.setPosX(j);
        fichaSeleccionada.setPosY(i);

        historialFichas.add(fichaSeleccionada);
        historialJugadas.add(fichaSeleccionada.movio);
        // Cambiar el turno
        fichaSeleccionada.setMovio(true);
        
        if (fichaEnNuevaPosicion != null) {
            guardarJugada(fichaSeleccionada.getClass().getSimpleName()+"X", j, i);
        }else{
            guardarJugada(fichaSeleccionada.getClass().getSimpleName(), j, i);
        }

        

        arregloFichasMovimiento.add(fichaEnNuevaPosicion);
        arregloFichasMovimiento.add(fichaSeleccionada);

        return arregloFichasMovimiento;
    }

    public void guardarJugada(String nombreFicha, int posX, int posY) {
        // Verifica si nombreFicha contiene la letra "X"
        if (nombreFicha.contains("X")) {
            // Elimina todos los espacios de la cadena
            nombreFicha = nombreFicha.replaceAll("\\s", "");
            historialPartida.add(nombreFicha + "(" + ((char) (posY + 'a')) + ", " + posX + ")\n");
        }else{
            // Agrega la información de la jugada al área de texto
            historialPartida.add(nombreFicha + " " + " (" + ((char) (posY + 'a')) + ", " + posX + ")\n");
        }
        

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

    public Fichas obtenerRey(ArrayList<Fichas> fichas) {
        for (Fichas ficha : fichas) {
            if (ficha instanceof Rey) {
                return ficha; // Devolver la instancia del rey
            }
        }
        return null; // Si no se encuentra el rey
    }
}