package modelo.Tablero;

import java.util.ArrayList;

import modelo.juegoLan;
import modelo.fichas.Fichas;
import modelo.jugadores.Jugadores;

public class VerificacionesLan {

    public Fichas hayFicha(int i, int j, int turno, Tablero juegoLan) {
        if (turno == 0) {
            for (Fichas ficha : juegoLan.jugador2.fichas) {
                if (ficha.getPosX() == j && ficha.getPosY() == i) {
                    return ficha;
                }
            }
        } else if (turno == 1) {
            for (Fichas ficha : juegoLan.jugador1.fichas) {
                if (ficha.getPosX() == j && ficha.getPosY() == i) {
                    return ficha;
                }
            }
        }
        return null;
    }

    public Fichas hayFicha2(int i, int j, int turno, juegoLan juegoLan) {
        if (turno == 0) {
            for (Fichas ficha : juegoLan.jugador1.fichas) {
                if (ficha.getPosX() == j && ficha.getPosY() == i) {
                    return ficha;
                }
            }
        } else if (turno == 1) {
            for (Fichas ficha : juegoLan.jugador2.fichas) {
                if (ficha.getPosX() == j && ficha.getPosY() == i) {
                    return ficha;
                }
            }
        }
        return null;
    }

    public boolean estaEnJaque(int turno, Tablero juegoLan) {
        // Obtener la posición del rey del oponente
        Fichas rey;
        if (turno == 1) {
            rey = juegoLan.obtenerRey(juegoLan.jugador2.getFichas());
        } else {
            rey = juegoLan.obtenerRey(juegoLan.jugador1.getFichas());
        }
        Jugadores oponente = (turno == 0) ? juegoLan.jugador2 : juegoLan.jugador1;
        for (Fichas ficha : oponente.fichas) {
            int i = ficha.getPosX();
            int j = ficha.getPosY();

            ficha.movimientoFicha((char) (j + 97) + " " + i, juegoLan, 3, true, 1);

            ArrayList<String> movimientos = ficha.listaDeMovimientos;
            for (String movimiento : movimientos) {
                String[] pos = movimiento.split(" ");

                int newY = pos[0].charAt(0) - 'a';
                int newX = Integer.parseInt(pos[1]);

                if (newX == rey.getPosX() && newY == rey.getPosY()) {
                    if (turno == 1) {
                        juegoLan.jaqueNegro = true;
                    } else {
                        juegoLan.jaqueBlanco = true;
                    }
                    return true; // El rey está en jaque
                }
            }
        }

        juegoLan.jaqueBlanco = false;
        juegoLan.jaqueNegro = false;
        return false; // El rey no está en jaque
    }

    public Fichas SimulacionMoverFicha(Fichas fichaSeleccionada, Tablero juegoLan, int i, int j) {

        // SI ENCUENTRA UNA FICHA EN LA PSOCIÓN A LA QUE SE MUEVE LA BORRA NE LA COPIA
        // Fichas fichaEnNuevaPosicion = hayFicha2(i, j, 0);
        Fichas fichaEnNuevaPosicion = hayFicha(j, i, (juegoLan.getTurno() == 1) ? 0 : 1, juegoLan);

        if (fichaEnNuevaPosicion != null) {
            if (((juegoLan.getTurno() == 1) ? 0 : 1) == 1) {
                juegoLan.jugador1.fichas.remove(fichaEnNuevaPosicion);
            } else {
                juegoLan.jugador2.fichas.remove(fichaEnNuevaPosicion);
            }
        }

        // SE ALTERAN LOS VALORES DE LA COPIA DE LA FICHA POR LOS NUEVOS
        fichaSeleccionada.setPosX(i);
        fichaSeleccionada.setPosY(j);

        return fichaEnNuevaPosicion;
    }

    public Fichas SimulacionRetrocesoFicha(Fichas fichaEliminada, Fichas fichaSeleccionada, Tablero juegoLan, int i,
            int j) {

        // VER BIEN EL TRUNO DE ESTA
        if (fichaEliminada != null) {
            if (((juegoLan.getTurno() == 1) ? 0 : 1) == 1) {
                juegoLan.jugador1.fichas.add(fichaEliminada);
            } else {
                juegoLan.jugador2.fichas.add(fichaEliminada);
            }
        }

        fichaSeleccionada.setPosX(i);
        fichaSeleccionada.setPosY(j);

        return fichaSeleccionada;
    }

    public boolean estaEnJaque2(int turno, Tablero juegoLan) {
        // Obtener la posición del rey del oponente
        Fichas rey;
        if (turno == 1) {
            rey = juegoLan.obtenerRey(juegoLan.jugador2.getFichas());
        } else {
            rey = juegoLan.obtenerRey(juegoLan.jugador1.getFichas());
        }
        Jugadores oponente = (turno == 0) ? juegoLan.jugador2 : juegoLan.jugador1;
        
        for (Fichas ficha : oponente.fichas) {
            
            int i = ficha.getPosX();
            int j = ficha.getPosY();
            // ficha.movimientoFicha((char) (j + 97) + " " + i, this, 0);
            ficha.movimientoFicha((char) (j + 97) + " " + i, juegoLan, turno, true,1);

            ArrayList<String> movimientos = ficha.getListaDeMovimientos();
            
            for (String movimiento : movimientos) {
                String[] pos = movimiento.split(" ");

                int newY = pos[0].charAt(0) - 'a';
                int newX = Integer.parseInt(pos[1]);

                if (newY == rey.getPosY() && newX == rey.getPosX()) {
                    if (turno == 1) {
                        juegoLan.jaqueNegro = true;
                    } else {
                        juegoLan.jaqueBlanco = true;
                    }
                    return true; // El rey está en jaque
                }
            }
        }

        juegoLan.jaqueBlanco = false;
        juegoLan.jaqueNegro = false;
        return false; // El rey no está en jaque
    }

    public boolean estaEnJaque3(int turno, Tablero juegoLan) {
        // Obtener la posición del rey del oponente
        Fichas rey;
        if (turno == 1) {
            rey = juegoLan.obtenerRey(juegoLan.jugador2.getFichas());
        } else {
            rey = juegoLan.obtenerRey(juegoLan.jugador1.getFichas());
        }
        Jugadores oponente = (turno == 0) ? juegoLan.jugador2 : juegoLan.jugador1;
        
        for (Fichas ficha : oponente.fichas) {
            
            int i = ficha.getPosX();
            int j = ficha.getPosY();
            // ficha.movimientoFicha((char) (j + 97) + " " + i, this, 0);
            ficha.movimientoFicha((char) (j + 97) + " " + i, juegoLan, turno, true,1);

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

    public boolean esMovimientoValidoParaSalirDelJaque(Fichas ficha, int i, int j, Tablero juegoLan) {
        juegoLan.fichasValidasSalvarJaque.clear();
        ficha.movimientoFicha((char) (i + 97) + " " + j, juegoLan, 3, true, 0);
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
                    juegoLan,
                    moveY,
                    moveX);

            // Verificar si el rey está en jaque después del movimiento
            boolean jaqueDespuesDeMovimiento = estaEnJaque2((juegoLan.getTurno() == 1) ? 0 : 1, juegoLan);
            // Deshacer el movimiento temporal
            SimulacionRetrocesoFicha(
                    fichaEliminada,
                    ficha,
                    juegoLan,
                    originalY,
                    originalX);
            // Si el movimiento no pone al rey en jaque, es un movimiento válido para salir
            // del jaque
            if (!jaqueDespuesDeMovimiento) {
                juegoLan.jaqueBlanco = true;
                juegoLan.jaqueNegro = true;
                // aqui se agrega movimiento que quita jaque
                juegoLan.fichasValidasSalvarJaque.add(movimiento);

            }
        }

        if (juegoLan.jaqueBlanco || juegoLan.jaqueNegro) {
            return true;
        }

        juegoLan.jaqueBlanco = false;
        juegoLan.jaqueBlanco = false;

        return false;
    }

    public ArrayList<String> detectarJaqueMate(Tablero juegoLan) {
        juegoLan.fichasValidasJaqueMate.clear();
        Jugadores fichasEquipo = (juegoLan.getTurno() == 0) ? juegoLan.jugador2 : juegoLan.jugador1;
        for (Fichas ficha : fichasEquipo.fichas) {
            int i = ficha.getPosY();
            int j = ficha.getPosX();
            ficha.movimientoFicha((char) (i + 97) + " " + j, juegoLan, 3, true, 1);
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
                        juegoLan,
                        moveY,
                        moveX);

                // Verificar si el rey está en jaque después del movimiento
                boolean jaqueDespuesDeMovimiento = estaEnJaque2((juegoLan.getTurno() == 1) ? 0 : 1, juegoLan);
                // Deshacer el movimiento temporal
                SimulacionRetrocesoFicha(
                        fichaEliminada,
                        ficha,
                        juegoLan,
                        originalY,
                        originalX);
                // Si el movimiento no pone al rey en jaque, es un movimiento válido para salir
                // del jaque
                if (!jaqueDespuesDeMovimiento) {
                    // aqui se agrega movimiento que quita jaque
                    juegoLan.fichasValidasJaqueMate.add(movimiento);
                }
            }
        }
        return juegoLan.fichasValidasJaqueMate;
    }
}
