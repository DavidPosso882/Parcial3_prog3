package Productor_Consumidor;

// Importación de las clases necesarias para trabajar con números aleatorios y la cola bloqueante
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Productor implements Runnable {
    // Cola compartida donde el productor pondrá los números
    private BlockingQueue<Integer> queue;
    // Número máximo de producciones que realizará el productor
    private int maxProduccion;
    // Objeto para generar números aleatorios
    private Random random;

    // Constructor de la clase Productor
    public Productor(BlockingQueue<Integer> queue, int maxProduction) {
        this.queue = queue;  // Se asigna la cola compartida a la instancia
        this.maxProduccion = maxProduction;  // Se asigna el número máximo de producciones
        this.random = new Random();  // Se crea una nueva instancia de Random para generar números aleatorios
    }

    @Override
    public void run() {
        try {
            // Aquí se producen los números hasta alcanzar maxProduccion
            for (int i = 0; i < maxProduccion; i++) {
                // Generar un número aleatorio entre 1 y 100
                int numero = random.nextInt(100) + 1;  // Genera un número entre 1 y 100
                // Poner el número en la cola (si la cola está llena, esperará)
                queue.put(numero);  // Pone el número generado en la cola de manera bloqueante
                // Muestra el número producido y el nombre del hilo productor
                System.out.println(Thread.currentThread().getName() + " produjo: " + numero);
                // Espera los 500 milisegundos antes de producir el siguiente número
                Thread.sleep(500);  // Pausa la ejecución del hilo por medio segundo
            }
        } catch (InterruptedException e) {
            // Si el hilo es interrumpido durante la espera, se maneja la excepción
            System.out.println(Thread.currentThread().getName() + " fue interrumpido.");
        }
        // Al finalizar la producción, se imprime un mensaje indicando que el productor ha terminado
        System.out.println(Thread.currentThread().getName() + " ha terminado de producir.");
    }
}

