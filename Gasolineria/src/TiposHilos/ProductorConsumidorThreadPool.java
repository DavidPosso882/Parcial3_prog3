package TiposHilos;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Productor implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Productor(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int value = 0;
        try {
            while (true) {
                queue.put(value);
                System.out.println("Producido: " + value);
                value++;
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumidor implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Consumidor(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int value = queue.take();
                System.out.println("Consumido: " + value);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class ProductorConsumidorThreadPool {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        // Crear un pool de hilos con 4 hilos
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // Iniciar dos productores y dos consumidores
        executorService.execute(new Productor(queue));
        executorService.execute(new Productor(queue));
        executorService.execute(new Consumidor(queue));
        executorService.execute(new Consumidor(queue));

        // Cerrar el executor después de 10 segundos (opcional)
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdownNow();
    }
}

