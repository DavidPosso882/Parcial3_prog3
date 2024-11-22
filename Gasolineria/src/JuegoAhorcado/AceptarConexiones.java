package JuegoAhorcado;

// Importaciones necesarias para el manejo de la red y listas
import java.io.*;
import java.net.*;
import java.util.List;

// Clase que acepta conexiones de clientes y maneja el flujo de mensajes
public class AceptarConexiones implements Runnable {

    private ServerSocket serverSocket; // Socket del servidor para aceptar conexiones
    private List<PrintWriter> clientesConectados; // Lista para guardar la salida de cada cliente conectado
    private ServidorAhorcado servidor; // Referencia al objeto que representa el servidor del juego
    private volatile boolean running = true; // Bandera para controlar si el hilo debe seguir ejecutándose

    // Constructor que inicializa el socket del servidor, la lista de clientes y el servidor del juego
    public AceptarConexiones(ServerSocket serverSocket, List<PrintWriter> clientesConectados, ServidorAhorcado servidor) {
        this.serverSocket = serverSocket;
        this.clientesConectados = clientesConectados;
        this.servidor = servidor;
    }

    // Metodo que ejecuta el hilo para aceptar conexiones de clientes
    @Override
    public void run() {
        try {
            // Bucle principal que permanece activo mientras 'running' sea true
            while (running) {
                try {
                    // Espera a que un cliente se conecte y acepta la conexión
                    Socket clientSocket = serverSocket.accept();

                    // Crea un objeto PrintWriter para enviar mensajes al cliente recién conectado
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                    // Añade el PrintWriter del cliente a la lista de clientes conectados
                    clientesConectados.add(out);

                    // Inicia un hilo para manejar el cliente conectado, pasándole el socket del cliente y el servidor
                    new Thread(new ManejarCliente(clientSocket, servidor)).start();
                } catch (SocketException e) { // Excepción cuando el socket se cierra
                    // Verifica si el hilo fue detenido intencionalmente; de ser así, sale del bucle
                    if (!running) break;
                    else e.printStackTrace(); // Si fue otro error, imprime la traza de error
                }
            }
        } catch (IOException e) { // Excepción general de E/S
            e.printStackTrace();
        }
    }

    // Metodo para detener el hilo de aceptación de conexiones
    public void stop() {
        running = false; // Cambia 'running' a false para detener el bucle en 'run()'
        try {
            serverSocket.close(); // Cierra el server socket para liberar el recurso
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

