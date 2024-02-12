package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuA extends JFrame {

    public MenuA() {

        // Configura la ventana
        setTitle("Ajedrez");
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dibuja la imagen de fondo en el panel
            }
        };

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centra el panel

        // Etiqueta de título
        JLabel titulo = new JLabel("Ajedrez");
        titulo.setFont(new Font("Arial", Font.BOLD, 90));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setForeground(Color.BLACK);
        panel.add(titulo);

        // Botón para iniciar el juego
        JButton botonIniciar = new JButton("Iniciar Juego");
        botonIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonIniciar.setPreferredSize(new Dimension(200, 50));
        botonIniciar.setBackground(new Color(0, 0, 0)); // Color de fondo
        botonIniciar.setForeground(Color.WHITE); // Color del texto
        botonIniciar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margen interno
        botonIniciar.setFocusPainted(false); // Quitar el borde de enfoque
        botonIniciar.setFont(new Font("Arial", Font.BOLD, 50)); // Fuente y tamaño del texto

        // Botón para salir
        JButton botonSalir = new JButton("Salir");
        botonSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonSalir.setPreferredSize(new Dimension(200, 50));
        botonSalir.setBackground(new Color(0, 0, 0));
        botonSalir.setForeground(Color.WHITE);
        botonSalir.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        botonSalir.setFocusPainted(false);
        botonSalir.setFont(new Font("Arial", Font.BOLD, 50));



        botonIniciar.addActionListener(e -> {

            new Tablero();

        });


        botonSalir.addActionListener(e -> {

            System.exit(0);

        });

        panel.add(Box.createVerticalStrut(300));
        panel.add(botonIniciar);
        panel.add(Box.createVerticalStrut(10));
        panel.add(botonSalir);

        add(panel);

        setVisible(true);
    }

}