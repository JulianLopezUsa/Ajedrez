package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class Rendir extends JFrame {

    private JPanel contentPane;

    public Rendir() {
        setTitle("Panel de Rendición");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana al cerrarla
        setBounds(100, 100, 300, 200);
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JLabel lblRendicion = new JLabel("¡Jugador rendido!");
        contentPane.add(lblRendicion, BorderLayout.CENTER);
    }
}
