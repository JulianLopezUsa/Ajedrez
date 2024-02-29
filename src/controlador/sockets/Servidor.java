package controlador.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        ServerSocket servidorSocket = null;
        Socket socket = null;

        try {
            servidorSocket = new ServerSocket(12345); // Puerto de escucha
            System.out.println("Servidor esperando conexiones...");

            // Aceptar conexiones entrantes
            while (true) {
                socket = servidorSocket.accept();
                System.out.println("Nuevo cliente conectado: " + socket);

                // Crear un nuevo hilo para manejar la conexión con el cliente
                ThreadCliente cliente = new ThreadCliente(socket);

                // Iniciar el hilo del cliente
                cliente.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (servidorSocket != null) {
                    servidorSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
