package modelo.fichas;

import java.util.ArrayList;

import modelo.Tablero.Tablero;

public class Caballo extends Fichas {

    public ArrayList<String> listaDeMovimientos = new ArrayList<>();

    public Caballo(int posX, int posY, String color) {
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

        // Array con los posibles desplazamientos en forma de "L"
        int[][] desplazamientos = {
                { letraff - 2, numeroF - 1 }, { letraff - 2, numeroF + 1 },
                { letraff - 1, numeroF - 2 }, { letraff - 1, numeroF + 2 },
                { letraff + 1, numeroF - 2 }, { letraff + 1, numeroF + 2 },
                { letraff + 2, numeroF - 1 }, { letraff + 2, numeroF + 1 }
        };

        // Validar cada posible movimiento
        for (int[] movimiento : desplazamientos) {

            int nuevaLetra = movimiento[0];
            int nuevoNumero = movimiento[1];

            // Verificar si el movimiento está dentro del tablero
            if (nuevaLetra >= 97 && nuevaLetra <= 104 && nuevoNumero >= 0 && nuevoNumero <= 7) {
                // Verificar si hay una ficha del mismo equipo en la casilla final del
                // movimiento
                Fichas ficha = tablero.hayFicha(nuevaLetra - 'a', nuevoNumero, tt);

                if (ficha == null || !ficha.getColor().equals(this.getColor())) {
                    // Si no hay una ficha del mismo equipo, agregar el movimiento a la lista de
                    // movimientos válidos
                    listaDeMovimientos.add((char) nuevaLetra + " " + nuevoNumero);
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
        ArrayList<String> movimientosSegurosCaballo = new ArrayList<>();
        for (String movimiento : listaDeMovimientos) {
            String[] pos = movimiento.split(" ");
            int moveX = pos[0].charAt(0) - 'a';
            int moveY = Integer.parseInt(pos[1]);

            // Mover la ficha temporalmente
            int originalX = this.getPosX();
            int originalY = this.getPosY();

            Fichas fichaEliminada = tablero2.SimulacionMoverFicha(
                    this,
                    tablero2,
                    moveY,
                    moveX);

            // Verificar si el rey está en jaque después del movimiento
            boolean jaqueDespuesDeMovimiento = tablero2.estaEnJaque3((tablero2.getTurno() == 1) ? 0 : 1);
            // Deshacer el movimiento temporal
            tablero2.SimulacionRetrocesoFicha(
                    fichaEliminada,
                    this,
                    tablero2,
                    originalX,
                    originalY);

            // Si el movimiento no pone al rey en jaque, es un movimiento válido para salir
            // del jaque
            if (jaqueDespuesDeMovimiento) {
                // aqui se agrega movimiento que quita jaque
                movimientosSegurosCaballo.add(movimiento);
            }
        }
        return movimientosSegurosCaballo;
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