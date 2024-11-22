package EstacionGasolina;

import java.util.Scanner;

// Clase principal que simula la operación de una estación de servicio con productores (camiones cisterna) y consumidores (vehículos y motos)
public class EstacionServicio {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Crea un objeto Scanner para leer la entrada del usuario
        Tanque tanque = new Tanque(); // Crea un objeto Tanque para almacenar la gasolina
        int numProductores = 4; // Número de camiones cisterna que producirán gasolina
        int ciclos; // Variable para almacenar el número de ciclos de carga

        // Solicitar al administrador el número de ciclos de carga
        System.out.print("Ingrese el numero de ciclos de carga (o presione Enter para usar la capacidad total de los " +
                "camiones): ");
        String input = scanner.nextLine(); // Lee la entrada del administrador
        if (input.isEmpty()) {
            ciclos = 20; // Si no se ingresa ningún valor, se usa el valor por defecto (20 ciclos)
        } else {
            ciclos = Integer.parseInt(input); // Si se ingresa un número, lo convierte a entero
        }

        // Crear y lanzar los hilos de productores (camiones cisterna)
        for (int i = 0; i < numProductores; i++) {
            Productor productor = new Productor(tanque, ciclos); // Crea un productor con el tanque y los ciclos especificados
            Thread hiloProductor = new Thread(new Productor(tanque, ciclos)); // Crea un hilo para el productor
            hiloProductor.start(); // Inicia el hilo del productor
        }

        // Crear y lanzar los hilos de consumidores (vehículos y motos)
        // El vehículo consume 10 galones y la moto consume 4 galones
        Thread hiloConsumidorVehiculo = new Thread(new Consumidor(tanque, 10)); // Hilo para el consumidor de vehículos (10 galones)
        Thread hiloConsumidorMoto = new Thread(new Consumidor(tanque, 4)); // Hilo para el consumidor de motos (4 galones)
        hiloConsumidorVehiculo.start(); // Inicia el hilo del consumidor de vehículos
        hiloConsumidorMoto.start(); // Inicia el hilo del consumidor de motos

        // Esperar un tiempo para que termine la simulación
        try {
            // El tiempo de simulación es igual al número de ciclos multiplicado por 1000 (para convertirlo a milisegundos)
            Thread.sleep(ciclos * 1000); // Pausa la ejecución del programa durante el tiempo de simulación
            System.exit(0); // Termina el programa después de la simulación
        } catch (InterruptedException e) {
            e.printStackTrace(); // Si el hilo es interrumpido, se imprime la traza de la excepción
        }
    }
}
