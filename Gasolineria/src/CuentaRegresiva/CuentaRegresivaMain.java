package CuentaRegresiva;

// Clase principal que coordina la ejecución de la cuenta regresiva y los hilos que esperan
public class CuentaRegresivaMain {

    // Metodo principal, punto de entrada de la aplicación
    public static void main(String[] args) throws InterruptedException {
        // Crear una instancia de HiloPrincipal, que controla la cuenta regresiva y la sincronización
        HiloPrincipal sincronizador = new HiloPrincipal();

        // Crear múltiples hilos (4 en este caso) que esperan a que termine la cuenta regresiva
        // Cada hilo recibe el objeto `sincronizador` para saber cuándo iniciar
        Thread hilo1 = new Thread(new HiloEsperando(sincronizador), "Hilo 1");
        Thread hilo2 = new Thread(new HiloEsperando(sincronizador), "Hilo 2");
        Thread hilo3 = new Thread(new HiloEsperando(sincronizador), "Hilo 3");
        Thread hilo4 = new Thread(new HiloEsperando(sincronizador), "Hilo 4");

        // Iniciar cada uno de los hilos de espera; estarán en pausa hasta que la cuenta regresiva llegue a 0
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();

        // Llamar al metodo `iniciarCuentaRegresiva` en el objeto `sincronizador`
        // Este metodo ejecuta la cuenta regresiva, emitiendo un aviso cuando llega a 0
        sincronizador.iniciarCuentaRegresiva();
    }
}

