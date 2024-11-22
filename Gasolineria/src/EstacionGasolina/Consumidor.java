package EstacionGasolina;

// Clase Consumidor que implementa Runnable para simular el consumo de gasolina por vehículos/motos
public class Consumidor implements Runnable {

    private Tanque tanque; // Referencia al objeto Tanque que contiene la gasolina disponible
    private int consumo;   // Cantidad de gasolina consumida por cada vehículo/moto (por ejemplo, 10 para vehículos y 4 para motos)

    // Constructor que recibe un objeto Tanque y el consumo específico del vehículo o moto
    public Consumidor(Tanque tanque, int consumo) {
        this.tanque = tanque; // Asigna el tanque al objeto
        this.consumo = consumo; // Asigna el consumo de gasolina al objeto
    }

    // Metodo que ejecuta el hilo para consumir gasolina
    @Override
    public void run() {
        try {
            // Mientras quede gasolina suficiente para continuar consumiendo (mínimo 4 litros)
            while (tanque.getGasolinaActual() >= 4) {
                tanque.consumir(consumo); // Llama al metodo consumir() del objeto Tanque, que reduce la gasolina disponible
                Thread.sleep(300); // Simula el tiempo entre clientes (por ejemplo, el tiempo de consumo de gasolina)
            }
            // Si no hay suficiente gasolina para continuar, imprime un mensaje
            System.out.println("No queda suficiente gasolina para consumir");
        } catch (InterruptedException e) {
            // Si el hilo es interrumpido, se maneja la excepción e interrumpe el hilo actual
            Thread.currentThread().interrupt();
        }
    }
}

