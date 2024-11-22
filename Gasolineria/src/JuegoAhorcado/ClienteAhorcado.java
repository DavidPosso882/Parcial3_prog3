package JuegoAhorcado;

// Importaciones necesarias para la interfaz gráfica y comunicación de red
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// Clase que representa el cliente para el juego de Ahorcado
public class ClienteAhorcado extends JFrame {

    // Componentes de la interfaz gráfica
    private JTextField txtPalabra; // Muestra la palabra en progreso con letras adivinadas y ocultas
    private JTextField txtLetra; // Campo de entrada para que el usuario envíe una letra
    private JButton btnEnviar; // Botón para enviar la letra

    // Variables para la conexión de red
    private Socket socket; // Socket para la conexión con el servidor
    private PrintWriter out; // Objeto para enviar mensajes al servidor
    private BufferedReader in; // Objeto para recibir mensajes del servidor

    // Constructor que configura la interfaz gráfica y la conexión con el servidor
    public ClienteAhorcado() {
        setTitle("Cliente Ahorcado"); // Título de la ventana
        setSize(300, 200); // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra el programa al cerrar la ventana
        setLayout(new FlowLayout()); // Usa un layout sencillo para la distribución

        // Configuración de los componentes de la interfaz
        txtPalabra = new JTextField(20); // Campo de texto para mostrar la palabra
        txtPalabra.setEditable(false); // No editable para solo mostrar la palabra actual
        txtLetra = new JTextField(5); // Campo de texto para la letra
        btnEnviar = new JButton("Enviar"); // Botón de enviar

        // Añadir los componentes a la ventana
        add(new JLabel("Palabra: "));
        add(txtPalabra);
        add(new JLabel("Letra: "));
        add(txtLetra);
        add(btnEnviar);

        // Iniciar la conexión con el servidor
        try {
            socket = new Socket("localhost", 12345); // Conecta al servidor en localhost y puerto 12345
            out = new PrintWriter(socket.getOutputStream(), true); // Para enviar datos al servidor
            in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Para recibir datos del servidor

            // Inicia un hilo para recibir mensajes del servidor continuamente
            new Thread(new RecibirMensajes(in, txtPalabra, this, socket)).start();
        } catch (Exception e) { // Manejo de posibles excepciones
            e.printStackTrace();
        }

        // Acción del botón enviar que envía la letra al servidor
        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarLetra(); // Llama al metodo que envía la letra al servidor
            }
        });
    }

    // Metodo que envía la letra al servidor
    private void enviarLetra() {
        String letra = txtLetra.getText().trim(); // Toma la letra ingresada y elimina espacios
        if (letra.length() == 1) { // Verifica que solo se ha ingresado una letra
            out.println(letra); // Envía la letra al servidor
            txtLetra.setText(""); // Limpia el campo de texto
        } else { // Si no es una letra válida
            JOptionPane.showMessageDialog(this, "Ingrese una sola letra."); // Muestra mensaje de error
        }
    }

    // Metodo principal para iniciar la aplicación cliente
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() { // Ejecuta el cliente en el hilo de la GUI
            public void run() {
                new ClienteAhorcado().setVisible(true); // Crea y muestra la ventana del cliente
            }
        });
    }
}

