package vistaConexion;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import modelo.fichas.Fichas;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.jugadores.Jugadores;
import controlador.AccionRendir;
import controlador.sockets.Cliente;

public class TableroCliente extends JFrame {

    public JButton[][] cuadro;
    private Cliente cliente;
    private String nombre;
    private String nombreJ2;
    public JButton fin1, fin2;
    public int jaqueX_negras, jaqueY_negras;
    public int jaqueX_blancas, jaqueY_blancas;
    public String nombreFichaCoronada;
    public Boolean banderaJaque_negras = false, banderaJaque_blancaa = false;
    public Object[] opciones = { new ImageIcon("src/img/torre_blanco.png"), new ImageIcon("src/img/alfil_blanco.png"),
            new ImageIcon("src/img/reina_blanco.png"), new ImageIcon("src/img/caballo_blanco.png") };
    private JTextArea texto = new JTextArea();
    private Jugadores jugadores;

    public TableroCliente(String nombre, Jugadores jugadores) {
        this.jugadores = jugadores;
        try {
            cliente = new Cliente();
        } catch (IOException ex) {
            Logger.getLogger(TableroCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        cliente.enviarDatosServidor(nombre);

        this.nombre = nombre;
        this.nombreJ2 = nombreJ2;
        initComponents();
    }

    public void initComponents() {

        setTitle("TServidor");
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
        panelDerecho.setBackground(Color.WHITE);
        panelDerecho.setForeground(Color.BLACK);
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
        panelDerecho.setMaximumSize(new Dimension(200, Integer.MAX_VALUE)); // Establecer el ancho máximo deseado

        JPanel panelJugador1 = new JPanel();
        panelJugador1.setBackground(Color.WHITE);
        panelJugador1.setForeground(Color.BLACK);
        panelJugador1.setLayout(new BoxLayout(panelJugador1, BoxLayout.X_AXIS));

        JLabel label = new JLabel("Información");
        label.setForeground(Color.BLACK);
        // Crear un objeto Font con el tamaño deseado (por ejemplo, 20)
        Font font = new Font(label.getFont().getName(), Font.PLAIN, 20);
        label.setFont(font);

        JLabel label1 = new JLabel(" - Jugador 1:");
        label1.setForeground(Color.BLACK);
        JLabel nombre1 = new JLabel(nombre);

        panelJugador1.add(label1);
        panelJugador1.add(Box.createRigidArea(new Dimension(10, 0)));
        panelJugador1.add(nombre1);
        panelJugador1.add(Box.createHorizontalGlue());

        JPanel panelJugador2 = new JPanel();
        panelJugador2.setBackground(Color.WHITE);
        panelJugador2.setForeground(Color.BLACK);
        panelJugador2.setLayout(new BoxLayout(panelJugador2, BoxLayout.X_AXIS));

        JLabel label2 = new JLabel(" - Jugador 2:");
        label2.setForeground(Color.BLACK);
        JLabel nombre2 = new JLabel(nombreJ2);

        panelJugador2.add(label2);
        panelJugador2.add(Box.createRigidArea(new Dimension(10, 0)));
        panelJugador2.add(nombre2);
        panelJugador2.add(Box.createHorizontalGlue());
        panelJugador2.add(fin2);

        panelDerecho.add(label);
        panelDerecho.add(panelJugador1);
        panelDerecho.add(panelJugador2);

        JPanel contenido = new JPanel(new GridLayout(1, 2));
        contenido.add(panelTablero);
        contenido.add(panelDerecho);

        panelDerecho.add(Box.createRigidArea(new Dimension(50, 70)));

        // Deshabilitar edición
        texto.setEditable(false);

        // Agregar al panel
        panelDerecho.add(texto);
        // Agregar al panel dentro de un JScrollPane
        JScrollPane scrollPane = new JScrollPane(texto);
        panelDerecho.add(scrollPane);

        texto.setBackground(Color.BLACK);
        texto.setForeground(Color.WHITE);

        AccionRendir accionRendir = new AccionRendir(this, 2, fin1, fin2);
        add(contenido);
    }

    public void mostrarHistorialPartida(ArrayList<String> historialPartida) {
        texto.setText("---------------------------------------------------------\n"); // Limpiar el contenido actual del
                                                                                      // JTextArea
        int numeroMovimiento = 1;

        // Recorremos el historial de movimientos
        for (int i = 0; i < historialPartida.size(); i += 2) {
            String numeroFormateado = numeroMovimiento + ". ";
            String mov1 = historialPartida.get(i);
            String mov2 = (i + 1 < historialPartida.size()) ? historialPartida.get(i + 1) : ""; // Evitar índice fuera
                                                                                                // de rango
            String cadenaMovimientos = numeroFormateado + mov1 + "       " + mov2;
            String textoSinSaltos = cadenaMovimientos.replaceAll("\\n", "");
            texto.append("  " + textoSinSaltos + "\n");
            numeroMovimiento++;
            texto.append("---------------------------------------------------------\n");
        }

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

    public ImageIcon escalarImagen(String ruta) {
        ImageIcon icono = new ImageIcon(ruta);
        Image imagen = icono.getImage();
        Image imagenEscalada = imagen.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenEscalada);
    }

    public void resaltarMovimientos(ArrayList<String> arreglo) {
        resetearColores();

        // Resalta los botones correspondientes
        for (String movimiento : arreglo) {
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

        resaltarJaque();
    }

    public void quitarJaque() {
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

    public void resaltarJaque() {
        if (banderaJaque_blancaa) {
            cuadro[jaqueX_blancas][jaqueY_blancas].setBackground(Color.RED);
        } else if (banderaJaque_negras) {
            cuadro[jaqueX_negras][jaqueY_negras].setBackground(Color.RED);
        }
    }

    public void ponerJaque(boolean jaqueBlanco, boolean jaqueNegro, String color, int x, int y) {
        if (jaqueNegro) {
            jaqueX_negras = y;
            jaqueY_negras = x;
            banderaJaque_negras = true;
        } else if (jaqueBlanco) {
            jaqueX_blancas = y;
            jaqueY_blancas = x;
            banderaJaque_blancaa = true;
        }
        cuadro[y][x].setBackground(Color.RED);
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

    public String coronacionPieza(int turno, int x, int y) {
        if (turno == 1) {
            // Crear un arreglo de objetos para mostrar las imágenes
            opciones[0] = new ImageIcon("src/img/torre_negro.png");
            opciones[1] = new ImageIcon("src/img/alfil_negro.png");
            opciones[2] = new ImageIcon("src/img/dama_negro.png");
            opciones[3] = new ImageIcon("src/img/caballo_negro.png");
        } else {
            opciones[0] = new ImageIcon("src/img/torre_blanco.png");
            opciones[1] = new ImageIcon("src/img/alfil_blanco.png");
            opciones[2] = new ImageIcon("src/img/dama_blanco.png");
            opciones[3] = new ImageIcon("src/img/caballo_blanco.png");
        }
        // Mostrar el JOptionPane con las imágenes
        int seleccion = JOptionPane.showOptionDialog(
                null,
                "¡Coronaste un peón!: Elige una ficha para cambiar",
                "Selección de imagen",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        // Verificar la selección del usuario
        if (seleccion >= 0 && seleccion < opciones.length) {
            if (seleccion == 0) {
                JOptionPane.showMessageDialog(null, "Has seleccionado a la torre");
                cuadro[x][y].setIcon(escalarImagen(opciones[0].toString()));
                nombreFichaCoronada = "torre";
            } else if (seleccion == 1) {
                JOptionPane.showMessageDialog(null, "Has seleccionado al afil");
                cuadro[x][y].setIcon(escalarImagen(opciones[1].toString()));
                nombreFichaCoronada = "alfil";
            } else if (seleccion == 2) {
                JOptionPane.showMessageDialog(null, "Has seleccionado al dama");
                cuadro[x][y].setIcon(escalarImagen(opciones[2].toString()));
                nombreFichaCoronada = "dama";
            } else {
                JOptionPane.showMessageDialog(null, "Has seleccionado al caballo");
                cuadro[x][y].setIcon(escalarImagen(opciones[3].toString()));
                nombreFichaCoronada = "caballo";
            }
        }
        return nombreFichaCoronada;
    }

    public void closeGame() {
        this.dispose();
    }
}
