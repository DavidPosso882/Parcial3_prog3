package ImprimirNumeros;

public class MainImprimirNumeros {

    public static void main(String[] args) {

        HiloImprimir hiloImprimir = new HiloImprimir();
        HiloImprimir2 hiloImprimir2 = new HiloImprimir2();

        hiloImprimir.start();
        hiloImprimir2.start();

        try {
            hiloImprimir.join();
            hiloImprimir2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
