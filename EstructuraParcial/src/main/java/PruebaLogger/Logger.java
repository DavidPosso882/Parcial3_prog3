package PruebaLogger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class Logger {

    public static java.util.logging.Logger logger;

    public static void iniciarLogger() {
        // El logger tiene un nombre fijo
        logger = java.util.logging.Logger.getLogger("MiLoggerBasico");

        try {
            // Crear un manejador de archivos para guardar los logs
            FileHandler fileHandler = new FileHandler("registro.log", true);

            // Usar el formato simple para los logs
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);

            // Agregar el manejador de archivos al logger
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            // Manejar la excepción de manera básica
            System.out.println("Error al configurar el logger.");
            e.printStackTrace();

        }
    }
}

