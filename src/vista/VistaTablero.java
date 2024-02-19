package vista;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

import modelo.fichas.Fichas;

public class VistaTablero extends JFrame {

    public JButton[][] cuadro;

    public VistaTablero() {
        initComponents();
    }

    public void initComponents() {
        setTitle("Ajedrez");
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
                    cuadro[i][j].setBackground(new Color(210, 180, 140));
                }
                panelTablero.add(cuadro[i][j]);
            }
        }
        add(panelTablero);
    }

    public JButton getBoton(int x, int y) {
        return cuadro[x][y];
    }

    public void agregarFichas() {
        for (int i = 0; i < 8; i++) {
            cuadro[1][i].setIcon(escalarImagen("src/img/peon_blanco.png"));
            cuadro[6][i].setIcon(escalarImagen("src/img/peon_negro.png"));
        }

        cuadro[0][0].setIcon(escalarImagen("src/img/torre_blanco.png"));
        cuadro[0][7].setIcon(escalarImagen("src/img/torre_blanco.png"));
        cuadro[0][1].setIcon(escalarImagen("src/img/caballo_blanco.png"));
        cuadro[0][6].setIcon(escalarImagen("src/img/caballo_blanco.png"));
        cuadro[0][2].setIcon(escalarImagen("src/img/alfil_blanco.png"));
        cuadro[0][5].setIcon(escalarImagen("src/img/alfil_blanco.png"));
        cuadro[0][3].setIcon(escalarImagen("src/img/dama_blanco.png"));
        cuadro[0][4].setIcon(escalarImagen("src/img/rey_blanco.png"));

        cuadro[7][0].setIcon(escalarImagen("src/img/torre_negro.png"));
        cuadro[7][7].setIcon(escalarImagen("src/img/torre_negro.png"));
        cuadro[7][1].setIcon(escalarImagen("src/img/caballo_negro.png"));
        cuadro[7][6].setIcon(escalarImagen("src/img/caballo_negro.png"));
        cuadro[7][2].setIcon(escalarImagen("src/img/alfil_negro.png"));
        cuadro[7][5].setIcon(escalarImagen("src/img/alfil_negro.png"));
        cuadro[7][3].setIcon(escalarImagen("src/img/dama_negro.png"));
        cuadro[7][4].setIcon(escalarImagen("src/img/rey_negro.png"));
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
                    cuadro[i][j].setBackground(new Color(210, 180, 140));
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
