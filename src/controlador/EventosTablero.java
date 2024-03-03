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

    private VistaTablero tablero;
    private Tablero tablero2;
    private Fichas fichaSeleccionada;
    Fichas fichaElegida;
    private int cacheX, cacheY;
    public boolean nn = false, nn2 = false;
    public boolean jaqueNegro = false, jaqueBlanco = false, banderaJaque = false;
    public ArrayList<Fichas> arrExtra = new ArrayList<>();
    public ArrayList<String> fichasValidasSalvarJaque = new ArrayList<>();
    public ArrayList<String> fichasValidasJaqueMate = new ArrayList<>();

    public EventosTablero(VistaTablero tablero, Tablero tablero2) {
        this.tablero = tablero;
        this.tablero2 = tablero2;
        this.tablero.setVisible(true);
        this.tablero.agregarFichas();

        // Agregar ActionListener a cada botón en el tablero
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tablero.cuadro[i][j].addActionListener(this);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (e.getSource() == this.tablero.cuadro[i][j]) {
                    // Verificar si hay una ficha en el botón presionado
                    Fichas f = tablero2.hayFicha(i, j, tablero2.getTurno());

                    // Si ya hay una ficha seleccionada, intentar moverla al cuadro presionado
                    if (tablero.cuadro[i][j].getBackground() == Color.YELLOW) {

                        // Mover la ficha seleccionada al cuadro amarillo
                        tablero.eliminarDeVista(cacheY, cacheX);
                        // CAMBIO DE VISTA ENROQUE
                        if (fichaSeleccionada instanceof Rey) {
                            if (!cambioVistaEnroque(fichaSeleccionada, i, j, tablero)) {
                                arrExtra = tablero2.moverFicha(fichaSeleccionada, i, j);
                            }
                        } else {
                            arrExtra = tablero2.moverFicha(fichaSeleccionada, i, j);
                        }
                        fichaSeleccionada.movio = true;

                        Fichas fichaX = arrExtra.get(0);
                        if (fichaX != null) {
                            tablero.eliminarDeVista(fichaX.getPosX(), fichaX.getPosY());

                        }

                        tablero.banderaJaque_blancaa = false;
                        tablero.banderaJaque_negras = false;
                        tablero.quitarJaque();

                        // Actualizar la vista para reflejar el movimiento
                        actualizarVista();

                        // Para coronación del peón
                        if (fichaSeleccionada instanceof Peon) {
                            if (((Peon) fichaSeleccionada).alcanzoExtremoTablero(i, j)) {
                                tablero2.eliminarFicha(fichaSeleccionada);
                                tablero.eliminarDeVista(
                                        fichaSeleccionada.getPosX(),
                                        fichaSeleccionada.getPosY());

                                String nn = tablero.coronacionPieza(
                                        tablero2.getTurno(),
                                        fichaSeleccionada.getPosY(),
                                        fichaSeleccionada.getPosX());
                                tablero2.crearFichaNueva(
                                        nn,
                                        fichaSeleccionada.getPosX(),
                                        fichaSeleccionada.getPosY());
                            }
                        }

                        if (tablero2.getTurno() == 1) {
                            if (tablero2.jaqueBlanco == false) {
                                tablero.banderaJaque_blancaa = false;
                            }
                            if (tablero2.jaqueNegro == false) {
                                tablero.banderaJaque_negras = false;
                            }

                            tablero.resetearColores();
                            // Verificar Jaque
                            nn = verificarJaque(1);

                        } else {
                            if (tablero2.jaqueBlanco == false) {
                                tablero.banderaJaque_blancaa = false;
                            }
                            if (tablero2.jaqueNegro == false) {
                                tablero.banderaJaque_negras = false;
                            }

                            tablero.resetearColores();
                            nn2 = verificarJaque(0);
                        }
                        if (!nn && !nn2) {
                            banderaJaque = false;
                            tablero.resetearColores();
                        }
                        // Cambiar turno
                        tablero2.turno = (tablero2.turno + 1) % 2;

                        // SE VERIFICA JAQUE MATE
                        if (banderaJaque) {
                            if (detectarJaqueMate().isEmpty()) {
                                if (tablero2.getTurno() == 0) {
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
                                    this.tablero.resaltarMovimientos(fichasValidasSalvarJaque);
                                }

                            } else {
                                if (!banderaJaque) {
                                    // Obtener los posibles movimientos de la ficha en esa posición
                                    f.movimientoFicha((char) (i + 97) + " " + j, tablero2, 3, banderaJaque);
                                    this.tablero.resaltarMovimientos(f.getLista());
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
        tablero.resetearColores(); // Resetear los colores de los botones del tablero

        for (Fichas ficha : tablero2.jugador1.fichas) {
            int posX = ficha.getPosX();
            int posY = ficha.getPosY();
            tablero.actualizar(posX, posY, ficha);
        }
        for (Fichas ficha : tablero2.jugador2.fichas) {
            int posX = ficha.getPosX();
            int posY = ficha.getPosY();
            tablero.actualizar(posX, posY, ficha);
        }
    }

    public boolean verificarJaque(int turnoo) {
        if (tablero2.estaEnJaque(turnoo)) {
            if (turnoo == 1) {
                JOptionPane.showMessageDialog(null, "¡El rey Blanco está en jaque!");
                for (Fichas ficha : tablero2.jugador2.fichas) {
                    if (ficha instanceof Rey) {
                        fichaElegida = ficha; // Devolver la instancia del rey
                    }
                }
                tablero.ponerJaque(tablero2.jaqueBlanco, tablero2.jaqueNegro, "blanco",
                        fichaElegida.getPosX(), fichaElegida.getPosY());
                tablero.ponerJaque(
                        tablero2.jaqueBlanco,
                        tablero2.jaqueNegro,
                        "negro",
                        fichaElegida.getPosX(),
                        fichaElegida.getPosY());
                jaqueNegro = true;
                banderaJaque = true;
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "¡El rey Negro está en jaque!");
                for (Fichas ficha : tablero2.jugador1.fichas) {
                    if (ficha instanceof Rey) {
                        fichaElegida = ficha; // Devolver la instancia del rey
                    }
                }
                tablero.ponerJaque(tablero2.jaqueBlanco, tablero2.jaqueNegro, "negro",
                        fichaElegida.getPosX(), fichaElegida.getPosY());
                tablero.ponerJaque(
                        tablero2.jaqueBlanco,
                        tablero2.jaqueNegro,
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
        ficha.movimientoFicha((char) (i + 97) + " " + j, tablero2, 3, true);
        // Verificar si los movimientos de la ficha quitan el jaque o no

        ArrayList<String> movimientos = ficha.getLista();

        for (String movimiento : movimientos) {
            String[] pos = movimiento.split(" ");
            int moveX = pos[0].charAt(0) - 'a';
            int moveY = Integer.parseInt(pos[1]);

            // Mover la ficha temporalmente
            int originalX = i;
            int originalY = j;

            Fichas fichaEliminada = tablero2.SimulacionMoverFicha(
                    ficha,
                    tablero2,
                    moveY,
                    moveX);

            // Verificar si el rey está en jaque después del movimiento
            boolean jaqueDespuesDeMovimiento = tablero2.estaEnJaque2((tablero2.getTurno() == 1) ? 0 : 1);
            // Deshacer el movimiento temporal
            tablero2.SimulacionRetrocesoFicha(
                    fichaEliminada,
                    ficha,
                    tablero2,
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
        Jugadores fichasEquipo = (tablero2.getTurno() == 0) ? tablero2.jugador2 : tablero2.jugador1;
        for (Fichas ficha : fichasEquipo.fichas) {
            int i = ficha.getPosY();
            int j = ficha.getPosX();
            ficha.movimientoFicha((char) (i + 97) + " " + j, tablero2, 3, true);
            // Verificar si los movimientos de la ficha quitan el jaque o no

            ArrayList<String> movimientos = ficha.getLista();

            for (String movimiento : movimientos) {
                String[] pos = movimiento.split(" ");
                int moveX = pos[0].charAt(0) - 'a';
                int moveY = Integer.parseInt(pos[1]);

                // Mover la ficha temporalmente
                int originalX = i;
                int originalY = j;

                Fichas fichaEliminada = tablero2.SimulacionMoverFicha(
                        ficha,
                        tablero2,
                        moveY,
                        moveX);

                // Verificar si el rey está en jaque después del movimiento
                boolean jaqueDespuesDeMovimiento = tablero2.estaEnJaque2((tablero2.getTurno() == 1) ? 0 : 1);
                // Deshacer el movimiento temporal
                tablero2.SimulacionRetrocesoFicha(
                        fichaEliminada,
                        ficha,
                        tablero2,
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

    public boolean cambioVistaEnroque(Fichas fichaSeleccionada, int i, int j, VistaTablero tablero) {

        Jugadores equipo = (tablero2.getTurno() == 1) ? tablero2.jugador1 : tablero2.jugador2;
        if (fichaSeleccionada instanceof Rey) {
            Rey rey = (Rey) fichaSeleccionada;
            if (rey.enroque == true) {
                // Eroque largo
                System.err.println("i"+i);
                if (j < rey.getPosX()) {
                    System.out.println("entra");
                    int cachexx = rey.getPosX();
                    int cacheyy = rey.getPosY();
                    tablero.eliminarDeVista(cacheyy, cachexx);
                    tablero2.moverFicha(fichaSeleccionada, i, j);
                    actualizarVista();
                    for (Fichas fichas : equipo.getFichas()) {
                        if (fichas instanceof Torre) {
                            Torre tor = ((Torre) fichas);
                            System.out.println("Y"+tor.getPosY());
                            if ((tor.getPosX() == 0)) {
                                cachexx = tor.getPosX();
                                cacheyy = tor.getPosY();
                                tablero.eliminarDeVista(cachexx, cacheyy);
                                tablero2.moverFicha(tor, i, tor.getPosX() +3);
                                return true;
                            }
                        }
                    }
                }

                // Eroque corto
                else if (j > rey.getPosX()) {
                    int cachexx = rey.getPosX();
                    int cacheyy = rey.getPosY();
                    tablero.eliminarDeVista(cacheyy, cachexx);
                    tablero2.moverFicha(fichaSeleccionada, i, j);
                    actualizarVista();
                    for (Fichas fichas : equipo.getFichas()) {
                        if (fichas instanceof Torre) {
                            Torre tor = ((Torre) fichas);
                            if ((tor.getPosX() == 7)) {
                                cachexx = tor.getPosX();
                                cacheyy = tor.getPosY();
                                tablero.eliminarDeVista(cachexx, cacheyy);
                                tablero2.moverFicha(tor, i, tor.getPosX() - 2);
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
