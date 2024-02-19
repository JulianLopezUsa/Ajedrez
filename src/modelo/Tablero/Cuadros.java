
package modelo.Tablero;

import java.awt.Color;
import modelo.fichas.Fichas;

/**
 *
 * @author Laura
 */
public class Cuadros {
    //Es la que esta en el cuadro.
    private Fichas ficha;
    //El color del cuadro.
    private Color color;
    //La posición del cuadro en el tablero.
    private int x;
    private int y;
    
    public Cuadros(Color color, int x, int y, Fichas ficha) {
        this.color = color;
        this.ficha = ficha;
        this.x = x;
        this.y = y;   
    }

    public Fichas getFicha() {
        return ficha;
    }

    public void setFicha(Fichas ficha) {
        this.ficha = ficha;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public boolean estaVacio() {
        return ficha == null;
    }
    
}
