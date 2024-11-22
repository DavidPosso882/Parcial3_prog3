package Sockets;

import java.io.*; // Importa clases de entrada y salida.
import java.net.*; // Importa clases para trabajo en redes.

class ServidorMulticliente {
    public static void main(String[] args) {
        // Usa un bloque try-with-resources para el ServerSocket.
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Servidor multicliente iniciado...");
            // Ciclo para aceptar múltiples conexiones de clientes.
            while (true) {
                Socket clientSocket = serverSocket.accept(); // Espera la conexión de un cliente.
                new Thread(new ClienteHandler(clientSocket)).start(); // Crea un nuevo hilo para cada cliente.
            }
        } catch (IOException e) {
            e.printStackTrace(); // Muestra cualquier error.
        }
    }
}

// Clase para manejar las conexiones de clientes en hilos.
class ClienteHandler implements Runnable {
    private final Socket clientSocket;

    public ClienteHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        // Maneja la comunicación con el cliente dentro del hilo.
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String message;
            // Lee los mensajes del cliente.
            while ((message = in.readLine()) != null) {
                System.out.println("Cliente dice: " + message); // Muestra el mensaje en la consola del servidor.
                out.println("Servidor responde: " + message); // Envía una respuesta al cliente.
            }
        } catch (IOException e) {
            e.printStackTrace(); // Muestra cualquier error.
        }
    }
}



import java.io.*; // Importa las clases de entrada y salida.
        import java.net.*; // Importa las clases necesarias para trabajar con redes.

public class ClienteBasico {
    public static void main(String[] args) {
        // Usa un bloque try-with-resources para manejar el socket del cliente.
        try (Socket socket = new Socket("localhost", 12345); // Conecta con el servidor en localhost y puerto 12345.
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Lee los datos del servidor.
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // Envía datos al servidor.
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) { // Lee la entrada del usuario.

            System.out.println("Conectado al servidor. Escribe un mensaje:");
            String userInput;
            // Lee la entrada del usuario y envía al servidor hasta que se reciba un valor null.
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput); // Envía el mensaje al servidor.
                System.out.println("Servidor: " + in.readLine()); // Muestra la respuesta del servidor.
            }
        } catch (IOException e) {
            e.printStackTrace(); // Muestra el error en caso de excepción de entrada/salida.
        }
    }
}



