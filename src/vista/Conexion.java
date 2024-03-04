package vista;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Conexion extends JFrame {
    private JButton boton1, boton2, boton3;

    public Conexion() {
        setTitle("Panel de Conexión");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creamos los botones
        boton1 = new JButton("Crear Partida");
        boton2 = new JButton("Conectarse");
        boton3 = new JButton("Salir");

        // Configuramos el diseño del panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Agregamos los botones al panel
        panel.add(boton1);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); 
        panel.add(boton2);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); 
        panel.add(boton3);

        // Agregamos el panel al frame
        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Conexion();
            }
        });
    }
}







