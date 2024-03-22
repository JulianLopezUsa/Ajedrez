package modelo.Tablero;

import java.awt.Color;

import controlador.AjedrezServlet;
import modelo.fichas.Fichas;

public class juegoAjedrez {
	public Tablero modelo;
	public Cuadro cuadroSeleccionado;

    public juegoAjedrez() {
        this.modelo= new Tablero("","");
    }

    public void procesarMovimiento(int i, int j, AjedrezServlet aj) {
    	Fichas f = modelo.verificaciones.hayFicha(i, j, modelo.getTurno(), modelo);
    	
        // Encontrar cuadro
        for (Cuadro cuadro : modelo.listaDeCuadros) {
            if (cuadro.getX() == i && cuadro.getY() == j) {
                cuadroSeleccionado = cuadro;
            }
        }
        // Si ya hay una ficha seleccionada, intentar moverla al cuadro presionado
        if (cuadroSeleccionado.getColor() == Color.YELLOW) {
        	System.out.println("entra a cuadrAmarillo");
            modelo.verificaciones.verificarMovimientoAmarillo(i,j, aj, modelo, "normal");
            System.out.println(modelo.historialPartida.toString());
        } else {
            modelo.verificaciones.VerificarPosiblesMovimientos(f, i, j, modelo, aj,"normal");
            System.out.println("entra a nocuadrAmarillo");
            //verificarMovimeintosFicha(f,i,j);
        }
    }
}
