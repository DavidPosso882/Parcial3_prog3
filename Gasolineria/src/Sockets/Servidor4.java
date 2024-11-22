package Sockets;

import java.io.*; // Importa clases de entrada y salida.
import java.net.*; // Importa clases para trabajo en redes.

public class ServidorArchivo {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Servidor esperando archivo...");
            // Espera la conexión de un cliente.
            Socket clientSocket = serverSocket.accept();

            InputStream in = clientSocket.getInputStream(); // Obtiene el flujo de entrada del cliente.
            FileOutputStream fileOut = new FileOutputStream("archivo_recibido.txt"); // Abre un archivo para guardar los datos recibidos.
            byte[] buffer = new byte[1024]; // Tamaño del buffer de lectura.
            int bytesRead;

            // Lee los datos en bloques y los escribe en el archivo.
            while ((bytesRead = in.read(buffer)) != -1) {
                fileOut.write(buffer, 0, bytesRead); // Escribe los datos en el archivo.
            }

            System.out.println("Archivo recibido."); // Confirma la recepción del archivo.
            fileOut.close(); // Cierra el archivo de salida.
        } catch (IOException e) {
            e.printStackTrace(); // Muestra cualquier error.
        }
    }
}



import java.io.*; // Importa clases de entrada y salida.
        import java.net.*; // Importa clases para trabajo en redes.

public class ClienteArchivo {
    public static void main(String[] args) {
        // Usa un bloque try-with-resources para el socket del cliente.
        try (Socket socket = new Socket("localhost", 12345);
             FileInputStream fileIn = new FileInputStream("archivo_a_enviar.txt"); // Abre el archivo a enviar.
             OutputStream out = socket.getOutputStream()) {

            byte[] buffer = new byte[1024]; // Define el tamaño del buffer de envío.
            int bytesRead;

            // Lee el archivo en bloques y lo envía al servidor.
            while ((bytesRead = fileIn.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead); // Envía los datos al servidor.
            }

            System.out.println("Archivo enviado."); // Confirma el envío del archivo.
        } catch (IOException e) {
            e.printStackTrace(); // Muestra cualquier error.
        }
    }
}

