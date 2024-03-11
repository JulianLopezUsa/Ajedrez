package modelo.Tablero;

import java.awt.Color;

public class Cuadro {

  //El color del cuadro.
  public Color color;
  //La posición del cuadro en el tablero.
  public int x;
  public int y;

  public Cuadro(Color color, int x, int y) {
    this.color = color;
    this.x = x;
    this.y = y;
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

  public int getY() {
    return y;
  }
}
