package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Tablero.Tablero;
import modelo.fichas.Fichas;
import modelo.fichas.Peon;
import modelo.fichas.Rey;
import modelo.fichas.Torre;
import modelo.jugadores.Jugadores;
import vista.VistaTablero;

public class EventosTablero implements ActionListener {

    private VistaTablero vistaTablero;
    private Tablero tablero;
    private Fichas fichaSeleccionada;
    Fichas fichaElegida;
    private int cacheX, cacheY;
    
    public boolean nn = false, nn2 = false;
    public boolean jaqueNegro = false, jaqueBlanco = false, banderaJaque = false;

    public ArrayList<Fichas> arrExtra = new ArrayList<>();
    public ArrayList<String> fichasValidasSalvarJaque = new ArrayList<>();
    public ArrayList<String> fichasValidasJaqueMate = new ArrayList<>();

    public EventosTablero(VistaTablero vistaTablero, Tablero tablero) {
        this.vistaTablero = vistaTablero;
        this.tablero = tablero;
        this.vistaTablero.setVisible(true);
        this.vistaTablero.agregarFichas();

        // Agregar ActionListener a cada botón en el tablero
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                vistaTablero.cuadro[i][j].addActionListener(this);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (e.getSource() == this.vistaTablero.cuadro[i][j]) {
                    // Verificar si hay una ficha en el botón presionado
                    Fichas f = tablero.hayFicha(i, j, tablero.getTurno());

                    // Si ya hay una ficha seleccionada, intentar moverla al cuadro presionado
                    if (vistaTablero.cuadro[i][j].getBackground() == Color.YELLOW) {

                        // Mover la ficha seleccionada al cuadro amarillo
                        vistaTablero.eliminarDeVista(cacheY, cacheX);
                        // CAMBIO DE VISTA ENROQUE
                        if (fichaSeleccionada instanceof Rey) {
                            if (!cambioVistaEnroque(fichaSeleccionada, i, j, vistaTablero)) {
                                arrExtra = tablero.moverFicha(fichaSeleccionada, i, j);
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
                                    vistaTablero.eliminarDeVista(peonsito.getPosX(), peonsito.getPosY());
                                }
                            }
                            arrExtra = tablero.moverFicha(fichaSeleccionada, i, j);

                        } else {
                            arrExtra = tablero.moverFicha(fichaSeleccionada, i, j);
                        }
                        fichaSeleccionada.movio = true;

                        Fichas fichaX = arrExtra.get(0);
                        if (fichaX != null) {
                            vistaTablero.eliminarDeVista(fichaX.getPosX(), fichaX.getPosY());

                        }

                        vistaTablero.banderaJaque_blancaa = false;
                        vistaTablero.banderaJaque_negras = false;
                        vistaTablero.quitarJaque();

                        // Actualizar la vista para reflejar el movimiento
                        actualizarVista();

                        // Para coronación del peón
                        if (fichaSeleccionada instanceof Peon) {
                            if (((Peon) fichaSeleccionada).alcanzoExtremoTablero(i, j)) {
                                tablero.eliminarFicha(fichaSeleccionada);
                                vistaTablero.eliminarDeVista(
                                        fichaSeleccionada.getPosX(),
                                        fichaSeleccionada.getPosY());

                                String nn = vistaTablero.coronacionPieza(
                                        tablero.getTurno(),
                                        fichaSeleccionada.getPosY(),
                                        fichaSeleccionada.getPosX());
                                tablero.crearFichaNueva(
                                        nn,
                                        fichaSeleccionada.getPosX(),
                                        fichaSeleccionada.getPosY());
                            }
                        }

                        if (tablero.getTurno() == 1) {
                            if (tablero.jaqueBlanco == false) {
                                vistaTablero.banderaJaque_blancaa = false;
                            }
                            if (tablero.jaqueNegro == false) {
                                vistaTablero.banderaJaque_negras = false;
                            }

                            vistaTablero.resetearColores();
                            // Verificar Jaque
                            nn = verificarJaque(1);

                        } else {
                            if (tablero.jaqueBlanco == false) {
                                vistaTablero.banderaJaque_blancaa = false;
                            }
                            if (tablero.jaqueNegro == false) {
                                vistaTablero.banderaJaque_negras = false;
                            }

                            vistaTablero.resetearColores();
                            nn2 = verificarJaque(0);
                        }
                        if (!nn && !nn2) {
                            banderaJaque = false;
                            vistaTablero.resetearColores();
                        }
                        // Actualizar historial y  Cambiar turno
                        vistaTablero.mostrarHistorialPartida(tablero.historialPartida);
                        tablero.turno = (tablero.turno + 1) % 2;

                        // SE VERIFICA JAQUE MATE
                        if (banderaJaque) {
                            if (detectarJaqueMate().isEmpty()) {
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
                    } else {
                        // Si no hay una ficha seleccionada, seleccionar la ficha presionada
                        if (f != null) {
                            actualizarVista();
                            fichaSeleccionada = f;
                            cacheX = i;
                            cacheY = j;

                            // Se tiene que verificar si no está en jaque la ficha entonces permitir
                            // movimeinto
                            // Si sí se encuentra en jaque entonces no permite movimiento
                            if (banderaJaque) {
                                if (esMovimientoValidoParaSalirDelJaque(f, i, j)) {
                                    this.vistaTablero.resaltarMovimientos(fichasValidasSalvarJaque);
                                }

                            } else {
                                if (!banderaJaque) {
                                    // Obtener los posibles movimientos de la ficha en esa posición
                                    f.movimientoFicha((char) (i + 97) + " " + j, tablero, 3, banderaJaque, 0);
                                    this.vistaTablero.resaltarMovimientos(f.getLista());
                                    System.out.println(f.getLista());
                                }
                            }

                        }
                    }
                    return; // Salir del bucle cuando se encuentre el botón presionado
                }
            }
        }

    }

   

    public void actualizarVista() {
        // Actualizar la vista de acuerdo a la configuración actual del tablero
        vistaTablero.resetearColores(); // Resetear los colores de los botones del tablero

        for (Fichas ficha : tablero.jugador1.fichas) {
            int posX = ficha.getPosX();
            int posY = ficha.getPosY();
            vistaTablero.actualizar(posX, posY, ficha);
        }
        for (Fichas ficha : tablero.jugador2.fichas) {
            int posX = ficha.getPosX();
            int posY = ficha.getPosY();
            vistaTablero.actualizar(posX, posY, ficha);
        }
    }

    public boolean verificarJaque(int turnoo) {
        if (tablero.estaEnJaque(turnoo)) {
            if (turnoo == 1) {
                JOptionPane.showMessageDialog(null, "¡El rey Blanco está en jaque!");
                for (Fichas ficha : tablero.jugador2.fichas) {
                    if (ficha instanceof Rey) {
                        fichaElegida = ficha; // Devolver la instancia del rey
                    }
                }
                vistaTablero.ponerJaque(tablero.jaqueBlanco, tablero.jaqueNegro, "blanco",
                        fichaElegida.getPosX(), fichaElegida.getPosY());
                vistaTablero.ponerJaque(
                        tablero.jaqueBlanco,
                        tablero.jaqueNegro,
                        "negro",
                        fichaElegida.getPosX(),
                        fichaElegida.getPosY());
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
                vistaTablero.ponerJaque(tablero.jaqueBlanco, tablero.jaqueNegro, "negro",
                        fichaElegida.getPosX(), fichaElegida.getPosY());
                vistaTablero.ponerJaque(
                        tablero.jaqueBlanco,
                        tablero.jaqueNegro,
                        "blanco",
                        fichaElegida.getPosX(),
                        fichaElegida.getPosY());
                jaqueBlanco = true;
                banderaJaque = true;
                return true;
            }
        }
        return false;
    }

    // Método para verificar si un movimiento saca al rey del jaque
    private boolean esMovimientoValidoParaSalirDelJaque(Fichas ficha, int i, int j) {
        fichasValidasSalvarJaque.clear();
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

            Fichas fichaEliminada = tablero.SimulacionMoverFicha(
                    ficha,
                    tablero,
                    moveY,
                    moveX);

            // Verificar si el rey está en jaque después del movimiento
            boolean jaqueDespuesDeMovimiento = tablero.estaEnJaque2((tablero.getTurno() == 1) ? 0 : 1);
            // Deshacer el movimiento temporal
            tablero.SimulacionRetrocesoFicha(
                    fichaEliminada,
                    ficha,
                    tablero,
                    originalY,
                    originalX);
            // Si el movimiento no pone al rey en jaque, es un movimiento válido para salir
            // del jaque
            if (!jaqueDespuesDeMovimiento) {
                this.jaqueBlanco = true;
                this.jaqueNegro = true;
                // aqui se agrega movimiento que quita jaque
                fichasValidasSalvarJaque.add(movimiento);

            }
        }

        if (this.jaqueBlanco || this.jaqueNegro) {
            return true;
        }

        this.jaqueBlanco = false;
        this.jaqueBlanco = false;

        return false;
    }

    public ArrayList<String> detectarJaqueMate() {
        fichasValidasJaqueMate.clear();
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

                Fichas fichaEliminada = tablero.SimulacionMoverFicha(
                        ficha,
                        tablero,
                        moveY,
                        moveX);

                // Verificar si el rey está en jaque después del movimiento
                boolean jaqueDespuesDeMovimiento = tablero.estaEnJaque2((tablero.getTurno() == 1) ? 0 : 1);
                // Deshacer el movimiento temporal
                tablero.SimulacionRetrocesoFicha(
                        fichaEliminada,
                        ficha,
                        tablero,
                        originalY,
                        originalX);
                // Si el movimiento no pone al rey en jaque, es un movimiento válido para salir
                // del jaque
                if (!jaqueDespuesDeMovimiento) {
                    // aqui se agrega movimiento que quita jaque
                    fichasValidasJaqueMate.add(movimiento);
                }
            }
        }
        return fichasValidasJaqueMate;
    }

    public boolean cambioVistaEnroque(Fichas fichaSeleccionada, int i, int j, VistaTablero vistaTablero) {

        Jugadores equipo = (tablero.getTurno() == 1) ? tablero.jugador1 : tablero.jugador2;
        if (fichaSeleccionada instanceof Rey) {
            Rey rey = (Rey) fichaSeleccionada;
            if (rey.enroque == true) {
                // Eroque largo
                if (j < rey.getPosX()) {
                    int cachexx = rey.getPosX();
                    int cacheyy = rey.getPosY();
                    vistaTablero.eliminarDeVista(cacheyy, cachexx);
                    tablero.moverFicha(fichaSeleccionada, i, j);
                    actualizarVista();
                    for (Fichas fichas : equipo.getFichas()) {
                        if (fichas instanceof Torre) {
                            Torre tor = ((Torre) fichas);
                            if ((tor.getPosX() == 0)) {
                                cachexx = tor.getPosX();
                                cacheyy = tor.getPosY();
                                vistaTablero.eliminarDeVista(cachexx, cacheyy);
                                tablero.moverFicha(tor, i, tor.getPosX() + 3);
                                return true;
                            }
                        }
                    }
                }

                // Eroque corto
                else if (j > rey.getPosX()) {
                    int cachexx = rey.getPosX();
                    int cacheyy = rey.getPosY();
                    vistaTablero.eliminarDeVista(cacheyy, cachexx);
                    tablero.moverFicha(fichaSeleccionada, i, j);
                    actualizarVista();
                    for (Fichas fichas : equipo.getFichas()) {
                        if (fichas instanceof Torre) {
                            Torre tor = ((Torre) fichas);
                            if ((tor.getPosX() == 7)) {
                                cachexx = tor.getPosX();
                                cacheyy = tor.getPosY();
                                vistaTablero.eliminarDeVista(cachexx, cacheyy);
                                tablero.moverFicha(tor, i, tor.getPosX() - 2);
                                return true;
                            }
                        }
                    }
                }
                actualizarVista();
            }
        }
        return false;
    }
}
