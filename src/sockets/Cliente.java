package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;

public class Cliente extends Conexion {

    public Cliente() throws IOException {
        super("cliente");
    } // Se usa el constructor para cliente de Conexion

    public String leerDatosServidor() {
        String dato = null;
        try {
            // Flujo de datos del servidor hacia el cliente
            entrada = new DataInputStream(cs.getInputStream());
            dato = entrada.readUTF();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return dato;
    }



    public void enviarDatosServidor(String dato) {
        try {

            salida = new DataOutputStream(cs.getOutputStream());
            salida.writeUTF(dato);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Limpia el buffer de la salida del cliente.
     */
    public void limpiarSalida() {
        try {
            salida.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Cierra el cliente.
     */
    public void cerrarCliente() {
        try {
            cs.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        Cliente cliente = new Cliente();
        StringTokenizer separador = new StringTokenizer(cliente.leerDatosServidor());
        System.out.println("x = " + separador.nextToken() + "  y = " + separador.nextToken());
        cliente.cerrarCliente();
        /*
         * Cliente cliente = new Cliente();
         * String datosServidor = cliente.leerDatosServidor();
         * if (datosServidor != null) {
         * StringTokenizer separador = new StringTokenizer(datosServidor);
         * if (separador.hasMoreTokens()) {
         * System.out.println("x = " + separador.nextToken());
         * }
         * if (separador.hasMoreTokens()) {
         * System.out.println("y = " + separador.nextToken());
         * }
         * } else {
         * System.out.println("No se recibieron datos del servidor.");
         * }
         * cliente.cerrarCliente();
         */
    }

}
