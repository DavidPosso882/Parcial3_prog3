public class Main {
    public static void main(String[] args) {
       /*
        MiHilo hilo1 = new MiHilo();
        MiHilo hilo2 = new MiHilo();


        hilo1.start(); // Inicia el primer hilo
        hilo2.start(); // Inicia el segundo hilo

*/

        // Crear instancias de Runnable
        MiRunnable miRunnable1 = new MiRunnable();
        MiRunnable miRunnable2 = new MiRunnable();

        // Crear hilos
        Thread hilo1 = new Thread(miRunnable1);
        Thread hilo2 = new Thread(miRunnable2);

        Thread hilo3 = new Thread(new MiRunnable());

        // Iniciar hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}





/*class MiRunnable implements Runnable {
    private int contador = 0;

    // Metodo sincronizado
    public synchronized void incrementarContador() {
        contador++;
        System.out.println("Contador: " + contador + " - Hilo: " + Thread.currentThread().getName());
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            incrementarContador(); // Acceso controlado al recurso compartido
            try {
                Thread.sleep(1000); // Pausa de 1 segundo entre iteraciones
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class EjemploRunnable {
    public static void main(String[] args) {
        // Usar la misma instancia de Runnable para ambos hilos
        Runnable miRunnable = new MiRunnable();

        // Crear hilos
        Thread hilo1 = new Thread(miRunnable);
        Thread hilo2 = new Thread(miRunnable);

        // Iniciar hilos
        hilo1.start();
        hilo2.start();
    }
}*/

