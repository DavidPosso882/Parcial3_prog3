public class MiHilo extends Thread {

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(i + " - Hilo: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000); // Pausa de 1 segundo entre iteraciones
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
