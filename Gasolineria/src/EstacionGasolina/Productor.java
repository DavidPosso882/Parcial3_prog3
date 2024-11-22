package EstacionGasolina;

// Clase que representa el productor, en este caso, el camión cisterna que llena el tanque de gasolina
public class Productor implements Runnable {
    private Tanque tanque; // Referencia al objeto Tanque donde se almacenará la gasolina
    private int capacidad = 100; // Capacidad inicial del camión cisterna (100 galones)
    private int ciclos; // Número de ciclos de carga que realizará el productor
    private static int ciclosCompletados = 0; // Contador estático que lleva el registro de los ciclos completados

    // Constructor de la clase Productor, que recibe el tanque y el número de ciclos a realizar
    public Productor(Tanque tanque, int ciclos) {
        this.tanque = tanque;
        this.ciclos = ciclos; // Inicializa el número de ciclos de carga
    }

    // Metodo que se ejecutará cuando el hilo del productor sea iniciado
    @Override
    public void run() {
        try {
            // Mientras haya capacidad para cargar gasolina y el número de ciclos completados sea menor que el total de ciclos
            while (capacidad > 0 && ciclosCompletados < ciclos) {
                // Llama al metodo llenar del objeto Tanque, llenando el tanque con 20 galones de gasolina
                tanque.llenar(20);
                capacidad -= 20; // Reduce la capacidad del camión cisterna en 20 galones
                ciclosCompletados++; // Incrementa el contador de ciclos completados
                System.out.println("Capacidad restante: " + capacidad); // Muestra la capacidad restante del camión
                Thread.sleep(500); // Simula el tiempo de viaje del camión (500 milisegundos)
            }
            System.out.println("El camión cisterna ha completado todos los ciclos."); // Mensaje indicando que el productor ha terminado
        } catch (InterruptedException e) {
            // Si el hilo es interrumpido, se maneja la excepción y se interrumpe el hilo actual
            Thread.currentThread().interrupt();
        }
    }

    // Metodo getter para obtener la capacidad restante del camión cisterna
    public int getCapacidadRestante() {
        return capacidad;
    }
}

