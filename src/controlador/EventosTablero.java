package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Tablero.Tablero;
import modelo.fichas.Fichas;
import vista.VistaTablero;

public class EventosTablero implements ActionListener {

    private VistaTablero tablero;
    private Tablero tablero2;
    private Fichas fichaSeleccionada;
    private int cacheX, cacheY;

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
        // Verificar cuál botón se ha presionado
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (e.getSource() == this.tablero.cuadro[i][j]) {
                    // Verificar si hay una ficha en el botón presionado

                    Fichas f = tablero2.hayFicha(i, j, tablero2.getTurno());

                    // Si ya hay una ficha seleccionada, intentar moverla al cuadro presionado
                    if ( tablero.cuadro[i][j].getBackground() == Color.YELLOW) {
                        // Mover la ficha seleccionada al cuadro amarillo
                        tablero.eliminarDeVista(cacheY, cacheX);
                        Fichas fichaX =tablero2.moverFicha(fichaSeleccionada, i, j);
                        
                        if(fichaX!=null){
                            tablero.eliminarDeVista(fichaX.getPosX(), fichaX.getPosY());
                        }
                
                        // Actualizar la vista para reflejar el movimiento
                        actualizarVista();
                        // Limpiar la ficha seleccionada
                        fichaSeleccionada = null;
                    } else {
                        // Si no hay una ficha seleccionada, seleccionar la ficha presionada
                        if (f != null) {
                            fichaSeleccionada = f;
                            cacheX = i;
                            cacheY = j;
                            // Obtener los posibles movimientos de la ficha en esa posición
                            f.movimientoFicha((char) (i + 97) + " " + j, tablero2);
                            // Cambiar el color de los botones correspondientes a los movimientos válidos
                            this.tablero.resaltarMovimientos(f);
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
}
