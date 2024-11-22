package JuegoAhorcado;

// Importaciones necesarias para crear una interfaz gráfica y gestionar eventos, red, entrada/salida, y listas
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.List;

// Clase principal que extiende JFrame para crear una ventana gráfica y gestionar el servidor del juego Ahorcado
public class ServidorAhorcado extends JFrame {

    // Componentes de la interfaz gráfica
    private JButton btnSeleccionarPalabra; // Botón para seleccionar una palabra aleatoria
    private JTextField txtPalabra; // Campo de texto para mostrar la palabra oculta

    // Variables para el juego y la comunicación
    private ArrayList<String> palabras; // Lista de palabras posibles para el juego
    private String palabraSeleccionada; // Palabra actual que los clientes intentarán adivinar
    private String palabraOculta; // Representación de la palabra con letras ocultas

    private List<PrintWriter> clientesConectados; // Lista de clientes conectados al servidor para enviar mensajes
    private ServerSocket serverSocket; // Socket del servidor que acepta conexiones de clientes
    private AceptarConexiones aceptarConexiones; // Hilo para gestionar conexiones de clientes

    // Constructor que configura la interfaz y el servidor
    public ServidorAhorcado() {
        setTitle("Servidor Ahorcado"); // Título de la ventana
        setSize(300, 200); // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierre de la aplicación al cerrar la ventana
        setLayout(new FlowLayout()); // Disposición de componentes en la ventana

        // Crear y configurar el botón y campo de texto
        btnSeleccionarPalabra = new JButton("Seleccionar Palabra");
        txtPalabra = new JTextField(20);
        txtPalabra.setEditable(false); // Campo de texto no editable

        // Agregar los componentes a la ventana
        add(btnSeleccionarPalabra);
        add(txtPalabra);

        // Inicializar la lista de palabras y la lista de clientes conectados
        palabras = new ArrayList<>(Arrays.asList("casa", "java", "programacion", "ingenieria", "computadora"));
        clientesConectados = new ArrayList<>();

        // Agregar un ActionListener al botón para seleccionar una palabra cuando se haga clic
        btnSeleccionarPalabra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarPalabra(); // Llamar al metodo que selecciona y muestra una palabra oculta
            }
        });

        // Crear el socket del servidor en el puerto 12345 y comenzar a aceptar conexiones
        try {
            serverSocket = new ServerSocket(12345); // Crear socket en el puerto 12345
            // Crear y comenzar el hilo para aceptar conexiones de clientes
            aceptarConexiones = new AceptarConexiones(serverSocket, clientesConectados, this); // Pasa la referencia al servidor y a los clientes
            new Thread(aceptarConexiones).start(); // Iniciar el hilo que espera conexiones
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de error en caso de que falle el socket
        }
    }

    // Metodo que selecciona una palabra aleatoria, la oculta y la envía a todos los clientes conectados
    private void seleccionarPalabra() {
        Random rand = new Random(); // Generador de números aleatorios
        palabraSeleccionada = palabras.get(rand.nextInt(palabras.size())); // Seleccionar una palabra aleatoria
        palabraOculta = "*".repeat(palabraSeleccionada.length()); // Convertir la palabra en asteriscos
        txtPalabra.setText(palabraOculta); // Mostrar la palabra oculta en la interfaz
        enviarATodos(palabraOculta); // Enviar la palabra oculta a todos los clientes
    }

    // Metodo para enviar un mensaje a todos los clientes conectados
    public void enviarATodos(String mensaje) {
        for (PrintWriter cliente : clientesConectados) { // Recorrer la lista de clientes
            cliente.println(mensaje); // Enviar el mensaje a cada cliente
        }
    }

    // Metodo para procesar una letra enviada por los clientes y actualizar la palabra oculta
    public void procesarLetra(char letra) {
        boolean letraEncontrada = false; // Indica si la letra se encontró en la palabra
        StringBuilder nuevaPalabraOculta = new StringBuilder(palabraOculta); // Crear una copia de la palabra oculta

        // Recorrer cada letra de la palabra seleccionada
        for (int i = 0; i < palabraSeleccionada.length(); i++) {
            if (palabraSeleccionada.charAt(i) == letra) { // Si la letra coincide
                nuevaPalabraOculta.setCharAt(i, letra); // Reemplazar el asterisco por la letra correcta
                letraEncontrada = true; // Marcar que la letra fue encontrada
            }
        }

        // Si la letra fue encontrada, actualizar la palabra oculta y notificar a los clientes
        if (letraEncontrada) {
            palabraOculta = nuevaPalabraOculta.toString(); // Actualizar la palabra oculta
            txtPalabra.setText(palabraOculta); // Mostrar la palabra actualizada en la interfaz
            enviarATodos(palabraOculta); // Enviar la palabra actualizada a todos los clientes

            // Si la palabra ha sido adivinada completamente
            if (!palabraOculta.contains("*")) {
                enviarATodos("¡Palabra adivinada! La palabra era: " + palabraSeleccionada); // Notificar a los clientes
                cerrarConexiones(); // Cerrar las conexiones de los clientes y finalizar el juego
            }
        }
    }

    // Metodo para cerrar todas las conexiones de los clientes y el servidor
    private void cerrarConexiones() {
        aceptarConexiones.stop(); // Detener el hilo de aceptación de conexiones
        for (int i = 0; i < clientesConectados.size(); i++) { // Recorrer cada cliente
            PrintWriter cliente = clientesConectados.get(i);
            cliente.close(); // Cerrar la conexión del cliente
        }
        try {
            serverSocket.close(); // Cerrar el socket del servidor
        } catch (IOException e) {
            e.printStackTrace(); // Manejar excepción en caso de error al cerrar el socket
        }
        System.exit(0); // Finalizar el programa
    }

    // Metodo principal para ejecutar la aplicación y mostrar la interfaz gráfica
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() { // Ejecutar en el hilo de eventos de Swing
            @Override
            public void run() {
                new ServidorAhorcado().setVisible(true); // Crear y mostrar la ventana del servidor
            }
        });
    }
}

