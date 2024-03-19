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
    public void movimientoFicha(String posicionActual, Tablero tablero, int turno, boolean banderaJaque,
            int primeraVez) {
        listaDeMovimientos.clear();
        int tt;
        if (turno != 3) {
            tt = turno;
            // letraff=letraff-1;
        } else {
            tt = tablero.getTurno();
        }
        String[] pos = posicionActual.split(" ");

        char letraF = pos[0].charAt(0);
        int numeroF = Integer.parseInt(pos[1]);

        // Se castea a número la letra para trabajar con ASCII
        int letraff = (int) letraF;

        // Moverse hacia la derecha
        for (int i = numeroF + 1; i <= 7; i++) {
            Fichas ficha = tablero.verificaciones.hayFicha(letraF - 'a', i, tt,tablero);
            Fichas ficha2 = tablero.verificaciones.hayFicha2(letraF - 'a', i, tt,tablero);

            if (ficha != null && ficha.getColor().equals(this.getColor())) {
                break; // Si hay una ficha del mismo color, detener la exploración en esta dirección
            }
            listaDeMovimientos.add(letraF + " " + i);
            if (ficha2 != null) {
                break; // Si hay una ficha de color diferente, detener la exploración en esta dirección
                       // después de agregar la posición
            }
        }

        // Moverse hacia la izquierda
        for (int i = numeroF - 1; i >= 0; i--) {
            Fichas ficha = tablero.verificaciones.hayFicha(letraF - 'a', i, tt,tablero);
            Fichas ficha2 = tablero.verificaciones.hayFicha2(letraF - 'a', i, tt,tablero);

            if (ficha != null && ficha.getColor().equals(this.getColor())) {
                break; // Si hay una ficha del mismo color, detener la exploración en esta dirección
            }
            listaDeMovimientos.add(letraF + " " + i);
            if (ficha2 != null) {
                break; // Si hay una ficha de color diferente, detener la exploración en esta dirección
                       // después de agregar la posición
            }
        }

        // Moverse hacia arriba
        for (int i = letraff + 1; i <= 'h'; i++) {
            Fichas ficha = tablero.verificaciones.hayFicha(i - 'a', numeroF, tt,tablero);
            Fichas ficha2 = tablero.verificaciones.hayFicha2(i - 'a', numeroF, tt, tablero);

            if (ficha != null && ficha.getColor().equals(this.getColor())) {
                break; // Si hay una ficha del mismo color, detener la exploración en esta dirección
            }
            listaDeMovimientos.add((char) i + " " + numeroF);
            if (ficha2 != null) {
                break; // Si hay una ficha de color diferente, detener la exploración en esta dirección
                       // después de agregar la posición
            }
        }

        // Moverse hacia abajo
        for (int i = letraff - 1; i >= 'a'; i--) {
            Fichas ficha = tablero.verificaciones.hayFicha(i - 'a', numeroF, tt,tablero);
            Fichas ficha2 = tablero.verificaciones.hayFicha2(i - 'a', numeroF, tt,tablero);

            if (ficha != null && ficha.getColor().equals(this.getColor())) {
                break; // Si hay una ficha del mismo color, detener la exploración en esta dirección
            }
            listaDeMovimientos.add((char) i + " " + numeroF);
            if (ficha2 != null) {
                break; // Si hay una ficha de color diferente, detener la exploración en esta dirección
                       // después de agregar la posición
            }
        }
        ArrayList<String> Arr = new ArrayList<>();
        if (primeraVez == 0) {
            Arr = movimeintosNoProducenJaque(tablero);
        }
        for (String mov : Arr) {
            listaDeMovimientos.remove(mov);
        }
        setLista(listaDeMovimientos);
    }

    public ArrayList<String> movimeintosNoProducenJaque(Tablero tablero2) {
        ArrayList<String> movimientosSegurosTorre = new ArrayList<>();
        for (String movimiento : listaDeMovimientos) {
            String[] pos = movimiento.split(" ");
            int moveX = pos[0].charAt(0) - 'a';
            int moveY = Integer.parseInt(pos[1]);

            // Mover la ficha temporalmente
            int originalX = this.getPosX();
            int originalY = this.getPosY();

            Fichas fichaEliminada = tablero2.verificaciones.SimulacionMoverFicha(
                    this,
                    tablero2,
                    moveY,
                    moveX);

            // Verificar si el rey está en jaque después del movimiento
            boolean jaqueDespuesDeMovimiento = tablero2.verificaciones.estaEnJaque3((tablero2.getTurno() == 1) ? 0 : 1,tablero2);
            // Deshacer el movimiento temporal
            tablero2.verificaciones.SimulacionRetrocesoFicha(
                    fichaEliminada,
                    this,
                    tablero2,
                    originalX,
                    originalY);

            // Si el movimiento no pone al rey en jaque, es un movimiento válido para salir
            // del jaque
            if (jaqueDespuesDeMovimiento) {
                // aqui se agrega movimiento que quita jaque
                movimientosSegurosTorre.add(movimiento);
            }
        }
        return movimientosSegurosTorre;
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