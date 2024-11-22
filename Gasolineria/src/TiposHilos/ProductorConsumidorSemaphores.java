package TiposHilos;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

class Buffer1 {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int CAPACITY = 5;

    private final Semaphore items = new Semaphore(0);
    private final Semaphore space = new Semaphore(CAPACITY);
    private final Semaphore mutex = new Semaphore(1);

    public void produce(int value) throws InterruptedException {
        space.acquire();
        mutex.acquire();
        queue.add(value);
        System.out.println("Producido: " + value);
        mutex.release();
        items.release();
    }

    public int consume() throws InterruptedException {
        items.acquire();
        mutex.acquire();
        int value = queue.poll();
        System.out.println("Consumido: " + value);
        mutex.release();
        space.release();
        return value;
    }
}

class Productor2 implements Runnable {
    private final Buffer1 buffer;

    public Productor2(Buffer1 buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int value = 0;
        try {
            while (true) {
                buffer.produce(value++);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumidor2 implements Runnable {
    private final Buffer1 buffer;

    public Consumidor2(Buffer1 buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                buffer.consume();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class ProductorConsumidorSemaphores {
    public static void main(String[] args) {
        Buffer1 buffer = new Buffer1();

        // Creando múltiples productores y consumidores
        for (int i = 0; i < 3; i++) {
            new Thread(new Productor2(buffer), "Productor-" + (i + 1)).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(new Consumidor2(buffer), "Consumidor-" + (i + 1)).start();
        }
    }
}

