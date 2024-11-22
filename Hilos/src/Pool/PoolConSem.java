package Pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

class RecursoCompartido {
    private Semaphore semaforo;

    public RecursoCompartido(int permisos) {
        semaforo = new Semaphore(permisos); // Controlar cuántos hilos pueden acceder
    }

    public void usoDelRecurso() {
        try {
            semaforo.acquire();
            System.out.println(Thread.currentThread().getName() + " está usando el recurso.");
            Thread.sleep(1000); // Simular trabajo con el recurso
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " terminó de usar el recurso.");
            semaforo.release();
        }
    }
}

class Tarea implements Runnable {
    private RecursoCompartido recurso;

    public Tarea(RecursoCompartido recurso) {
        this.recurso = recurso;
    }

    @Override
    public void run() {
        recurso.usoDelRecurso();
    }
}

public class PoolConSem {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3); // Pool de 3 hilos
        RecursoCompartido recurso = new RecursoCompartido(2); // Permitir solo 2 hilos simultáneos

        // Ejecutar varias tareas
        for (int i = 0; i < 5; i++) {
            pool.execute(new Tarea(recurso));
        }

        pool.shutdown();
    }
}

