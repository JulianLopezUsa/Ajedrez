package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.VistaTablero;
import vista.Rendir;

public class AccionRendir implements ActionListener {

  private VistaTablero vistaTablero;
  private int jugador;

  public AccionRendir(VistaTablero vistaTablero, int jugador) {
    this.vistaTablero = vistaTablero;
    this.jugador = jugador;
  }
  
  Rendir rendirFrame;
  @Override
  public void actionPerformed(ActionEvent e) {
    if (jugador == 1) {
      System.out.println("Jugador 1 rendido!");
      Rendir rendirFrame = new Rendir("¡Jugador 1 rendido!", "src/img/chess-checkmate.gif", jugador);
    } else if (jugador == 2) {
      System.out.println("Jugador 2 rendido!");  
      Rendir rendirFrame = new Rendir("¡Jugador 2 rendido!", "src/img/dama_negro.png", jugador);
    }
    
    rendirFrame.setVisible(true);
  }
}