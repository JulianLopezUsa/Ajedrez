package vista;

import javax.swing.*;
import java.awt.*;

public class Rendir extends JFrame {

  private JPanel contentPane;
  private int jugador;
  
  public Rendir(String mensaje, String rutaImagen, int jugador) {
    this.jugador = jugador;
    setTitle("Panel de Rendición");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 400, 300);
    
    contentPane = new JPanel();
    contentPane.setLayout(new BorderLayout(0, 0));
    setContentPane(contentPane);

    if(jugador == 1){
      rutaImagen = "src/img/chess-checkmate.gif"; 
    } else {
      rutaImagen = "src/img/paul-giamatti-chuck-rhoades.gif";
    }
    
    JLabel lblMensaje = new JLabel(mensaje);
    lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
    contentPane.add(lblMensaje, BorderLayout.NORTH);

    ImageIcon icono = new ImageIcon(rutaImagen); 
    Image imagen = icono.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
    JLabel lblImagen = new JLabel(new ImageIcon(imagen));
    lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
    contentPane.add(lblImagen, BorderLayout.CENTER);
  }

}