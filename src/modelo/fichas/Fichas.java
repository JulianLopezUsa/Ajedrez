
package modelo.fichas;

import java.util.ArrayList;

import modelo.Tablero.Tablero;

/**
 *
 * @author Laura
 */
public abstract class Fichas {
    private int posX;
    private int posY;
    private String color; 
    public ArrayList<String> listaDeMovimientos = new ArrayList<>();
    public boolean movio = false;
    
    public Fichas(int posX, int posY, String color) {
        this.posX = posX;
        this.posY = posY;
        this.color = color;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getColor() {
        return color;
    }

    public void setLista(ArrayList<String> listaDeMovimientos) {
        this.listaDeMovimientos = listaDeMovimientos;
    }

    public ArrayList<String> getLista() {
        return listaDeMovimientos;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Fichas{" + "posX=" + posX + ", posY=" + posY + ", color=" + color + '}';
    }
    
    public void movimientoFicha(String posicionActual, Tablero tablero, int turno){
        
    }

    public void verificarCoronacion(Fichas fichaSeleccionada, int turno, int i){

    }

    public ArrayList<String> getListaDeMovimientos() {
        return listaDeMovimientos;
    }

    public void setListaDeMovimientos(ArrayList<String> listaDeMovimientos) {
        this.listaDeMovimientos = listaDeMovimientos;
    }

    public boolean isMovio() {
        return movio;
    }

    public void setMovio(boolean movio) {
        this.movio = movio;
    };


}