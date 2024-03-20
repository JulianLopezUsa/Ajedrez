package modelo.fichas;

import java.util.ArrayList;

import modelo.Tablero.Tablero;

public class Alfil extends Fichas {
    public ArrayList<String> listaDeMovimientos = new ArrayList<>();

    public Alfil(int posX, int posY, String color) {
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

        // Calcular posibles movimientos en diagonal
        for (int i = -1; i <= 1; i += 2) {
            for (int j = -1; j <= 1; j += 2) {
                int nuevaLetra = letraF + i;
                int nuevoNumero = numeroF + j;
                boolean puedeAvanzar = true; // Variable para verificar si puede avanzar en diagonal
                while (nuevaLetra >= 'a' && nuevaLetra <= 'h' && nuevoNumero >= 0 && nuevoNumero <= 7 && puedeAvanzar) {
                    // Verificar si hay una ficha en la casilla adyacente
                    Fichas ficha = tablero.verificaciones.hayFicha(nuevaLetra - 'a', nuevoNumero, tt, tablero); // verifica las del otro color
                    Fichas ficha2 = tablero.verificaciones.hayFicha2(nuevaLetra - 'a', nuevoNumero, tt,tablero); // verfica las del mismo color
                    if (ficha != null || ficha2 != null) {
                        puedeAvanzar = false;
                        if (ficha2 != null) {
                            listaDeMovimientos.add((char) nuevaLetra + " " + nuevoNumero);
                        }
                    } else {
                        // Si la casilla adyacente está vacía, puede moverse
                        listaDeMovimientos.add((char) nuevaLetra + " " + nuevoNumero);
                    }
                    // Avanzar en diagonal
                    nuevaLetra += i;
                    nuevoNumero += j;
                }
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
        ArrayList<String> movimientosSegurosAlfil = new ArrayList<>();
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
            boolean jaqueDespuesDeMovimiento = tablero2.verificaciones.estaEnJaque3((tablero2.getTurno() == 1) ? 0 : 1, tablero2);
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
                movimientosSegurosAlfil.add(movimiento);
            }
        }
        return movimientosSegurosAlfil;
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