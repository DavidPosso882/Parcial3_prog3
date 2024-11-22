package EstacionGasolina;

// Clase que representa un tanque de gasolina con capacidad limitada
public class Tanque {
    private int gasolinaActual = 0; // Cantidad de gasolina actual en el tanque (inicialmente 0)
    private int capacidadMaxima = 1000; // Capacidad máxima del tanque (1000 galones)

    // Metodo sincronizado para llenar el tanque con una cierta cantidad de gasolina
    public synchronized void llenar(int cantidad) throws InterruptedException {
        // Mientras la cantidad de gasolina actual más la cantidad a llenar supere la capacidad máxima del tanque
        while (gasolinaActual + cantidad > capacidadMaxima) {
            wait(); // El hilo espera a que haya espacio suficiente en el tanque para agregar más gasolina
        }
        gasolinaActual += cantidad; // Se incrementa la cantidad de gasolina en el tanque
        System.out.println("Tanque abastecido con " + cantidad + " galones. Gasolina actual: " + gasolinaActual); // Se imprime la cantidad agregada y la gasolina actual
        notifyAll(); // Notifica a todos los hilos que están esperando en este objeto, en caso de que otros hilos puedan continuar
    }

    // Metodo sincronizado para consumir una cierta cantidad de gasolina del tanque
    public synchronized void consumir(int cantidad) throws InterruptedException {
        // Mientras no haya suficiente gasolina en el tanque para el consumo
        while (gasolinaActual < cantidad) {
            wait(); // El hilo espera a que haya suficiente gasolina para consumir
        }
        gasolinaActual -= cantidad; // Se disminuye la cantidad de gasolina en el tanque
        System.out.println("Se consumieron " + cantidad + " galones. Gasolina actual: " + gasolinaActual); // Se imprime la cantidad consumida y la gasolina actual
        notifyAll(); // Notifica a todos los hilos que están esperando en este objeto, en caso de que otros hilos puedan continuar
    }

    // Metodo getter para obtener la cantidad actual de gasolina en el tanque
    public int getGasolinaActual() {
        return gasolinaActual; // Devuelve la cantidad de gasolina actual en el tanque
    }
}

