public class ProblemaParticion {

    public static void main(String[] args) {
        int[] conjunto = {1, 5, 11, 5};
        if (resolverParticion(conjunto)) {
            System.out.println("El conjunto puede ser particionado en dos subconjuntos con sumas iguales.");
        } else {
            System.out.println("El conjunto no puede ser particionado en dos subconjuntos con sumas iguales.");
        }
    }

    public static boolean resolverParticion(int[] conjunto) {
        int sumaTotal = calcularSuma(conjunto, 0);
        if (sumaTotal % 2 != 0) {
            return false;
        }
        return particionRecursiva(conjunto, 0, 0, sumaTotal / 2);
    }

    public static int calcularSuma(int[] conjunto, int indice) {
        if (indice == conjunto.length) {
            return 0;
        }
        return conjunto[indice] + calcularSuma(conjunto, indice + 1);
    }

    public static boolean particionRecursiva(int[] conjunto, int indice, int sumaActual, int sumaObjetivo) {
        if (sumaActual == sumaObjetivo) {
            return true;
        }
        if (indice == conjunto.length || sumaActual > sumaObjetivo) {
            return false;
        }

        // Incluir el elemento actual en el subconjunto
        boolean incluir = particionRecursiva(conjunto, indice + 1, sumaActual + conjunto[indice], sumaObjetivo);

        // Excluir el elemento actual del subconjunto
        boolean excluir = particionRecursiva(conjunto, indice + 1, sumaActual, sumaObjetivo);

        return incluir || excluir;
    }
}

