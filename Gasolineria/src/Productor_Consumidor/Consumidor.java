package Productor_Consumidor;

// Importación de la clase BlockingQueue para trabajar con colas bloqueantes
import java.util.concurrent.BlockingQueue;

public class Consumidor implements Runnable {
    // Cola compartida de donde el consumidor tomará los números
    private BlockingQueue<Integer> queue;
    // Esta variable es para controlar si el consumidor debe seguir ejecutándose
    private boolean corriendo;

    // Constructor de la clase Consumidor
    public Consumidor(BlockingQueue<Integer> queue) {
        this.queue = queue;  // Asigna la cola compartida
        this.corriendo = true;  // Inicializa el estado del consumidor para que siga ejecutándose
    }

    @Override
    public void run() {
        try {
            // Mientras running sea true, el consumidor seguirá consumiendo
            while (corriendo) {
                // Intenta tomar un número de la cola (si está vacía, esperará)
                Integer numero = queue.take();  // Obtiene un número de la cola de manera bloqueante
                // Muestra el número consumido y el nombre del hilo consumidor
                System.out.println(Thread.currentThread().getName() + " consumió: " + numero);
                // Pausa la ejecución durante 1 segundo antes de consumir el siguiente número
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            // Si el hilo es interrumpido, maneja la excepción
            System.out.println(Thread.currentThread().getName() + " fue interrumpido.");
        }
        // Al finalizar el consumo, imprime un mensaje indicando que el consumidor ha terminado
        System.out.println(Thread.currentThread().getName() + " ha terminado.");
    }

    // Este metodo sirve para detener el consumidor
    public void detener() {
        corriendo = false;  // Cambia el estado para que el consumidor deje de ejecutarse
    }
}

