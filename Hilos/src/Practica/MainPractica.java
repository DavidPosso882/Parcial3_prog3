package Practica;

public class MainPractica {

    public static void main(String[] args) {
        Hilos hilo1 = new Hilos("proceso1");
        Hilos hilo2 = new Hilos("proceso2");

        hilo2.start();
        hilo1.start();

        try {
            hilo1.join(); // Esperamos a que hilo1 termine
            hilo2.join(); // Esperamos a que hilo2 termine
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
