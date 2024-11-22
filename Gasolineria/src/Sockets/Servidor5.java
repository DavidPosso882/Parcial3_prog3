package Sockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ServidorMulticast {
    public static void main(String[] args) {
        try (MulticastSocket socket = new MulticastSocket()) { // Crea un socket de datagramas multicast.
            String mensaje = "¡Hola, este es un mensaje multicast!"; // Mensaje a enviar.

            InetAddress grupo = InetAddress.getByName("230.0.0.1"); // Dirección del grupo multicast.
            DatagramPacket paquete = new DatagramPacket(mensaje.getBytes(), mensaje.length(), grupo, 12345); // Crea el paquete de datagrama.
            socket.send(paquete); // Envía el paquete al grupo multicast.

            System.out.println("Mensaje multicast enviado.");
        } catch (IOException e) {
            e.printStackTrace(); // Muestra cualquier error.
        }
    }
}



import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ClienteMulticast {
    public static void main(String[] args) {
        try (MulticastSocket socket = new MulticastSocket(12345)) { // Se une al grupo en el puerto 12345.
            InetAddress grupo = InetAddress.getByName("230.0.0.1"); // Dirección del grupo multicast.
            socket.joinGroup(grupo); // Se une al grupo multicast.

            byte[] buffer = new byte[256]; // Buffer de recepción.
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length); // Crea el paquete para recibir.

            socket.receive(paquete); // Recibe el paquete del grupo multicast.
            String mensaje = new String(paquete.getData(), 0, paquete.getLength()); // Obtiene el mensaje.

            System.out.println("Mensaje recibido: " + mensaje); // Muestra el mensaje recibido.
            socket.leaveGroup(grupo); // Deja el grupo multicast.
        } catch (IOException e) {
            e.printStackTrace(); // Muestra cualquier error.
        }
    }
}

