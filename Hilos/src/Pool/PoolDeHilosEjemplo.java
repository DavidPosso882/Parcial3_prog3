package Pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PoolDeHilosEjemplo {
    public static void main(String[] args) {
        // Crear un pool de hilos con 3 hilos
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Enviar tareas al pool
        for (int i = 1; i < 6; i++) {
            Runnable tarea = new Tarea2(String.valueOf(i));
            executor.execute(tarea);
        }

        // Cerrar el pool de hilos cuando todas las tareas hayan finalizado
        executor.shutdown();
    }
}

class Tarea2 implements Runnable {
    private String nombre;

    public Tarea2(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        System.out.println("Tarea " + nombre + " está siendo ejecutada por " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000); // Simulando una tarea que toma tiempo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Tarea " + nombre + " completada por " + Thread.currentThread().getName());
    }
}
