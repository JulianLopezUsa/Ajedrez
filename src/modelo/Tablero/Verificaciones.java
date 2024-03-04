package modelo.Tablero;

import java.util.ArrayList;

import modelo.fichas.Fichas;
import modelo.jugadores.Jugadores;

public class Verificaciones {

    public Fichas hayFicha(int i, int j, int turno, Tablero tablero) {
        if (turno == 0) {
            for (Fichas ficha : tablero.jugador2.fichas) {
                if (ficha.getPosX() == j && ficha.getPosY() == i) {
                    return ficha;
                }
            }
        } else if (turno == 1) {
            for (Fichas ficha : tablero.jugador1.fichas) {
                if (ficha.getPosX() == j && ficha.getPosY() == i) {
                    return ficha;
                }
            }
        }
        return null;
    }

    public Fichas hayFicha2(int i, int j, int turno, Tablero tablero) {
        if (turno == 0) {
            for (Fichas ficha : tablero.jugador1.fichas) {
                if (ficha.getPosX() == j && ficha.getPosY() == i) {
                    return ficha;
                }
            }
        } else if (turno == 1) {
            for (Fichas ficha : tablero.jugador2.fichas) {
                if (ficha.getPosX() == j && ficha.getPosY() == i) {
                    return ficha;
                }
            }
        }
        return null;
    }

    public boolean estaEnJaque(int turno, Tablero tablero) {
        // Obtener la posición del rey del oponente
        Fichas rey;
        if (turno == 1) {
            rey = tablero.obtenerRey(tablero.jugador2.getFichas());
        } else {
            rey = tablero.obtenerRey(tablero.jugador1.getFichas());
        }
        Jugadores oponente = (turno == 0) ? tablero.jugador2 : tablero.jugador1;
        for (Fichas ficha : oponente.fichas) {
            int i = ficha.getPosX();
            int j = ficha.getPosY();

            ficha.movimientoFicha((char) (j + 97) + " " + i, tablero, 3, true, 1);

            ArrayList<String> movimientos = ficha.listaDeMovimientos;
            for (String movimiento : movimientos) {
                String[] pos = movimiento.split(" ");

                int newY = pos[0].charAt(0) - 'a';
                int newX = Integer.parseInt(pos[1]);

                if (newX == rey.getPosX() && newY == rey.getPosY()) {
                    if (turno == 1) {
                        tablero.jaqueNegro = true;
                    } else {
                        tablero.jaqueBlanco = true;
                    }
                    return true; // El rey está en jaque
                }
            }
        }

        tablero.jaqueBlanco = false;
        tablero.jaqueNegro = false;
        return false; // El rey no está en jaque
    }

    public Fichas SimulacionMoverFicha(Fichas fichaSeleccionada, Tablero tablero, int i, int j) {

        // SI ENCUENTRA UNA FICHA EN LA PSOCIÓN A LA QUE SE MUEVE LA BORRA NE LA COPIA
        // Fichas fichaEnNuevaPosicion = hayFicha2(i, j, 0);
        Fichas fichaEnNuevaPosicion = hayFicha(j, i, (tablero.getTurno() == 1) ? 0 : 1, tablero);

        if (fichaEnNuevaPosicion != null) {
            if (((tablero.getTurno() == 1) ? 0 : 1) == 1) {
                tablero.jugador1.fichas.remove(fichaEnNuevaPosicion);
            } else {
                tablero.jugador2.fichas.remove(fichaEnNuevaPosicion);
            }
        }

        // SE ALTERAN LOS VALORES DE LA COPIA DE LA FICHA POR LOS NUEVOS
        fichaSeleccionada.setPosX(i);
        fichaSeleccionada.setPosY(j);

        return fichaEnNuevaPosicion;
    }

    public Fichas SimulacionRetrocesoFicha(Fichas fichaEliminada, Fichas fichaSeleccionada, Tablero tablero, int i,
            int j) {

        // VER BIEN EL TRUNO DE ESTA
        if (fichaEliminada != null) {
            if (((tablero.getTurno() == 1) ? 0 : 1) == 1) {
                tablero.jugador1.fichas.add(fichaEliminada);
            } else {
                tablero.jugador2.fichas.add(fichaEliminada);
            }
        }

        fichaSeleccionada.setPosX(i);
        fichaSeleccionada.setPosY(j);

        return fichaSeleccionada;
    }

    public boolean estaEnJaque2(int turno, Tablero tablero) {
        // Obtener la posición del rey del oponente
        Fichas rey;
        if (turno == 1) {
            rey = tablero.obtenerRey(tablero.jugador2.getFichas());
        } else {
            rey = tablero.obtenerRey(tablero.jugador1.getFichas());
        }
        Jugadores oponente = (turno == 0) ? tablero.jugador2 : tablero.jugador1;
        
        for (Fichas ficha : oponente.fichas) {
            
            int i = ficha.getPosX();
            int j = ficha.getPosY();
            // ficha.movimientoFicha((char) (j + 97) + " " + i, this, 0);
            ficha.movimientoFicha((char) (j + 97) + " " + i, tablero, turno, true,1);

            ArrayList<String> movimientos = ficha.getListaDeMovimientos();
            
            for (String movimiento : movimientos) {
                String[] pos = movimiento.split(" ");

                int newY = pos[0].charAt(0) - 'a';
                int newX = Integer.parseInt(pos[1]);

                if (newY == rey.getPosY() && newX == rey.getPosX()) {
                    if (turno == 1) {
                        tablero.jaqueNegro = true;
                    } else {
                        tablero.jaqueBlanco = true;
                    }
                    return true; // El rey está en jaque
                }
            }
        }

        tablero.jaqueBlanco = false;
        tablero.jaqueNegro = false;
        return false; // El rey no está en jaque
    }

    public boolean estaEnJaque3(int turno, Tablero tablero) {
        // Obtener la posición del rey del oponente
        Fichas rey;
        if (turno == 1) {
            rey = tablero.obtenerRey(tablero.jugador2.getFichas());
        } else {
            rey = tablero.obtenerRey(tablero.jugador1.getFichas());
        }
        Jugadores oponente = (turno == 0) ? tablero.jugador2 : tablero.jugador1;
        
        for (Fichas ficha : oponente.fichas) {
            
            int i = ficha.getPosX();
            int j = ficha.getPosY();
            // ficha.movimientoFicha((char) (j + 97) + " " + i, this, 0);
            ficha.movimientoFicha((char) (j + 97) + " " + i, tablero, turno, true,1);

            ArrayList<String> movimientos = ficha.getListaDeMovimientos();
            
            for (String movimiento : movimientos) {
                String[] pos = movimiento.split(" ");

                int newY = pos[0].charAt(0) - 'a';
                int newX = Integer.parseInt(pos[1]);

                if (newY == rey.getPosY() && newX == rey.getPosX()) {
                    return true; // El rey está en jaque
                }
            }
        }
        return false; // El rey no está en jaque
    }

    public boolean esMovimientoValidoParaSalirDelJaque(Fichas ficha, int i, int j, Tablero tablero) {
        tablero.fichasValidasSalvarJaque.clear();
        ficha.movimientoFicha((char) (i + 97) + " " + j, tablero, 3, true, 0);
        // Verificar si los movimientos de la ficha quitan el jaque o no

        ArrayList<String> movimientos = ficha.getLista();

        for (String movimiento : movimientos) {
            String[] pos = movimiento.split(" ");
            int moveX = pos[0].charAt(0) - 'a';
            int moveY = Integer.parseInt(pos[1]);

            // Mover la ficha temporalmente
            int originalX = i;
            int originalY = j;

            Fichas fichaEliminada = SimulacionMoverFicha(
                    ficha,
                    tablero,
                    moveY,
                    moveX);

            // Verificar si el rey está en jaque después del movimiento
            boolean jaqueDespuesDeMovimiento = estaEnJaque2((tablero.getTurno() == 1) ? 0 : 1, tablero);
            // Deshacer el movimiento temporal
            SimulacionRetrocesoFicha(
                    fichaEliminada,
                    ficha,
                    tablero,
                    originalY,
                    originalX);
            // Si el movimiento no pone al rey en jaque, es un movimiento válido para salir
            // del jaque
            if (!jaqueDespuesDeMovimiento) {
                tablero.jaqueBlanco = true;
                tablero.jaqueNegro = true;
                // aqui se agrega movimiento que quita jaque
                tablero.fichasValidasSalvarJaque.add(movimiento);

            }
        }

        if (tablero.jaqueBlanco || tablero.jaqueNegro) {
            return true;
        }

        tablero.jaqueBlanco = false;
        tablero.jaqueBlanco = false;

        return false;
    }

    public ArrayList<String> detectarJaqueMate(Tablero tablero) {
        tablero.fichasValidasJaqueMate.clear();
        Jugadores fichasEquipo = (tablero.getTurno() == 0) ? tablero.jugador2 : tablero.jugador1;
        for (Fichas ficha : fichasEquipo.fichas) {
            int i = ficha.getPosY();
            int j = ficha.getPosX();
            ficha.movimientoFicha((char) (i + 97) + " " + j, tablero, 3, true, 1);
            // Verificar si los movimientos de la ficha quitan el jaque o no

            ArrayList<String> movimientos = ficha.getLista();

            for (String movimiento : movimientos) {
                String[] pos = movimiento.split(" ");
                int moveX = pos[0].charAt(0) - 'a';
                int moveY = Integer.parseInt(pos[1]);

                // Mover la ficha temporalmente
                int originalX = i;
                int originalY = j;

                Fichas fichaEliminada = SimulacionMoverFicha(
                        ficha,
                        tablero,
                        moveY,
                        moveX);

                // Verificar si el rey está en jaque después del movimiento
                boolean jaqueDespuesDeMovimiento = estaEnJaque2((tablero.getTurno() == 1) ? 0 : 1, tablero);
                // Deshacer el movimiento temporal
                SimulacionRetrocesoFicha(
                        fichaEliminada,
                        ficha,
                        tablero,
                        originalY,
                        originalX);
                // Si el movimiento no pone al rey en jaque, es un movimiento válido para salir
                // del jaque
                if (!jaqueDespuesDeMovimiento) {
                    // aqui se agrega movimiento que quita jaque
                    tablero.fichasValidasJaqueMate.add(movimiento);
                }
            }
        }
        return tablero.fichasValidasJaqueMate;
    }
}
