package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.VistaTablero;
import vista.MenuA;
import vista.Rendir;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class AccionRendir implements ActionListener {

    private VistaTablero vistaTablero;
    private int jugador;
    private Rendir rendirFrame;
    private JButton fin1;
    private JButton fin2;

    public AccionRendir(VistaTablero vistaTablero, int jugador, JButton fin1, JButton fin2) {
        this.vistaTablero = vistaTablero;
        this.jugador = jugador;
        this.fin1 = fin1;
        this.fin2 = fin2;
        this.fin1.addActionListener(this);
        this.fin2.addActionListener(this);
    }

    public void mostrarMensajeRendicion(int jugador) {
        String mensaje = ""; // Valor predeterminado
        String rutaImagen = ""; // Valor predeterminado

        if (jugador == 1) {
            mensaje = "Jugador 1";
            rutaImagen = "src/img/rey_blanco.png";

        } else if (jugador == 2) {
            mensaje = "Jugador 2";
            rutaImagen = "src/img/rey_negro.png";
        }
        Rendir rendirFrame = new Rendir(mensaje, rutaImagen, jugador);
        rendirFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fin1) {
            System.out.println("Jugador 1 rendido!");
            mostrarMensajeRendicion(1);
        }
        if (e.getSource() == fin2) {

            System.out.println("Jugador 2 rendido!");
            mostrarMensajeRendicion(2);
        }
    }

     
}
