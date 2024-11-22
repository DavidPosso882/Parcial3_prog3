package Productor_Consumidor;

// Importación de clases necesarias para trabajar con colas bloqueantes y concurrencia
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProductorConsumidorDemo {
    public static void main(String[] args) {
        // Se crea una cola compartida de tipo BlockingQueue con capacidad para 10 elementos
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        // Definir la cantidad máxima de producción que cada productor podrá agregar a la cola
        int maxProduccion = 20;

        // Se crean dos instancias de los hilos Productor con la cola y el límite de producción
        Productor productor1 = new Productor(queue, maxProduccion);
        Productor productor2 = new Productor(queue, maxProduccion);

        // Se crean dos instancias de los hilos Consumidor que tomarán elementos de la cola
        Consumidor consumidor1 = new Consumidor(queue);
        Consumidor consumidor2 = new Consumidor(queue);

        // Se crean los hilos para los productores y consumidores, asignándoles nombres específicos
        Thread hiloProductor1 = new Thread(productor1, "Productor 1");
        Thread hiloProductor2 = new Thread(productor2, "Productor 2");
        Thread hiloConsumidor1 = new Thread(consumidor1, "Consumidor 1");
        Thread hiloConsumidor2 = new Thread(consumidor2, "Consumidor 2");

        // Se inician los hilos de los productores y consumidores
        hiloProductor1.start();
        hiloProductor2.start();
        hiloConsumidor1.start();
        hiloConsumidor2.start();

        // El hilo principal espera a que los productores terminen su ejecución
        try {
            hiloProductor1.join();
            hiloProductor2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Interrumpir el hilo actual si se produce una excepción
            System.out.println("El hilo principal fue interrumpido.");
        }

        // Espera adicional para que los consumidores procesen los últimos elementos en la cola
        try {
            Thread.sleep(5000); // Se hace una pausa de 5 segundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Interrumpir el hilo actual si se produce una excepción
            System.out.println("El hilo principal fue interrumpido durante la espera final.");
        }

        // Se detienen los consumidores
        consumidor1.detener();
        consumidor2.detener();

        // El hilo principal espera un poco más para que los consumidores terminen de procesar los elementos
        try {
            hiloConsumidor1.join(2000); // Espera hasta 2000 ms para que consumidor1 termine
            hiloConsumidor2.join(2000); // Espera hasta 2000 ms para que consumidor2 termine
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Interrumpir el hilo actual si se produce una excepción
            System.out.println("El hilo principal fue interrumpido mientras esperaba a los consumidores.");
        }

        // Mensaje indicando que el programa ha terminado
        System.out.println("Programa terminado.");
    }
}

