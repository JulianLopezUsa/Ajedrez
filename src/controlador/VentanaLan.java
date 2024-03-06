package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import vistaConexion.Conexion;

public class VentanaLan implements ActionListener {

    private final Conexion conexion;

    public VentanaLan(Conexion conexion) {
        
        this.conexion = conexion;
        this.conexion.crear.addActionListener(this);
        this.conexion.unir.addActionListener(this);
        this.conexion.salir.addActionListener(this);
        this.conexion.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getSource());
        if (e.getSource().equals(this.conexion.crear)) {
            crear();
        } else if (e.getSource().equals(this.conexion.unir)) {
            unir();
        } else if (e.getSource().equals(this.conexion.salir)) {
            salir();
        }
    }

    private void crear() {
        System.out.println("salir");
        String nombre = JOptionPane.showInputDialog(this.conexion, "Ingrese su nombre", "Nombre del jugador", JOptionPane.QUESTION_MESSAGE);
    }
    
    private void unir() {
        String nombre = JOptionPane.showInputDialog(this.conexion, "Ingrese su nombre", "Nombre jugador", JOptionPane.QUESTION_MESSAGE);
        System.out.println("unir");
    }

    private void salir() {
        this.conexion.setVisible(false);
    }
}
