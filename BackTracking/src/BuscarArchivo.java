import java.io.File;

public class BuscarArchivo {

    public static File buscarArchivo(String nombreArchivo, File nombreCarpeta) {
        // Verifica si el directorio es válido
        if (nombreCarpeta == null || !nombreCarpeta.isDirectory()) {
            return null;
        }

        // Lista todos los archivos y carpetas en el directorio actual
        File[] files = nombreCarpeta.listFiles();
        if (files == null) {
            return null;
        }

        // Llama a la función recursiva para buscar el archivo
        return searchFileRecursively(nombreArchivo, files, 0);
    }

    private static File searchFileRecursively(String nombreArch, File[] archivos, int i) {
        // Si hemos recorrido todos los archivos y carpetas, retornamos null
        if (i >= archivos.length) {
            return null;
        }

        File file = archivos[i];

        // Si encuentra el archivo, lo retorna
        if (file.isFile() && file.getName().equals(nombreArch)) {
            return file;
        }

        // Si encuentra una carpeta, realiza una llamada recursiva
        if (file.isDirectory()) {
            File found = buscarArchivo(nombreArch, file);
            if (found != null) {
                return found;
            }
        }

        // Llama recursivamente al siguiente archivo o carpeta
        return searchFileRecursively(nombreArch, archivos, i + 1);
    }

    public static void main(String[] args) {

        File inicio = new File("C:\\Users\\marce\\OneDrive\\Desktop\\inicio");
        String nombreArchivo = "8.txt";

        File result = buscarArchivo(nombreArchivo, inicio);
        if (result != null) {
            System.out.println("Archivo encontrado: " + result.getAbsolutePath());
        } else {
            System.out.println("Archivo no encontrado.");
        }
    }
}

