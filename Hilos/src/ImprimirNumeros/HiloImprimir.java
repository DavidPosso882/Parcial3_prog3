package ImprimirNumeros;

public class HiloImprimir extends Thread {

    private int i = 1;
    public final double DURACION_TOTAL = 5000;

    @Override
    public void run() {
        double tiempoInicio = System.currentTimeMillis();
        while (System.currentTimeMillis() - tiempoInicio < DURACION_TOTAL){
            System.out.println(i);
            i += 1;

            // Calculamos cuánto tiempo ha pasado para ajustarlo dinámicamente
            double tiempoRestante = DURACION_TOTAL - (System.currentTimeMillis() - tiempoInicio);
            if (tiempoRestante > 0) {
                try {
                    Thread.sleep(Math.min(50, (long) tiempoRestante));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

