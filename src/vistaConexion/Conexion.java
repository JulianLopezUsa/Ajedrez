package vistaConexion;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Conexion extends JFrame {
    public JButton crear,unir,salir;

    public Conexion() {
        setTitle("Partida Online");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creamos los botones
        crear = new JButton("Crear partida");
        crear.setAlignmentX(Component.CENTER_ALIGNMENT);
        unir = new JButton("Conectarse a partida");
        unir.setAlignmentX(Component.CENTER_ALIGNMENT);
        salir = new JButton("Salir");
        salir.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Configuramos el diseño del panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Agregamos los botones al panel
        panel.add(Box.createRigidArea(new Dimension(0, 25))); 
        panel.add(crear);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); 
        panel.add(unir);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); 
        panel.add(salir);

        // Agregamos el panel al frame
        add(panel);
        setVisible(true);
    }

    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(new Runnable() {
    //         @Override
    //         public void run() {
    //             new Conexion();
    //         }
    //     });
    // }
}







