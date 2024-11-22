import java.util.concurrent.Semaphore;

public class SemaforoEjemplo {

    private static Semaphore semaforo = new Semaphore(1);

    public static void main(String[] args) {


        Thread hilo1 = new Thread(new Tarea("Hilo1", semaforo));
        Thread hilo2 = new Thread(new Tarea("Hilo2", semaforo));

        hilo1.start();
        hilo2.start();

    }
}
