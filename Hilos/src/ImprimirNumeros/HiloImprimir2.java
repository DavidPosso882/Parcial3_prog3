package ImprimirNumeros;

public class HiloImprimir2 extends Thread {
    private int i = 100;
    public final double DURACION_TOTAL = 5000;

    @Override
    public void run() {
        double tiempoInicio = System.currentTimeMillis();
        while (System.currentTimeMillis() - tiempoInicio < DURACION_TOTAL){
            // Imprime antes de decrementar para comenzar con 100
            System.out.println(i);
            i -= 1;

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
