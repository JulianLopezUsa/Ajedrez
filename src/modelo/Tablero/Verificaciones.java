package modelo.Tablero;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import controlador.EventosTablero;
import modelo.fichas.Fichas;
import modelo.fichas.Peon;
import modelo.fichas.Rey;
import modelo.fichas.Torre;
import modelo.jugadores.Jugadores;

public class Verificaciones {

    public Fichas fichaElegida;
    public boolean nn = false, nn2 = false;
    public ArrayList<Fichas> arrExtra = new ArrayList<>();
    public boolean jaqueNegro = false, jaqueBlanco = false, banderaJaque = false;
    public Fichas fichaSeleccionada;
    public int cacheX, cacheY;

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
            ficha.movimientoFicha((char) (j + 97) + " " + i, tablero, turno, true, 1);

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
            ficha.movimientoFicha((char) (j + 97) + " " + i, tablero, turno, true, 1);

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

    public boolean verificarJaque(int turnoo, Tablero tablero) {
        if (tablero.verificaciones.estaEnJaque(turnoo, tablero)) {
            if (turnoo == 1) {
                JOptionPane.showMessageDialog(null, "¡El rey Blanco está en jaque!");
                for (Fichas ficha : tablero.jugador2.fichas) {
                    if (ficha instanceof Rey) {
                        fichaElegida = ficha; // Devolver la instancia del rey
                    }
                }
                // vistaTablero.ponerJaque(tablero.jaqueBlanco, tablero.jaqueNegro, "blanco",
                // fichaElegida.getPosX(), fichaElegida.getPosY());
                tablero.ponerJaque(fichaElegida.getPosX(), fichaElegida.getPosY());

                // vistaTablero.ponerJaque(tablero.jaqueBlanco,tablero.jaqueNegro,"negro",
                // fichaElegida.getPosX(),fichaElegida.getPosY());
                tablero.ponerJaque(fichaElegida.getPosX(), fichaElegida.getPosY());
                jaqueNegro = true;
                banderaJaque = true;
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "¡El rey Negro está en jaque!");
                for (Fichas ficha : tablero.jugador1.fichas) {
                    if (ficha instanceof Rey) {
                        fichaElegida = ficha; // Devolver la instancia del rey
                    }
                }
                // vistaTablero.ponerJaque(tablero.jaqueBlanco, tablero.jaqueNegro, "negro",
                // fichaElegida.getPosX(), fichaElegida.getPosY());
                tablero.ponerJaque(fichaElegida.getPosX(), fichaElegida.getPosY());

                // vistaTablero.ponerJaque(7tablero.jaqueBlanco,7tablero.jaqueNegro,7"blanco",
                // fichaElegida.getPosX(),7fichaElegida.getPosY());
                tablero.ponerJaque(fichaElegida.getPosX(), fichaElegida.getPosY());

                jaqueBlanco = true;
                banderaJaque = true;
                return true;
            }
        }
        return false;
    }

    public void VerificarPosiblesMovimientos(Fichas f, int i, int j, Tablero tablero, EventosTablero ev) {
        if (f != null) {
            ev.actualizarVista();
            fichaSeleccionada = f;
            cacheX = i;
            cacheY = j;
            // Se tiene que verificar si no está en jaque la ficha entonces permitir
            // movimeinto
            // Si sí se encuentra en jaque entonces no permite movimiento
            if (tablero.verificaciones.banderaJaque) {
                if (tablero.verificaciones.esMovimientoValidoParaSalirDelJaque(f, i, j, tablero)) {
                    tablero.resaltarMovimeintosCuadros(tablero.fichasValidasSalvarJaque);
                    ev.resaltarMovimientos(tablero.fichasValidasSalvarJaque);
                }
            } else if (!tablero.verificaciones.banderaJaque) {
                // Obtener los posibles movimientos de la ficha en esa posición
                f.movimientoFicha((char) (i + 97) + " " + j, tablero, 3, tablero.verificaciones.banderaJaque, 0);
                tablero.resaltarMovimeintosCuadros(f.getLista());
                ev.resaltarMovimientos(f.getLista());
            }
        }
    }

    public void verificarMovimientoAmarillo(int i, int j, EventosTablero ev, Tablero tablero) {
        // Mover la ficha seleccionada al cuadro amarillo

        ev.eliminarDeVista(cacheY, cacheX);
        // vistaTablero.eliminarDeVista(cacheY, cacheX);

        // CAMBIO DE VISTA ENROQUE
        if (fichaSeleccionada instanceof Rey) {
            if (!cambioVistaEnroque(fichaSeleccionada, i, j, tablero, ev)) {
                ev.eliminarDeVista(cacheY, cacheX);
                // vistaTablero.eliminarDeVista(cacheY, cacheX);
                arrExtra = tablero.moverFicha(fichaSeleccionada, i, j);
                ev.actualizarVista();
            }
        } else if (fichaSeleccionada instanceof Peon) {
            Peon peon = (Peon) fichaSeleccionada;
            if (peon.banderaPeonAlPaso) {
                String[] pos = peon.movimeintoPeonAlPaso.get(0).split(" ");
                int x = Integer.parseInt(pos[0]);
                int y = Integer.parseInt(pos[1]);
                if (x == i && y == j) {
                    Peon peonsito = (Peon) tablero.historialFichas
                            .get(tablero.historialFichas.size() - 1);
                    if (tablero.getTurno() == 0) {
                        tablero.jugador1.fichas.remove(peonsito);
                    } else {
                        tablero.jugador2.fichas.remove(peonsito);
                    }
                    ev.eliminarDeVista(peonsito.getPosX(), peonsito.getPosY());
                    // vistaTablero.eliminarDeVista(peonsito.getPosX(), peonsito.getPosY());
                }
            }
            arrExtra = tablero.moverFicha(fichaSeleccionada, i, j);

        } else {
            arrExtra = tablero.moverFicha(fichaSeleccionada, i, j);
        }
        fichaSeleccionada.movio = true;

        Fichas fichaX = arrExtra.get(0);
        if (fichaX != null) {
            ev.eliminarDeVista(fichaX.getPosX(), fichaX.getPosY());
            // vistaTablero.eliminarDeVista(fichaX.getPosX(), fichaX.getPosY());
        }

        // vistaTablero.banderaJaque_blancaa = false;
        // vistaTablero.banderaJaque_negras = false;

        ev.quitarJaqueVista();
        // vistaTablero.quitarJaque();
        // Actualizar la vista para reflejar el movimiento
        ev.actualizarVista();

        // Para coronación del peón
        if (fichaSeleccionada instanceof Peon) {
            if (((Peon) fichaSeleccionada).alcanzoExtremoTablero(i, j)) {
                tablero.eliminarFicha(fichaSeleccionada);
                ev.eliminarDeVista(fichaSeleccionada.getPosX(), fichaSeleccionada.getPosY());
                // vistaTablero.eliminarDeVista(
                // fichaSeleccionada.getPosX(),
                // fichaSeleccionada.getPosY());

                String nn = ev.coronacionFichaVista(tablero.getTurno(),
                        fichaSeleccionada.getPosY(),
                        fichaSeleccionada.getPosX());
                /*
                 * String nn = vistaTablero.coronacionPieza(
                 * tablero.getTurno(),
                 * fichaSeleccionada.getPosY(),
                 * fichaSeleccionada.getPosX());
                 */
                tablero.crearFichaNueva(
                        nn,
                        fichaSeleccionada.getPosX(),
                        fichaSeleccionada.getPosY());
            }
        }

        if (tablero.getTurno() == 1) {
            if (tablero.jaqueBlanco == false) {
                ev.cambiarbanderaB();
            }
            if (tablero.jaqueNegro == false) {
                ev.cambiarbanderaN();
            }

            tablero.resetearColores();
            ev.resetearColoresVista();
            // vistaTablero.resetearColores();
            // Verificar Jaque
            nn = tablero.verificaciones.verificarJaque(1, tablero);

        } else {
            if (tablero.jaqueBlanco == false) {
                ev.cambiarbanderaB();
            }
            if (tablero.jaqueNegro == false) {
                ev.cambiarbanderaN();
            }
            tablero.resetearColores();
            ev.resetearColoresVista();
            // vistaTablero.resetearColores();
            nn2 = tablero.verificaciones.verificarJaque(0, tablero);
        }

        if (!nn && !nn2) {
            tablero.verificaciones.banderaJaque = false;
            tablero.resetearColores();
            ev.resetearColoresVista();
            // vistaTablero.resetearColores();

        } else if (nn || nn2) {
            ev.ponerJaqueVista();
        }

        // Actualizar historial y Cambiar turno
        ev.mostrarHistorialVista();

        tablero.turno = (tablero.turno + 1) % 2;

        // SE VERIFICA JAQUE MATE
        if (tablero.verificaciones.banderaJaque) {
            if (tablero.verificaciones.detectarJaqueMate(tablero).isEmpty()) {
                if (tablero.getTurno() == 0) {
                    JOptionPane.showMessageDialog(null,
                            "Jaque mate al equipo blanco. Gana el equipo negro");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Jaque mate al equipo negro. Gana el equipo blanco");
                }
                System.exit(0);
            }
        }
        // Limpiar la ficha seleccionada
        fichaSeleccionada = null;
    }

    public boolean cambioVistaEnroque(Fichas fichaSeleccionada, int i, int j, Tablero tablero, EventosTablero ev) {
        Jugadores equipo = (tablero.getTurno() == 1) ? tablero.jugador1 : tablero.jugador2;
        if (fichaSeleccionada instanceof Rey) {
            Rey rey = (Rey) fichaSeleccionada;
            if (rey.enroque == true) {
                // Eroque largo
                if (j < (rey.getPosX() - 1) && i == rey.getPosY()) {
                    int cachexx = rey.getPosX();
                    int cacheyy = rey.getPosY();
                    ev.eliminarDeVista(cacheyy, cachexx);
                    // vistaTablero.eliminarDeVista(cacheyy, cachexx);
                    tablero.moverFicha(fichaSeleccionada, i, j);
                    ev.actualizarVista();
                    for (Fichas fichas : equipo.getFichas()) {
                        if (fichas instanceof Torre) {
                            Torre tor = ((Torre) fichas);
                            if ((tor.getPosX() == 0)) {
                                cachexx = tor.getPosX();
                                cacheyy = tor.getPosY();
                                ev.eliminarDeVista(cachexx, cacheyy);
                                // vistaTablero.eliminarDeVista(cachexx, cacheyy);
                                tablero.moverFicha(tor, i, tor.getPosX() + 3);
                                return true;
                            }
                        }
                    }
                }
                // Eroque corto
                else if (j > (rey.getPosX() + 1) && i == rey.getPosY()) {
                    int cachexx = rey.getPosX();
                    int cacheyy = rey.getPosY();
                    ev.eliminarDeVista(cacheyy, cachexx);
                    // vistaTablero.eliminarDeVista(cacheyy, cachexx);
                    tablero.moverFicha(fichaSeleccionada, i, j);
                    ev.actualizarVista();
                    for (Fichas fichas : equipo.getFichas()) {
                        if (fichas instanceof Torre) {
                            Torre tor = ((Torre) fichas);
                            if ((tor.getPosX() == 7)) {
                                cachexx = tor.getPosX();
                                cacheyy = tor.getPosY();
                                ev.eliminarDeVista(cachexx, cacheyy);
                                // vistaTablero.eliminarDeVista(cachexx, cacheyy);
                                tablero.moverFicha(tor, i, tor.getPosX() - 2);
                                return true;
                            }
                        }
                    }
                }
                ev.actualizarVista();
            }
        }
        return false;
    }

}