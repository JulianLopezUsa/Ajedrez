package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import modelo.Tablero.Tablero;
import modelo.fichas.Fichas;
import vista.VistaTablero;

public class EventosTablero implements ActionListener {

    public VistaTablero tablero;
    public Tablero tablero2;

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
                    System.out.println("Se presionó:"+ i+""+j);
                    Fichas f = tablero2.hayFicha(i, j, tablero2.getTurno());
                    System.out.println(f);
                    if (f != null) {
                        // Obtener los posibles movimientos de la ficha en esa posición
                        f.movimientoFicha((char)(i + 97) + " " + j, tablero2);
                        // Cambiar el color de los botones correspondientes a los movimientos válidos
                        this.tablero.resaltarMovimientos(f);
                    }
                    return; // Salir del bucle cuando se encuentre el botón presionado
                }
            }
        }

    }


}
