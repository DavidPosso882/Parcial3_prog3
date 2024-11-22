package Sockets;

import java.io.*; // Importa las clases de entrada y salida de Java.
import java.net.*; // Importa las clases necesarias para trabajar con redes.

public class ServidorBasico {
    public static void main(String[] args) {
        // Usa un bloque try-with-resources para crear el ServerSocket.
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Servidor iniciado y esperando cliente...");

            // Espera la conexión de un cliente.
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado.");

            // Crea un BufferedReader para leer los datos entrantes del cliente.
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Crea un PrintWriter para enviar datos al cliente.
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String message;
            // Lee los mensajes enviados por el cliente hasta que se reciba un valor null.
            while ((message = in.readLine()) != null) {
                System.out.println("Cliente: " + message); // Muestra el mensaje en la consola del servidor.
                out.println("Recibido: " + message); // Responde al cliente con el mensaje recibido.
            }
        } catch (IOException e) {
            e.printStackTrace(); // Muestra el error en caso de una excepción de entrada/salida.
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

