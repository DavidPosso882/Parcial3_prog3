import java.util.concurrent.Semaphore;

public class Tarea implements Runnable {

    private String nombre;
    private Semaphore semaforo;

    public Tarea(String nombre, Semaphore semaforo) {
        this.nombre = nombre;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        try{
            semaforo.acquire();
            System.out.println(nombre + " ha adquirido el semaforo");

            System.out.println(nombre + " está trabajando...");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(nombre + " se ha liberado el semaforo.");
            semaforo.release();
        }
    }
}
