package PruebaLogger;

public class Main {

    public static void main(String[] args) {
        // Iniciar el logger usando la clase Logger
        Logger.iniciarLogger();

        // Registrar algunos mensajes de prueba
        Logger.logger.info("Este es un mensaje informativo.");
        Logger.logger.warning("Este es un mensaje de advertencia.");

        // Simular un error
        try {
            int resultado = 10 / 0;  // Esto va a causar una excepción
        } catch (ArithmeticException e) {
            Logger.logger.severe("Error: División por cero.");
        }

        // Mensaje de finalización del programa
        Logger.logger.info("El programa ha terminado.");
    }
}


/*

 */

