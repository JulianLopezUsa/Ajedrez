/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    
    public void movimientoFicha(String posicionActual, Tablero tablero){
        
    };
}
