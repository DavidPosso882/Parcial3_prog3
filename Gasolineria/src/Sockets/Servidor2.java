package Sockets;

import java.io.*; // Importa las clases de entrada y salida.
import java.net.*; // Importa las clases necesarias para redes.

class Persona implements Serializable { // Define una clase 'Persona' que implementa Serializable para permitir su serialización.
    String nombre;
    int edad;

    Persona(String nombre, int edad) { // Constructor de la clase Persona.
        this.nombre = nombre;
        this.edad = edad;
    }
}

public class ServidorObjeto {
    public static void main(String[] args) {
        // Usa un bloque try-with-resources para manejar el ServerSocket.
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Servidor esperando objeto...");
            // Acepta la conexión del cliente.
            Socket clientSocket = serverSocket.accept();

            // Crea un ObjectInputStream para leer el objeto del cliente.
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            // Lee el objeto 'Persona' enviado por el cliente.
            Persona persona = (Persona) in.readObject();
            System.out.println("Recibido: " + persona.nombre + ", Edad: " + persona.edad); // Muestra los datos del objeto recibido.
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // Muestra cualquier error.
        }
    }
}



import java.io.*; // Importa las clases de entrada y salida.
        import java.net.*; // Importa las clases necesarias para redes.

public class ClienteObjeto {
    public static void main(String[] args) {
        // Usa try-with-resources para manejar el socket del cliente.
        try (Socket socket = new Socket("localhost", 12345)) {
            // Crea un ObjectOutputStream para enviar el objeto al servidor.
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            // Crea un objeto 'Persona' y lo envía al servidor.
            Persona persona = new Persona("David", 25);
            out.writeObject(persona);
            System.out.println("Objeto enviado al servidor."); // Confirma el envío del objeto.
        } catch (IOException e) {
            e.printStackTrace(); // Muestra cualquier error.
        }
    }
}

