package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import modelo.Tablero.Cuadro;
import modelo.Tablero.Tablero;
import modelo.fichas.Fichas;
import vista.VistaTablero;

public class EventosTablero implements ActionListener, Eventos {

    public VistaTablero vistaTablero;
    private Tablero tablero;
    public Cuadro cuadroSeleccionado;


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
                if (e.getSource() == this.vistaTablero.cuadro[i][j]) {
                    // Verificar si hay una ficha en el botón presionado
                    Fichas f = tablero.verificaciones.hayFicha(i, j, tablero.getTurno(), tablero);

                    // Encontrar cuadro
                    for (Cuadro cuadro : tablero.listaDeCuadros) {
                        if (cuadro.getX() == i && cuadro.getY() == j) {
                            cuadroSeleccionado = cuadro;
                        }
                    }
                    // Si ya hay una ficha seleccionada, intentar moverla al cuadro presionado
                    // if (vistaTablero.cuadro[i][j].getBackground() == Color.YELLOW) {
                    if (cuadroSeleccionado.getColor() == Color.YELLOW) {
                        tablero.verificaciones.verificarMovimientoAmarillo(i,j, this, tablero, "normal");
                    } else {
                        tablero.verificaciones.VerificarPosiblesMovimientos(f, i, j, tablero, this,"normal");
                        //verificarMovimeintosFicha(f,i,j);
                    }
                    return; // Salir del bucle cuando se encuentre el botón presionado
                }
            }
        }
    }

    public void eliminarDeVista(int x, int y){
        vistaTablero.eliminarDeVista(x, y);
    }

    public void quitarJaqueVista(){
        vistaTablero.quitarJaque();
    }

    public String coronacionFichaVista(int turno, int x, int y){
        String nn = vistaTablero.coronacionPieza(turno,x,y);
        return nn;
    }

    public void cambiarbanderaB(){
        vistaTablero.cambiarBanderaB();
    }

    public void cambiarbanderaN(){
        vistaTablero.cambiarBanderaN();
    }

    public void resetearColoresVista(){
        vistaTablero.resetearColores();
    }

    public void ponerJaqueVista(){
        vistaTablero.ponerJaque(tablero.jaqueBlanco, tablero.jaqueNegro, "blanco",
        tablero.verificaciones.fichaElegida.getPosX(), tablero.verificaciones.fichaElegida.getPosY());
        vistaTablero.ponerJaque(tablero.jaqueBlanco,tablero.jaqueNegro,"negro",
        tablero.verificaciones.fichaElegida.getPosX(),tablero.verificaciones.fichaElegida.getPosY());
        tablero.resetearColores();
        vistaTablero.resetearColores();
    }

    public void mostrarHistorialVista(){
        vistaTablero.mostrarHistorialPartida(tablero.historialPartida);
    }

    public void darAccionBotones() {
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

    public void resaltarMovimientos(ArrayList<String> r){
        vistaTablero.resaltarMovimientos(r);
    }

    @Override
    public void esperarTurno() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'esperarTurno'");
    }

    
}