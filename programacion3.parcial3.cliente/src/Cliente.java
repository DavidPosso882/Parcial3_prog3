import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.List;
import javax.swing.event.*;

public class Cliente extends JFrame {
    private static final String HOST = "localhost";
    private static final int PUERTO = 5000;

    private JList<TrabajoGrado> listaTrabajosGrado;
    private JTextArea areaAutores;
    private DefaultListModel<TrabajoGrado> modeloLista;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket socket;
    private boolean conectado = false;

    public Cliente() {
        super("Sistema de Trabajos de Grado");
        configurarInterfaz();
        iniciarConexion();
    }

    private void configurarInterfaz() {
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        modeloLista = new DefaultListModel<>();
        listaTrabajosGrado = new JList<>(modeloLista);
        areaAutores = new JTextArea(10, 30);
        areaAutores.setEditable(false);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(listaTrabajosGrado),
                new JScrollPane(areaAutores));

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnReconectar = new JButton("Reconectar");
        btnReconectar.addActionListener(e -> reconectar());
        panelSuperior.add(btnReconectar);

        add(panelSuperior, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);

        listaTrabajosGrado.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarAutores();
            }
        });

        setSize(900, 400);
        setLocationRelativeTo(null);
    }

    private void iniciarConexion() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            socket = new Socket(HOST, PUERTO);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            conectado = true;
            cargarTrabajosGrado();
        } catch (IOException e) {
            manejarErrorConexion(e);
        }
    }

    private void reconectar() {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                iniciarConexion();
                return null;
            }
        };
        worker.execute();
    }

    private synchronized void enviarPeticion(String peticion, ResponseHandler handler) {
        if (!conectado) {
            JOptionPane.showMessageDialog(this,
                    "No hay conexión con el servidor",
                    "Error de Conexión",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            out.writeObject(peticion);
            out.flush();
            Object response = in.readObject();
            handler.handleResponse(response);
        } catch (Exception e) {
            manejarErrorConexion(e);
        }
    }

    private void cargarTrabajosGrado() {
        enviarPeticion("TRABAJOS_GRADO", response -> {
            try {
                List<TrabajoGrado> trabajosGrado = (List<TrabajoGrado>) response;
                SwingUtilities.invokeLater(() -> {
                    modeloLista.clear();
                    for (TrabajoGrado trabajo : trabajosGrado) {
                        modeloLista.addElement(trabajo);
                    }
                });
            } catch (Exception e) {
                manejarError("Error al procesar trabajos de grado", e);
            }
        });
    }

    private void mostrarAutores() {
        TrabajoGrado trabajoSeleccionado = listaTrabajosGrado.getSelectedValue();
        if (trabajoSeleccionado != null) {
            enviarPeticion("AUTORES:" + trabajoSeleccionado.getId(), response -> {
                try {
                    List<Autor> autores = (List<Autor>) response;
                    SwingUtilities.invokeLater(() -> {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Autores del trabajo:\n\n");
                        for (Autor autor : autores) {
                            sb.append(autor.toString()).append("\n");
                        }
                        areaAutores.setText(sb.toString());
                    });
                } catch (Exception e) {
                    manejarError("Error al procesar autores", e);
                }
            });
        }
    }

    private void manejarErrorConexion(Exception e) {
        conectado = false;
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(this,
                    "Error de conexión: " + e.getMessage() + "\nIntente nuevamente",
                    "Error de Conexión",
                    JOptionPane.ERROR_MESSAGE);
        });
    }



    private void manejarError(String mensaje, Exception e) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(this,
                    mensaje + ": " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        });
    }

    @Override
    public void dispose() {
        try {
            conectado = false;
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.dispose();
    }

    private interface ResponseHandler {
        void handleResponse(Object response) throws Exception;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Cliente cliente = new Cliente();
            cliente.setVisible(true);
        });
    }
}
