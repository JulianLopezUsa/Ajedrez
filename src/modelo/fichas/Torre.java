package modelo.fichas;

import java.util.ArrayList;

import modelo.Tablero.Tablero;

/**
 *
 * @author Laura
 */
public class Torre extends Fichas {
    public ArrayList<String> listaDeMovimientos = new ArrayList<>();

    public Torre(int posX, int posY, String color) {
        super(posX, posY, color);
    }

    @Override
    public void movimientoFicha(String posicionActual, Tablero tablero) {
        listaDeMovimientos.clear();
        String[] pos = posicionActual.split(" ");

        char letraF = pos[0].charAt(0);
        int numeroF = Integer.parseInt(pos[1]);

        // Se castea a número la letra para trabajar con ASCII
        int letraff = (int) letraF;

        // Moverse hacia la derecha
        for (int i = numeroF + 1; i <= 7; i++) {
            Fichas ficha = tablero.hayFicha(letraF - 'a',i,tablero.getTurno());
            Fichas ficha2 = tablero.hayFicha2(letraF - 'a', i, tablero.getTurno());
        
            if (ficha != null && ficha.getColor().equals(this.getColor())) {
                break; // Si hay una ficha del mismo color, detener la exploración en esta dirección
            }
            listaDeMovimientos.add(letraF + " " + i);
            if (ficha2 != null) {
                break; // Si hay una ficha de color diferente, detener la exploración en esta dirección después de agregar la posición
            }
        }

        // Moverse hacia la izquierda
        for (int i = numeroF - 1; i >= 0; i--) {
            Fichas ficha = tablero.hayFicha(letraF - 'a',i,tablero.getTurno());
            Fichas ficha2 = tablero.hayFicha2(letraF - 'a', i, tablero.getTurno());
            
            if (ficha != null && ficha.getColor().equals(this.getColor())) {
                break; // Si hay una ficha del mismo color, detener la exploración en esta dirección
            }
            listaDeMovimientos.add(letraF + " " + i);
            if (ficha2 != null) {
                break; // Si hay una ficha de color diferente, detener la exploración en esta dirección después de agregar la posición
            }
        }

        // Moverse hacia arriba
        for (int i = letraff + 1; i <= 'h'; i++) {
            Fichas ficha = tablero.hayFicha( i - 'a',numeroF,tablero.getTurno());
            Fichas ficha2 = tablero.hayFicha2(i - 'a',numeroF, tablero.getTurno()); 
                    
            if (ficha != null && ficha.getColor().equals(this.getColor())) {
                break; // Si hay una ficha del mismo color, detener la exploración en esta dirección
            }
            listaDeMovimientos.add((char) i + " " + numeroF);
            if (ficha2 != null) {
                break; // Si hay una ficha de color diferente, detener la exploración en esta dirección después de agregar la posición
            }
        }

        // Moverse hacia abajo
        for (int i = letraff - 1; i >= 'a'; i--) {
            Fichas ficha = tablero.hayFicha( i - 'a',numeroF,tablero.getTurno());
            Fichas ficha2 = tablero.hayFicha2(i - 'a', numeroF, tablero.getTurno()); 
                    
            if (ficha != null && ficha.getColor().equals(this.getColor())) {
                break; // Si hay una ficha del mismo color, detener la exploración en esta dirección
            }
            listaDeMovimientos.add((char) i + " " + numeroF);
            if (ficha2 != null) {
                break; // Si hay una ficha de color diferente, detener la exploración en esta dirección después de agregar la posición
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
