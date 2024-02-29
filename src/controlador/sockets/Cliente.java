package controlador.sockets;

import java.io.IOException;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) {
        Socket socket = null;

        try {
            // Conectar al servidor
            socket = new Socket("localhost", 12345); // IP y puerto del servidor

            // Lógica del cliente aquí...

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

