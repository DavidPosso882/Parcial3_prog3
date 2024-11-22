package CuentaRegresiva;

// Clase que implementa la interfaz Runnable y representa un hilo que esperará hasta que ocurra un evento de sincronización
public class HiloEsperando implements Runnable {

    // Referencia al objeto HiloPrincipal, que contiene la lógica de sincronización
    private final HiloPrincipal sincronizador;

    // Constructor que inicializa el objeto HiloEsperando con el sincronizador proporcionado
    public HiloEsperando(HiloPrincipal sincronizador) {
        // Asigna el objeto de sincronización (HiloPrincipal) a la variable de instancia
        this.sincronizador = sincronizador;
    }

    // Metodo `run` que define el comportamiento del hilo cuando es iniciado
    @Override
    public void run() {
        // Llama al metodo `esperarCuentaRegresiva` del objeto `sincronizador`,
        // lo que hace que este hilo espere hasta que la cuenta regresiva termine
        sincronizador.esperarCuentaRegresiva();
    }
}

