package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.VistaTablero;
import vista.Rendir;

public class AccionRendir implements ActionListener {

  private VistaTablero vistaTablero;
  private int jugador;
  private Rendir rendirFrame;

  public AccionRendir(VistaTablero vistaTablero, int jugador) {
    this.vistaTablero = vistaTablero;
    this.jugador = jugador;
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == fin1) {
      System.out.println("Jugador 1 rendido!");  
      mostrarMensajeRendicion(1);
    } else if (e.getSource() == fin2) {
      System.out.println("Jugador 2 rendido!");  
      mostrarMensajeRendicion(1);
      
    }
    
    rendirFrame.setVisible(true);
  }
}