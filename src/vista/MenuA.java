package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuA extends JFrame {

    private Image backgroundImage;

    public JButton botonIniciar, botonSalir;

    public MenuA() {
        try {
            backgroundImage = ImageIO.read(new File("src/img/inicio2.jpg"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        initComponents();
    }

    public void initComponents(){
        
        setTitle("Ajedrez");
        setSize(1250, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centra el panel

        

        // Botón para iniciar el juego
        botonIniciar = new JButton("Iniciar Juego");
        botonIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonIniciar.setPreferredSize(new Dimension(200, 50));
        botonIniciar.setBackground(new Color(220, 220, 220)); // Color de fondo
        botonIniciar.setForeground(Color.BLACK); // Color del texto
        botonIniciar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margen interno
        botonIniciar.setFocusPainted(false); // Quitar el borde de enfoque
        botonIniciar.setFont(new Font("Arial", Font.BOLD, 50)); // Fuente y tamaño del texto
        // Botón para salir

        botonSalir = new JButton("Salir");
        botonSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonSalir.setPreferredSize(new Dimension(200, 50));
        botonSalir.setBackground(new Color(220, 220, 220));
        botonSalir.setForeground(Color.BLACK);
        botonSalir.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        botonSalir.setFocusPainted(false);
        botonSalir.setFont(new Font("Arial", Font.BOLD, 50));

        panel.add(Box.createVerticalStrut(300));
        panel.add(botonIniciar);
        panel.add(Box.createVerticalStrut(10));
        panel.add(botonSalir);

        add(panel);
    }

}