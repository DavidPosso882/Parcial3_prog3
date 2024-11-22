import java.util.ArrayList;
import java.util.List;

public class SumaSubconjuntos {

    public static void main(String[] args) {
        int[] conjunto = {1, 8, 3, 6};
        List<Integer> subconjunto1 = new ArrayList<>();
        List<Integer> subconjunto2 = new ArrayList<>();

        if (resolverParticion(conjunto, subconjunto1, subconjunto2)) {
            System.out.println("El conjunto puede ser particionado en dos subconjuntos con sumas iguales.");
            System.out.println("Subconjunto 1: " + subconjunto1);
            System.out.println("Subconjunto 2: " + subconjunto2);
        } else {
            System.out.println("El conjunto no puede ser particionado en dos subconjuntos con sumas iguales.");
        }
    }

    public static boolean resolverParticion(int[] conjunto, List<Integer> subconjunto1, List<Integer> subconjunto2) {
        int sumaTotal = calcularSuma(conjunto, 0);
        if (sumaTotal % 2 != 0) {
            return false;
        }
        return particionRecursiva(conjunto, 0, 0, sumaTotal / 2, subconjunto1, subconjunto2);
    }

    public static int calcularSuma(int[] conjunto, int indice) {
        if (indice == conjunto.length) {
            return 0;
        }
        return conjunto[indice] + calcularSuma(conjunto, indice + 1);
    }

    public static boolean particionRecursiva(int[] conjunto, int indice, int sumaActual, int sumaObjetivo, List<Integer> subconjunto1, List<Integer> subconjunto2) {
        if (sumaActual == sumaObjetivo) {
            agregarRestantes(conjunto, indice, subconjunto2);
            return true;
        }
        if (indice == conjunto.length || sumaActual > sumaObjetivo) {
            return false;
        }

        // Incluir el elemento actual en el subconjunto 1
        subconjunto1.add(conjunto[indice]);
        if (particionRecursiva(conjunto, indice + 1, sumaActual + conjunto[indice], sumaObjetivo, subconjunto1, subconjunto2)) {
            return true;
        }
        subconjunto1.remove(subconjunto1.size() - 1);

        // Incluir el elemento actual en el subconjunto 2
        subconjunto2.add(conjunto[indice]);
        if (particionRecursiva(conjunto, indice + 1, sumaActual, sumaObjetivo, subconjunto1, subconjunto2)) {
            return true;
        }
        subconjunto2.remove(subconjunto2.size() - 1);

        return false;
    }

    public static void agregarRestantes(int[] conjunto, int indice, List<Integer> subconjunto2) {
        if (indice == conjunto.length) {
            return;
        }
        subconjunto2.add(conjunto[indice]);
        agregarRestantes(conjunto, indice + 1, subconjunto2);
    }
}

