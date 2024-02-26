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
    public boolean jaque= false;

    public Rey(int posX, int posY, String color) {
        super(posX, posY, color);
    }

    @Override
    public void movimientoFicha(String posicionActual,  Tablero tablero) {
        listaDeMovimientos.clear();
        String[] pos = posicionActual.split(" ");

        char letraF = pos[0].charAt(0);
        int numeroF = Integer.parseInt(pos[1]);
        
        // Calcular movimientos del rey
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nuevaLetra = letraF + i;
                int nuevoNumero = numeroF + j;
                // Verificar si el movimiento está dentro del tablero y no es la misma posición actual
                if (nuevaLetra >= 'a' && nuevaLetra <= 'h' && nuevoNumero >= 0 && nuevoNumero <= 7
                        && (i != 0 || j != 0)) {
                    // Verificar si hay una ficha en la casilla adyacente
                    Fichas ficha = tablero.hayFicha(nuevaLetra - 'a',nuevoNumero,tablero.getTurno());
                    
                    if (ficha == null || !ficha.getColor().equals(this.getColor())) {
                        listaDeMovimientos.add((char) nuevaLetra + " " + nuevoNumero);
                    }
                }
            }
        }


        Jugadores oponente = (tablero.turno == 0) ? tablero.jugador1 : tablero.jugador2;
        ArrayList<String> listaMovimientosEquipoContrario = new ArrayList<>();

        for (Fichas ficha : oponente.fichas) {

            ArrayList<String> movimientos = ficha.listaDeMovimientos;

            listaMovimientosEquipoContrario.addAll(movimientos); // Agregar los movimientos de la ficha a listaMovimientos
           
        }
        
        ArrayList<String> movimientosSegurosRey = new ArrayList<>();
        // Iterar sobre los movimientos del rey
        for (String movimiento : listaDeMovimientos) {
            // Verificar si el movimiento del rey está seguro
            if (!listaMovimientosEquipoContrario.contains(movimiento)) {
                movimientosSegurosRey.add(movimiento); // Agregar el movimiento seguro a la lista
            }
        }


        System.out.println("MOVIMIENTOS DEL REY");
        System.out.println(listaDeMovimientos);
        System.out.println("Movimientos seguros del rey");
        System.out.println(movimientosSegurosRey);
        System.out.println("Moviemitnos equipo contrario");
        System.out.println(listaMovimientosEquipoContrario);
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

    public void estaJaque(){
        this.jaque=true;
    }
}
