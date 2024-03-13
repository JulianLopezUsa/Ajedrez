package controlador;

import java.util.ArrayList;

public interface Eventos {
    public void esperarTurno();

    public void eliminarDeVista(int x, int y);

    public void quitarJaqueVista();

    public String coronacionFichaVista(int turno, int x, int y);

    public void cambiarbanderaB();

    public void cambiarbanderaN();

    public void resetearColoresVista();

    public void ponerJaqueVista();

    public void mostrarHistorialVista();

    public void darAccionBotones();

    public void actualizarVista();

    public void resaltarMovimientos(ArrayList<String> r);
}
