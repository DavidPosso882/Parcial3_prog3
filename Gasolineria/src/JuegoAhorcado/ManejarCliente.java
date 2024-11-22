package JuegoAhorcado;

// Importaciones necesarias para manejar entrada/salida y redes
import java.io.*;
import java.net.*;

// Clase que implementa Runnable, permitiendo que cada cliente se gestione en un hilo independiente
public class ManejarCliente implements Runnable {

    // Atributos de la clase
    private Socket clientSocket; // Socket que representa la conexión con el cliente
    private BufferedReader in; // Buffer de lectura para recibir datos desde el cliente
    private ServidorAhorcado servidor; // Referencia al servidor principal para procesar datos compartidos

    // Constructor que inicializa el socket del cliente y el servidor
    public ManejarCliente(Socket socket, ServidorAhorcado servidor) {
        this.clientSocket = socket; // Asigna el socket de cliente
        this.servidor = servidor; // Asigna la referencia al servidor principal

        // Inicializa el BufferedReader para leer mensajes del cliente
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) { // Captura y muestra posibles errores de entrada/salida
            e.printStackTrace();
        }
    }

    // Metodo run que se ejecuta cuando el hilo del cliente se inicia
    @Override
    public void run() {
        try {
            String inputLine; // Variable para almacenar cada línea de entrada del cliente

            // Bucle que se ejecuta mientras el cliente envíe mensajes
            while ((inputLine = in.readLine()) != null) {

                // Verifica que el mensaje recibido es una sola letra
                if (inputLine.length() == 1) {
                    // Llama al metodo del servidor para procesar la letra recibida
                    servidor.procesarLetra(inputLine.charAt(0));
                }
            }
        } catch (IOException e) { // Manejo de posibles excepciones de entrada/salida
            e.printStackTrace();
        }
    }
}

