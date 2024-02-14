package vista; 

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class VistaTablero extends JFrame {

    public VistaTablero() {
        initComponents();
    }

    public void initComponents() {
        setTitle("Ajedrez");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelTablero = new JPanel(new GridLayout(8, 8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton boton = new JButton();
                boton.setPreferredSize(new Dimension(80, 80)); // se establece el tamaño preferido del botón
                if ((i + j) % 2 == 0) {
                    boton.setBackground(new Color(222, 184, 135));
                } else {
                    boton.setBackground(new Color(139, 0, 0));
                }
                panelTablero.add(boton);
            }
        }
        add(panelTablero);
        pack(); // Ajusta el tamaño de la ventana al contenido
        setVisible(true);
    }
}
