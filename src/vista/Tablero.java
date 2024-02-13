package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class Tablero extends JFrame {

    public Tablero() {

        setTitle("Ajedrez");
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);

        JPanel[][] cuadro = new JPanel[8][8];

        JPanel panelTablero = new JPanel(new GridLayout(8, 8));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cuadro[i][j] = new JPanel();
                if ((i + j) % 2 == 0) {
                    cuadro[i][j].setBackground(Color.white);
                } else {
                    cuadro[i][j].setBackground(Color.BLACK);
                }
                panelTablero.add(cuadro[i][j]);

            }

        }

        add(panelTablero);
        setVisible(true);
    }

}
