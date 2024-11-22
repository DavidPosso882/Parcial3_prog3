public class MatrizTranspuesta {

    public static void main(String[] args) {
        int[][] matriz = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] transpuesta = transponerMatriz(matriz, new int[matriz[0].length][matriz.length], 0, 0);

        // Imprimir la matriz transpuesta de manera recursiva
        imprimirMatriz(transpuesta, 0, 0);
    }

    public static int[][] transponerMatriz(int[][] original, int[][] transpuesta, int i, int j) {
        if (i == original.length) {
            return transpuesta;
        }
        if (j == original[i].length) {
            return transponerMatriz(original, transpuesta, i + 1, 0);
        }
        transpuesta[j][i] = original[i][j];
        return transponerMatriz(original, transpuesta, i, j + 1);
    }

    public static void imprimirMatriz(int[][] matriz, int i, int j) {
        if (i == matriz.length) {
            return;
        }
        if (j == matriz[i].length) {
            System.out.println();
            imprimirMatriz(matriz, i + 1, 0);
            return;
        }
        System.out.print(matriz[i][j] + " ");
        imprimirMatriz(matriz, i, j + 1);
    }
}


