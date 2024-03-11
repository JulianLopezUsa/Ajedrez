package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import modelo.Tablero.Cuadro;
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
    public Cuadro cuadroSeleccionado;
    public boolean nn = false, nn2 = false;
    public boolean jaqueNegro = false, jaqueBlanco = false, banderaJaque = false;

    public ArrayList<Fichas> arrExtra = new ArrayList<>();

    public EventosTablero(VistaTablero vistaTablero, Tablero tablero) {
        this.vistaTablero = vistaTablero;
        this.tablero = tablero;
        this.vistaTablero.setVisible(true);
        this.tablero.inicializarCuadros();
        this.vistaTablero.agregarFichas();
        darAccionBotones();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(e.getSource() == this.vistaTablero.cuadro[i][j]) {
                    // Verificar si hay una ficha en el botón presionado
                    Fichas f = tablero.verificaciones.hayFicha(i, j, tablero.getTurno(), tablero);
                    
                    //Encontrar cuadro
                    for(Cuadro cuadro : tablero.listaDeCuadros){
                        if(cuadro.getX()==i && cuadro.getY()==j){
                            cuadroSeleccionado = cuadro;
                        }
                    }
                    // Si ya hay una ficha seleccionada, intentar moverla al cuadro presionado
                    //if (vistaTablero.cuadro[i][j].getBackground() == Color.YELLOW) {
                    if(cuadroSeleccionado.getColor() == Color.YELLOW) {
                        // Mover la ficha seleccionada al cuadro amarillo
                        vistaTablero.eliminarDeVista(cacheY, cacheX);
                        // CAMBIO DE VISTA ENROQUE
                        if (fichaSeleccionada instanceof Rey) {
                            if (!cambioVistaEnroque(fichaSeleccionada, i, j, vistaTablero)) {
                                System.out.println("entra aca");
                                vistaTablero.eliminarDeVista(cacheY, cacheX);
                                arrExtra = tablero.moverFicha(fichaSeleccionada, i, j);
                                actualizarVista();
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
                                if (tablero.verificaciones.esMovimientoValidoParaSalirDelJaque(f, i, j, tablero)) {
                                    this.vistaTablero.resaltarMovimientos(tablero.fichasValidasSalvarJaque);
                                    tablero.resetearColores();
                                    tablero.resaltarMovimeintosCuadros(tablero.fichasValidasSalvarJaque);
                                }

                            } else {
                                if (!banderaJaque) {
                                    // Obtener los posibles movimientos de la ficha en esa posición
                                    f.movimientoFicha((char) (i + 97) + " " + j, tablero, 3, banderaJaque, 0);
                                    this.vistaTablero.resaltarMovimientos(f.getLista());
                                    tablero.resetearColores();
                                    tablero.resaltarMovimeintosCuadros(f.getLista());
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

    public void darAccionBotones(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                vistaTablero.cuadro[i][j].addActionListener(this);
            }
        }
    }

    public void actualizarVista() {
        // Actualizar la vista de acuerdo a la configuración actual del tablero
        vistaTablero.resetearColores(); // Resetear los colores de los botones del tablero
        tablero.resetearColores();
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
        if (tablero.verificaciones.estaEnJaque(turnoo, tablero)) {
            if (turnoo == 1) {
                JOptionPane.showMessageDialog(null, "¡El rey Blanco está en jaque!");
                for (Fichas ficha : tablero.jugador2.fichas) {
                    if (ficha instanceof Rey) {
                        fichaElegida = ficha; // Devolver la instancia del rey
                    }
                }
                vistaTablero.ponerJaque(tablero.jaqueBlanco, tablero.jaqueNegro, "blanco",
                        fichaElegida.getPosX(), fichaElegida.getPosY());
                tablero.ponerJaque(fichaElegida.getPosX(), fichaElegida.getPosY());
                
                vistaTablero.ponerJaque(tablero.jaqueBlanco,
                        tablero.jaqueNegro,
                        "negro",
                        fichaElegida.getPosX(),
                        fichaElegida.getPosY());
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
                vistaTablero.ponerJaque(tablero.jaqueBlanco, tablero.jaqueNegro, "negro",
                        fichaElegida.getPosX(), fichaElegida.getPosY());
                tablero.ponerJaque(fichaElegida.getPosX(), fichaElegida.getPosY());
                
                vistaTablero.ponerJaque(
                        tablero.jaqueBlanco,
                        tablero.jaqueNegro,
                        "blanco",
                        fichaElegida.getPosX(),
                        fichaElegida.getPosY());
                tablero.ponerJaque(fichaElegida.getPosX(), fichaElegida.getPosY());
                
                jaqueBlanco = true;
                banderaJaque = true;
                return true;
            }
        }
        return false;
    }

    public boolean cambioVistaEnroque(Fichas fichaSeleccionada, int i, int j, VistaTablero vistaTablero) {
        Jugadores equipo = (tablero.getTurno() == 1) ? tablero.jugador1 : tablero.jugador2;
        if (fichaSeleccionada instanceof Rey) {
            Rey rey = (Rey) fichaSeleccionada;
            if (rey.enroque == true) {
                // Eroque largo
                if (j < (rey.getPosX()-1) && i==rey.getPosY()) {
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
                else if (j > (rey.getPosX()+1) && i==rey.getPosY()) {
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