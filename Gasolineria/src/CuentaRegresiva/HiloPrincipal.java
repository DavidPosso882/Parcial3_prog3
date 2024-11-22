package CuentaRegresiva;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// Clase principal para ejecutar un sistema de cuenta regresiva sincronizado con varios hilos
public class HiloPrincipal {

    // Constructor de la clase HiloPrincipal (sin parámetros)
    public HiloPrincipal() {
    }

    // Constante que define el valor inicial de la cuenta regresiva
    private static final int INICIO_CUENTA_REGRESIVA = 10;
    // Variable que actúa como contador, inicializada con el valor de cuenta regresiva
    private int contador = INICIO_CUENTA_REGRESIVA;
    // Instancia de ReentrantLock para gestionar la sincronización entre hilos
    private final ReentrantLock bloqueo = new ReentrantLock();
    // Objeto Condition asociado al bloqueo para permitir que los hilos esperen y sean notificados
    private final Condition condicion = bloqueo.newCondition();

    // Metodo que inicia la cuenta regresiva y controla su disminución hasta llegar a 0
    public void iniciarCuentaRegresiva() {
        // Bloquear el acceso a la sección crítica para evitar interferencias de otros hilos
        bloqueo.lock();
        try {
            // Bucle que decrece el contador hasta llegar a 0
            while (contador > 0) {
                // Imprimir el valor actual de la cuenta regresiva
                System.out.println("Cuenta regresiva: " + contador);
                // Disminuir el contador en 1
                contador = contador - 1;
                // Hacer que el hilo duerma 1 segundo (1000 milisegundos) entre cada decremento
                Thread.sleep(1000);
            }
            // Imprimir el mensaje final de la cuenta regresiva cuando el contador llega a 0
            System.out.println("Cuenta regresiva: 0");
            // Notificar a todos los hilos en espera de que la cuenta regresiva ha terminado
            condicion.signalAll();
        } catch (InterruptedException e) {
            // En caso de una interrupción, establecer el estado de interrupción del hilo actual
            Thread.currentThread().interrupt();
        } finally {
            // Liberar el bloqueo, permitiendo que otros hilos puedan acceder
            bloqueo.unlock();
        }
    }

    // Metodo que permite a los hilos esperar hasta que la cuenta regresiva termine
    public void esperarCuentaRegresiva() {
        // Bloquear el acceso a la sección crítica
        bloqueo.lock();
        try {
            // Bucle de espera: los hilos esperarán mientras el contador sea mayor que 0
            while (contador > 0) {
                // El hilo se pone en espera hasta que reciba una notificación
                condicion.await();
            }
            // Una vez terminada la espera, el hilo comienza su ejecución y lo indica con un mensaje
            System.out.println(Thread.currentThread().getName() + " ha comenzado a ejecutarse.");
        } catch (InterruptedException e) {
            // Si el hilo es interrumpido, establecer su estado de interrupción
            Thread.currentThread().interrupt();
        } finally {
            // Liberar el bloqueo para permitir el acceso a otros hilos
            bloqueo.unlock();
        }
    }
}

