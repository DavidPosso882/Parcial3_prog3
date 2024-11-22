import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Servidor {

    private static List<TrabajoGrado> trabajosGrado = new ArrayList<>();
    private static Map<String, Autor> autores = new HashMap<>();

    public static void main(String[] args) {
        cargarDatos();

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Servidor iniciado");

            while (true) {
                Socket clienteSocket = serverSocket.accept();
                Thread hiloCliente = new Thread(new ManejadorCliente(clienteSocket));
                hiloCliente.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cargarDatos() {
        cargarTrabajosGrado();
        cargarAutores();
    }

    private static void cargarTrabajosGrado() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/archivos/trabajogrados.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                TrabajoGrado trabajo = new TrabajoGrado(datos[0], datos[1], datos[2], datos[3]);
                for (int i = 4; i < datos.length; i++) {
                    trabajo.addAutorId(datos[i]);
                }
                trabajosGrado.add(trabajo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cargarAutores() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/archivos/autores.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                Autor autor = new Autor(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5]);
                autores.put(autor.getId(), autor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ManejadorCliente implements Runnable {
        private Socket clienteSocket;

        public ManejadorCliente(Socket socket) {
            this.clienteSocket = socket;
        }

        @Override
        public void run() {
            try (
                    ObjectOutputStream out = new ObjectOutputStream(clienteSocket.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(clienteSocket.getInputStream())
            ) {
                while (true) {
                    String solicitud = (String) in.readObject();
                    if ("TRABAJOS_GRADO".equals(solicitud)) {
                        out.writeObject(trabajosGrado);
                    } else if (solicitud.startsWith("AUTORES:")) {
                        String trabajoId = solicitud.split(":")[1];
                        List<Autor> autoresTrabajoGrado = obtenerAutoresTrabajoGrado(trabajoId);
                        out.writeObject(autoresTrabajoGrado);
                    }
                    out.flush();
                }
            } catch (EOFException e) {
                System.out.println("Cliente desconectado");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    clienteSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private List<Autor> obtenerAutoresTrabajoGrado(String trabajoId) {
            List<Autor> autoresTrabajoGrado = new ArrayList<>();
            for (TrabajoGrado trabajo : trabajosGrado) {
                if (trabajo.getId().equals(trabajoId)) {
                    for (String autorId : trabajo.getAutoresIds()) {
                        Autor autor = autores.get(autorId);
                        if (autor != null) {
                            autoresTrabajoGrado.add(autor);
                        }
                    }
                    break;
                }
            }
            return autoresTrabajoGrado;
        }
    }
}