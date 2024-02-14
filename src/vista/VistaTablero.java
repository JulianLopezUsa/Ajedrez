package vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class VistaTablero extends JFrame {

    public VistaTablero() {

        initComponents();
    }

    public void initComponents(){
        setTitle("Ajedrez");
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton[][] cuadro = new JButton[8][8];

        JPanel panelTablero = new JPanel(new GridLayout(8, 8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cuadro[i][j] = new JButton();
                if ((i + j) % 2 == 0) {
                    cuadro[i][j].setBackground(Color.white);
                } else {
                    cuadro[i][j].setBackground(Color.BLACK);
                }
                panelTablero.add(cuadro[i][j]);
            }
        }
        add(panelTablero);
    }

}
