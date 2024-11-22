package JuegoAhorcado;

// Importaciones necesarias para crear una interfaz gráfica y manejar entrada/salida
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

// Clase que implementa Runnable, lo que permite ejecutar la recepción de mensajes en un hilo separado
public class RecibirMensajes implements Runnable {

    // Atributos de la clase para la comunicación y la interfaz
    private Socket socket; // Socket del cliente para recibir mensajes del servidor
    private BufferedReader in; // BufferedReader para leer mensajes entrantes del servidor
    private JTextField txtPalabra; // Campo de texto en la interfaz gráfica para mostrar la palabra recibida
    private JFrame frame; // Marco principal de la interfaz gráfica donde se muestra el mensaje
    private String mensajeServidor; // Variable para almacenar mensajes recibidos del servidor

    // Constructor que inicializa los atributos con los parámetros recibidos
    public RecibirMensajes(BufferedReader in, JTextField txtPalabra, JFrame frame, Socket socket) {
        this.in = in; // Asigna el BufferedReader para recibir mensajes
        this.txtPalabra = txtPalabra; // Asigna el campo de texto de la interfaz
        this.frame = frame; // Asigna el marco de la interfaz
        this.socket = socket; // Asigna el socket del cliente
    }

    // Metodo run que se ejecuta al iniciar el hilo, encargado de recibir y procesar mensajes del servidor
    @Override
    public void run() {
        try {
            // Bucle que se mantiene activo mientras haya mensajes del servidor
            while ((mensajeServidor = in.readLine()) != null) {
                txtPalabra.setText(mensajeServidor); // Muestra el mensaje en el campo de texto

                // Si el mensaje recibido indica que la palabra fue adivinada
                if (mensajeServidor.startsWith("Palabra adivinada")) {
                    // Muestra un cuadro de diálogo con el mensaje de finalización
                    JOptionPane.showMessageDialog(frame, mensajeServidor);

                    // Cierra el socket y finaliza la aplicación
                    socket.close();
                    System.exit(0);
                }
            }
        } catch (IOException e) { // Manejo de posibles errores de entrada/salida
            e.printStackTrace();
        }
    }
}

