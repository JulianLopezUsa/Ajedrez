package controlador;

import java.awt.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Tablero.Cuadro;
import modelo.Tablero.Tablero;
import modelo.fichas.Fichas;

/**
 * Servlet implementation class MovimientoFichaServlet
 */
@WebServlet("/MovimientoFichaServlet")
public class MovimientoFichaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Tablero tablero;
    public Cuadro cuadroSeleccionado;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovimientoFichaServlet() {
        super();
        this.tablero = new Tablero("holi", "hilo2");
        this.tablero.inicializarCuadros();
    }
    

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// Obtener parámetros de la solicitud
        String fila = request.getParameter("fila");
        String columna = request.getParameter("columna");

        // Convertir los parámetros a números enteros si es necesario
        int i = Integer.parseInt(fila);
        int j = Integer.parseInt(columna);

        System.out.println("Se recibieron los datos de i: " + i + " y j: " + j);
        
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
}
