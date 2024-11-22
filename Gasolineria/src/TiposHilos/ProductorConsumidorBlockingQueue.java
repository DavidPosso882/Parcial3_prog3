package TiposHilos;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Productor1 implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Productor1(BlockingQueue<Integer> queue) {
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

class Consumidor1 implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Consumidor1(BlockingQueue<Integer> queue) {
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

public class ProductorConsumidorBlockingQueue {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
        Thread productor = new Thread(new Productor1(queue));
        Thread consumidor = new Thread(new Consumidor1(queue));

        productor.start();
        consumidor.start();
    }
}

