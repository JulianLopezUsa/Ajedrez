
package modelo.Tablero;

import java.awt.Color;
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

    public Verificaciones verificaciones;
    public int turno;
    public Jugadores jugador1;
    public Jugadores jugador2;
    public Cuadro c;
    public int jaqueX_negras, jaqueY_negras;
    public int jaqueX_blancas, jaqueY_blancas;

    public ArrayList<String> historialPartida = new ArrayList<>();

    public ArrayList<Fichas> historialFichas = new ArrayList<>();
    public ArrayList<Boolean> historialJugadas = new ArrayList<>();
    public ArrayList<Fichas> arregloFichasMovimiento;
    public ArrayList<Cuadro> listaDeCuadros = new ArrayList<>();
    public ArrayList<String> fichasValidasSalvarJaque = new ArrayList<>();
    public ArrayList<String> fichasValidasJaqueMate = new ArrayList<>();


    public Jugadores oponente;
    public boolean jaqueBlanco = false;
    public boolean jaqueNegro = false;


    public Tablero(String nombreJugador1, String nombreJugador2) {
        this.jugador1 = new Jugadores(nombreJugador1);
        this.jugador2 = new Jugadores(nombreJugador2);
        verificaciones = new Verificaciones();
        inicializarFichasEquipo1();
        inicializarFichasEquipo2();
        turno = 0;
        arregloFichasMovimiento = new ArrayList<>();
    }

    public void inicializarCuadros(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //cuadro[i][j] = new JButton();
                if ((i + j) % 2 == 0) {
                    c = new Cuadro(new Color(222, 184, 135),i,j);
                    //cuadro[i][j].setBackground(new Color(222, 184, 135));
                } else {
                    c = new Cuadro(new Color(139, 69, 19),i,j);
                    //cuadro[i][j].setBackground(new Color(139, 69, 19));
                }
                listaDeCuadros.add(c);
            }
        }
    }

    public void resetearColores(){
        for(Cuadro cuadro : listaDeCuadros){
            if ((cuadro.getX() + cuadro.getY()) % 2 == 0) {
                cuadro.setColor(new Color(222, 184, 135));
            } else {
                cuadro.setColor(new Color(139, 69, 19));
            }
        }
        resaltarJaque();
    }

    public void resaltarJaque() {
        if(jaqueBlanco) {
            for(Cuadro cuadro : listaDeCuadros){
                if (cuadro.getX() == jaqueX_blancas && cuadro.getY() == jaqueY_blancas){
                    cuadro.setColor(Color.RED);
                }
            }
            //cuadro[jaqueX_blancas][jaqueY_blancas].setBackground(Color.RED);
        } else if (jaqueNegro) {
            for(Cuadro cuadro : listaDeCuadros){
                if (cuadro.getX() == jaqueX_negras && cuadro.getY() == jaqueY_negras){
                    cuadro.setColor(Color.RED);
                }
            }
            //cuadro[jaqueX_negras][jaqueY_negras].setBackground(Color.RED);
        }
    }

    public void ponerJaque(int x, int y) {
        if (jaqueNegro) {
            jaqueX_negras = y;
            jaqueY_negras = x;
            jaqueNegro = true;
        } else if (jaqueBlanco) {
            jaqueX_blancas = y;
            jaqueY_blancas = x;
            jaqueBlanco = true;
        }
        
        for(Cuadro cuadro : listaDeCuadros){
            if (cuadro.getX() == y && cuadro.getY() == x){
                cuadro.setColor(Color.RED);
            }
        }
        //cuadro[y][x].setBackground(Color.RED);
    }


    public void quitarJaque(){
        // Resetear el color de todos los botones del tablero
        for(Cuadro cuadro : listaDeCuadros){
            if ((cuadro.getX() + cuadro.getY()) % 2 == 0) {
                cuadro.setColor(new Color(222, 184, 135));
            } else {
                cuadro.setColor(new Color(139, 69, 19));
            }
        }
    }

    public void resaltarMovimeintosCuadros(ArrayList<String> arreglo){
        // Resalta los botones correspondientes
        resetearColores();
        for (String movimiento : arreglo) {
            String[] pos = movimiento.split(" ");
            int newX = pos[0].charAt(0) - 'a';
            int newY = Integer.parseInt(pos[1]);
            for(Cuadro cuadro : listaDeCuadros){
                if (cuadro.getX() == newX && cuadro.getY() == newY){
                    cuadro.setColor(Color.YELLOW);
                }
            }
        }
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

        Fichas fichaEnNuevaPosicion = verificaciones.hayFicha2(i, j, turno, this);
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