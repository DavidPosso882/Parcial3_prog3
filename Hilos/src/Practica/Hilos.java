package Practica;

public class Hilos extends Thread {

    private static String resultado = "";
    private String palabra = "Hola";
    private String proceso;
    private static boolean proceso1Terminado = false;
    private static final long DURACION_TOTAL = 20000;

    public Hilos(String proceso) {
        this.proceso = proceso;
    }

   /* @Override
    public void run() {
        if ("proceso1".equals(proceso)){
            concatenarString(palabra);
        } else if ("proceso2".equals(proceso)){
            borrarInformacion();
        }
    }

    */

    @Override
    public void run() {
        long tiempoInicio = System.currentTimeMillis(); // Para repetir ciclo
        while (System.currentTimeMillis() - tiempoInicio < DURACION_TOTAL) {
            if ("proceso1".equals(proceso)) {
                concatenarString(palabra);
            } else if ("proceso2".equals(proceso)) {
                borrarInformacion();
            }
        }
    }

    private synchronized void concatenarString(String palabra) {
        long tiempoInicio = System.currentTimeMillis();
        long tiempoMaximo = 6000;

        while (System.currentTimeMillis() - tiempoInicio < tiempoMaximo) {
            resultado += palabra;
            System.out.println(resultado);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        proceso1Terminado = true;
        notify(); // Notifica al hilo que está esperando
    }

    private synchronized void borrarInformacion() {
        while (!proceso1Terminado) {
            try {
                wait(100); // Espera corta y verifica de nuevo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Este es el resultado antes de la eliminación: " + resultado);
        resultado = "";
        System.out.println("resultado: " + resultado);
        proceso1Terminado = false; // Reiniciar para el próximo ciclo
    }
}

