package modelo.fichas;

import java.util.ArrayList;

import modelo.Tablero.Tablero;

/**
 *
 * @author Laura
 */
public class Alfil extends Fichas {
    public ArrayList<String> listaDeMovimientos = new ArrayList<>();

    public Alfil(int posX, int posY, String color) {
        super(posX, posY, color);
    }

    
    @Override
    public void movimientoFicha(String posicionActual, Tablero tablero) {
        listaDeMovimientos.clear();
        String[] pos = posicionActual.split(" ");

        char letraF = pos[0].charAt(0);
        int numeroF = Integer.parseInt(pos[1]);

        // Calcular posibles movimientos en diagonal
        for (int i = -1; i <= 1; i += 2) {
            for (int j = -1; j <= 1; j += 2) {
                int nuevaLetra = letraF + i;
                int nuevoNumero = numeroF + j;
                boolean puedeAvanzar = true; // Variable para verificar si puede avanzar en diagonal
                while (nuevaLetra >= 'a' && nuevaLetra <= 'h' && nuevoNumero >= 0 && nuevoNumero <= 7 && puedeAvanzar) {
                    // Verificar si hay una ficha en la casilla adyacente
                    Fichas ficha = tablero.hayFicha( nuevaLetra - 'a',nuevoNumero, tablero.getTurno()); //verifica las del otro color
                    Fichas ficha2 = tablero.hayFicha2( nuevaLetra - 'a',nuevoNumero, tablero.getTurno()); // verfica las del mismo color
                    if (ficha != null || ficha2 !=null) {
                        puedeAvanzar = false;
                        if(ficha2!=null){
                            listaDeMovimientos.add((char) nuevaLetra + " " + nuevoNumero);
                        }
                    } else  {
                        // Si la casilla adyacente está vacía, puede moverse
                        listaDeMovimientos.add((char) nuevaLetra + " " + nuevoNumero);
                    }
                    // Avanzar en diagonal
                    nuevaLetra += i;
                    nuevoNumero += j;
                }
            }
        }
        setLista(listaDeMovimientos);

    }


    @Override
    public ArrayList<String> getLista() {
        return super.getLista();
    }


    @Override
    public void setLista(ArrayList<String> listaDeMovimientos) {
        super.setLista(listaDeMovimientos);
    }
}