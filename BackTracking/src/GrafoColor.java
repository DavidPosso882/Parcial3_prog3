public class GrafoColor {

    public static void main(String[] args) {
        int[][] grafo = {
                {0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 1},
                {1, 0, 1, 0}
        };
        int numColores = 3;
        int[] colores = new int[grafo.length];

        if (resolverColoreo(grafo, colores, 0, numColores)) {
            imprimirColores(colores, 0);
        } else {
            System.out.println("No se encontró solución.");
        }
    }

    public static boolean resolverColoreo(int[][] grafo, int[] colores, int nodo, int numColores) {
        if (nodo == grafo.length) {
            return true;
        }

        return intentarColores(grafo, colores, nodo, 1, numColores);
    }

    public static boolean intentarColores(int[][] grafo, int[] colores, int nodo, int color, int numColores) {
        if (color > numColores) {
            return false;
        }

        if (esSeguro(grafo, colores, nodo, color)) {
            colores[nodo] = color;
            if (resolverColoreo(grafo, colores, nodo + 1, numColores)) {
                return true;
            }
            colores[nodo] = 0; // Backtracking
        }

        return intentarColores(grafo, colores, nodo, color + 1, numColores);
    }

    public static boolean esSeguro(int[][] grafo, int[] colores, int nodo, int color) {
        return verificarAdyacentes(grafo, colores, nodo, color, 0);
    }

    public static boolean verificarAdyacentes(int[][] grafo, int[] colores, int nodo, int color, int adyacente) {
        if (adyacente == grafo.length) {
            return true;
        }
        if (grafo[nodo][adyacente] == 1 && colores[adyacente] == color) {
            return false;
        }
        return verificarAdyacentes(grafo, colores, nodo, color, adyacente + 1);
    }

    public static void imprimirColores(int[] colores, int indice) {
        if (indice == colores.length) {
            return;
        }
        System.out.println("Nodo " + indice + " -> Color " + colores[indice]);
        imprimirColores(colores, indice + 1);
    }
}

