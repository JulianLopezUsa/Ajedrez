package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.Conexion;

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
        if (e.getSource().equals(this.conexion.crear)) {
            crear();
        } else if (e.getSource().equals(this.conexion.unir)) {
            unir();
        } else if (e.getSource().equals(this.conexion.salir)) {
            salir();
        }
    }

    private void crear() {
        System.out.println("crear");

    }

    private void unir() {
        System.out.println("unir");

    }

    private void salir() {
        System.out.println("salir");
        this.conexion.setVisible(false);
    }

}
