package com.example.singleton;

import java.io.*;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Utilidades {
    private static Utilidades instance;
    private Logger logger;
    private ResourceBundle bundle;

    private Utilidades() {
        try {
            logger = Logger.getLogger("BibliotecaLog");
            FileHandler fh = new FileHandler("biblioteca.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setLocale(new Locale("es", "ES"));
    }

    public static Utilidades getInstance() {
        if (instance == null) {
            instance = new Utilidades();
        }
        return instance;
    }

    public void setLocale(Locale locale) {
        bundle = ResourceBundle.getBundle("messages", locale);
    }

    public String getMessage(String key) {
        return bundle.getString(key);
    }

    public void escribirLog(String mensaje, Level level) {
        logger.log(level, mensaje);
    }

    public void escribirArchivo(String nombreArchivo, List<String> datos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (int i = 0; i < datos.size(); i++) {
                writer.write(datos.get(i));
                writer.newLine();
                if ((i + 1) % 10 == 0) {
                    writer.flush();
                }
            }
        } catch (IOException e) {
            escribirLog("Error al escribir archivo: " + e.getMessage(), Level.SEVERE);
        }
    }

    public void serializarXML(Object objeto, String nombreArchivo) {
        try {
            ObjectMapper xmlMapper = new XmlMapper();
            xmlMapper.writeValue(new File(nombreArchivo), objeto);
        } catch (IOException e) {
            escribirLog("Error al serializar XML: " + e.getMessage(), Level.SEVERE);
        }
    }

    public <T> T deserializarXML(Class<T> clase, String nombreArchivo) {
        try {
            ObjectMapper xmlMapper = new XmlMapper();
            return xmlMapper.readValue(new File(nombreArchivo), clase);
        } catch (IOException e) {
            escribirLog("Error al deserializar XML: " + e.getMessage(), Level.SEVERE);
            return null;
        }
    }

    public void serializarBinario(Object objeto, String nombreArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(objeto);
        } catch (IOException e) {
            escribirLog("Error al serializar binario: " + e.getMessage(), Level.SEVERE);
        }
    }

    public Object deserializarBinario(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            escribirLog("Error al deserializar binario: " + e.getMessage(), Level.SEVERE);
            return null;
        }
    }
}
