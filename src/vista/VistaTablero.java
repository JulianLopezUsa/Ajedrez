package vista;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import modelo.fichas.Fichas;
import controlador.AccionRendir;

public class VistaTablero extends JFrame {

    public JButton[][] cuadro;
    private String nombreJ1;
    private String nombreJ2;
    public JButton fin1, fin2;
    private JTextArea texto = new JTextArea();

    public VistaTablero(String nombreJ1, String nombreJ2) {
        this.nombreJ1 = nombreJ1;
        this.nombreJ2 = nombreJ2;
        initComponents();
    }
    
    public void initComponents() {
        setTitle("MyLocalChess");
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cuadro = new JButton[8][8];

        JPanel panelTablero = new JPanel(new GridLayout(8, 8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cuadro[i][j] = new JButton();
                if ((i + j) % 2 == 0) {
                    cuadro[i][j].setBackground(new Color(222, 184, 135));
                } else {
                    cuadro[i][j].setBackground(new Color(139, 69, 19));
                }
                panelTablero.add(cuadro[i][j]);
            }
        }
        add(panelTablero);

        JPanel panelDerecho = new JPanel();

        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
        panelDerecho.setPreferredSize(new Dimension(200, 700));

        JPanel panelJugador1 = new JPanel();
        panelJugador1.setLayout(new BoxLayout(panelJugador1, BoxLayout.X_AXIS));

        JLabel label = new JLabel("INFORMACIÓN");
        JLabel label1 = new JLabel("Jugador 1:");
        JLabel nombre1 = new JLabel(nombreJ1);
        fin1 = new JButton("Rendirse");

        panelJugador1.add(label1);
        panelJugador1.add(Box.createRigidArea(new Dimension(10, 0)));
        panelJugador1.add(nombre1);
        panelJugador1.add(Box.createHorizontalGlue());
        panelJugador1.add(fin1);

        JPanel panelJugador2 = new JPanel();
        panelJugador2.setLayout(new BoxLayout(panelJugador2, BoxLayout.X_AXIS));

        JLabel label2 = new JLabel("Jugador 2:");
        JLabel nombre2 = new JLabel(nombreJ2);
        fin2 = new JButton("Rendirse");

        panelJugador2.add(label2);
        panelJugador2.add(Box.createRigidArea(new Dimension(10, 0)));
        panelJugador2.add(nombre2);
        panelJugador2.add(Box.createHorizontalGlue());
        panelJugador2.add(fin2);

        panelDerecho.add(label);
        panelDerecho.add(panelJugador1);
        panelDerecho.add(panelJugador2);

        JPanel contenido = new JPanel(new GridLayout(1, 3));
        contenido.add(panelTablero);
        contenido.add(panelDerecho);

        panelDerecho.add(Box.createRigidArea(new Dimension(100, 70)));


        // Deshabilitar edición
        texto.setEditable(false);

        // Agregar al panel
        panelDerecho.add(texto);

        texto.setBackground(Color.BLACK);
        texto.setForeground(Color.WHITE);

        texto.append("Jugador 1: " + nombreJ1);
        texto.append("\nJugador 2: " + nombreJ2);

        add(contenido);

        AccionRendir accionRendir = new AccionRendir(this);
        fin1.addActionListener(accionRendir);
        fin2.addActionListener(accionRendir);
    }

    public JButton getBoton(int x, int y) {
        return cuadro[x][y];
    }

    public void agregarFichas() {
        for (int i = 0; i < 8; i++) {
            cuadro[1][i].setIcon(escalarImagen("src/img/peon_negro.png"));
            cuadro[6][i].setIcon(escalarImagen("src/img/peon_blanco.png"));
        }

        cuadro[0][0].setIcon(escalarImagen("src/img/torre_negro.png"));
        cuadro[0][7].setIcon(escalarImagen("src/img/torre_negro.png"));
        cuadro[0][1].setIcon(escalarImagen("src/img/caballo_negro.png"));
        cuadro[0][6].setIcon(escalarImagen("src/img/caballo_negro.png"));
        cuadro[0][2].setIcon(escalarImagen("src/img/alfil_negro.png"));
        cuadro[0][5].setIcon(escalarImagen("src/img/alfil_negro.png"));
        cuadro[0][3].setIcon(escalarImagen("src/img/dama_negro.png"));
        cuadro[0][4].setIcon(escalarImagen("src/img/rey_negro.png"));

        cuadro[7][0].setIcon(escalarImagen("src/img/torre_blanco.png"));
        cuadro[7][7].setIcon(escalarImagen("src/img/torre_blanco.png"));
        cuadro[7][1].setIcon(escalarImagen("src/img/caballo_blanco.png"));
        cuadro[7][6].setIcon(escalarImagen("src/img/caballo_blanco.png"));
        cuadro[7][2].setIcon(escalarImagen("src/img/alfil_blanco.png"));
        cuadro[7][5].setIcon(escalarImagen("src/img/alfil_blanco.png"));
        cuadro[7][3].setIcon(escalarImagen("src/img/dama_blanco.png"));
        cuadro[7][4].setIcon(escalarImagen("src/img/rey_blanco.png"));
    }
    public void imprimirJugada(String nombreFicha, int posX, int posY) {
        // Agrega la información de la jugada al área de texto
        texto.append("Jugada: " + nombreFicha + " - Posición: (" + posX + ", " + posY + ")\n");
    }
    

    public ImageIcon escalarImagen(String ruta) {
        ImageIcon icono = new ImageIcon(ruta);
        Image imagen = icono.getImage();
        Image imagenEscalada = imagen.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenEscalada);
    }

    public void resaltarMovimientos(Fichas f) {
        resetearColores();

        ArrayList<String> movimientos = f.getLista();
        // Resalta los botones correspondientes
        for (String movimiento : movimientos) {
            String[] pos = movimiento.split(" ");
            int newX = pos[0].charAt(0) - 'a';
            int newY = Integer.parseInt(pos[1]);
            cuadro[newX][newY].setBackground(Color.YELLOW);
        }
    }

    public void resetearColores() {
        // Resetear el color de todos los botones del tablero
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    cuadro[i][j].setBackground(new Color(222, 184, 135));
                } else {
                    cuadro[i][j].setBackground(new Color(139, 69, 19));
                }
            }
        }
    }

    public void actualizar(int posX, int posY, Fichas ficha) {
        getBoton(posY, posX).setIcon(obtenerIconoFicha(ficha)); // Actualizar icono de la ficha en la vista
    }

    public void eliminarDeVista(int posX, int posY) {
        getBoton(posY, posX).setIcon(null);
    }

    private ImageIcon obtenerIconoFicha(Fichas ficha) {
        String color = ficha.getColor();
        String tipo = ficha.getClass().getSimpleName().toLowerCase(); // Obtener el nombre del tipo de ficha en
                                                                      // minúsculas
        String nombreIcono = "src/img/" + tipo + "_" + color + ".png"; // Nombre del archivo de la imagen de la ficha
        return escalarImagen(nombreIcono);
    }
}
