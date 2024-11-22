package Pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class RecursoCompartidoSync {
    public synchronized void usoDelRecurso() {
        System.out.println(Thread.currentThread().getName() + " está usando el recurso.");
        try {
            Thread.sleep(1000); // Simular trabajo con el recurso
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " terminó de usar el recurso.");
    }
}

class Tarea1 implements Runnable {
    private RecursoCompartidoSync recurso1;

    public Tarea1(RecursoCompartidoSync recurso1) {
        this.recurso1 = recurso1;
    }

    @Override
    public void run() {
        recurso1.usoDelRecurso(); // Este metodo está sincronizado
    }
}

public class PoolConSync {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3); // Crear un pool de 3 hilos
        RecursoCompartidoSync recurso1 = new RecursoCompartidoSync();

        // Ejecutar varias tareas en el pool
        for (int i = 0; i < 5; i++) {
            pool.execute(new Tarea1(recurso1));
        }

        pool.shutdown();
    }
}

