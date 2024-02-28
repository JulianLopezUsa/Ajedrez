package modelo.fichas;

import java.util.ArrayList;

import modelo.Tablero.Tablero;


public class Caballo extends Fichas {

    public ArrayList<String> listaDeMovimientos = new ArrayList<>();

    public Caballo(int posX, int posY, String color) {
        super(posX, posY, color);
    }

    @Override
    public void movimientoFicha(String posicionActual, Tablero tablero, int turno) {
        listaDeMovimientos.clear();

        int tt;
        if (turno!=3){
          tt = turno;
          //letraff=letraff-1;
        }else{
          tt = tablero.getTurno();
        }

        String[] pos = posicionActual.split(" ");
    
        char letraF = pos[0].charAt(0);
        int numeroF = Integer.parseInt(pos[1]);
        
        //Se castea a número la letra para trabajar con ASCII
        int letraff = (int) letraF;

        // Array con los posibles desplazamientos en forma de "L"
        int[][] desplazamientos = {
                {letraff - 2, numeroF - 1}, {letraff - 2, numeroF + 1},
                {letraff - 1, numeroF - 2}, {letraff - 1, numeroF + 2},
                {letraff + 1, numeroF - 2}, {letraff + 1, numeroF + 2},
                {letraff + 2, numeroF - 1}, {letraff + 2, numeroF + 1}
        };
    
        // Validar cada posible movimiento
        for (int[] movimiento : desplazamientos) {
            
            int nuevaLetra = movimiento[0];
            int nuevoNumero = movimiento[1];

            // Verificar si el movimiento está dentro del tablero
            if (nuevaLetra >= 97 && nuevaLetra <= 104 && nuevoNumero >= 0 && nuevoNumero <= 7) {
                // Verificar si hay una ficha del mismo equipo en la casilla final del movimiento
                Fichas ficha = tablero.hayFicha( nuevaLetra - 'a', nuevoNumero,tt);
                
                if (ficha == null || !ficha.getColor().equals(this.getColor())) {
                    // Si no hay una ficha del mismo equipo, agregar el movimiento a la lista de movimientos válidos
                    listaDeMovimientos.add((char) nuevaLetra + " " + nuevoNumero);
                }
            }
        }
        setLista(listaDeMovimientos);

    }

    @Override
    public ArrayList<String> getLista() {
        return super.getLista();
    }


    @Override
    public void setLista(ArrayList<String> listaDeMovimientos) {
        super.setLista(listaDeMovimientos);
    }

}