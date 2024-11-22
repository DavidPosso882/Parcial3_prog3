public class NReinas {

    public static void main(String[] args) {
        int n = 4;
        int[][] tablero = new int[n][n];
        resolverNReinas(tablero, 0);
    }

    public static boolean resolverNReinas(int[][] tablero, int fila) {
        int n = tablero.length;
        if (fila == n) {
            imprimirTablero(tablero, 0, 0);
            return true;
        }

        return colocarReina(tablero, fila, 0);
    }

    public static boolean colocarReina(int[][] tablero, int fila, int col) {
        int n = tablero.length;
        if (col == n) {
            return false;
        }

        if (esSeguro(tablero, fila, col)) {
            tablero[fila][col] = 1;
            if (resolverNReinas(tablero, fila + 1)) {
                return true;
            }
            tablero[fila][col] = 0; // Backtracking
        }

        return colocarReina(tablero, fila, col + 1);
    }

    public static boolean esSeguro(int[][] tablero, int fila, int col) {
        return verificarColumna(tablero, fila, col) &&
                verificarDiagonalIzquierda(tablero, fila, col) &&
                verificarDiagonalDerecha(tablero, fila, col);
    }

    public static boolean verificarColumna(int[][] tablero, int fila, int col) {
        if (fila < 0) {
            return true;
        }
        if (tablero[fila][col] == 1) {
            return false;
        }
        return verificarColumna(tablero, fila - 1, col);
    }

    public static boolean verificarDiagonalIzquierda(int[][] tablero, int fila, int col) {
        if (fila < 0 || col < 0) {
            return true;
        }
        if (tablero[fila][col] == 1) {
            return false;
        }
        return verificarDiagonalIzquierda(tablero, fila - 1, col - 1);
    }

    public static boolean verificarDiagonalDerecha(int[][] tablero, int fila, int col) {
        int n = tablero.length;
        if (fila < 0 || col >= n) {
            return true;
        }
        if (tablero[fila][col] == 1) {
            return false;
        }
        return verificarDiagonalDerecha(tablero, fila - 1, col + 1);
    }

    public static void imprimirTablero(int[][] tablero, int fila, int col) {
        int n = tablero.length;
        if (fila == n) {
            System.out.println();
            return;
        }
        if (col == n) {
            System.out.println();
            imprimirTablero(tablero, fila + 1, 0);
            return;
        }
        System.out.print((tablero[fila][col] == 1 ? "Q" : ".") + " ");
        imprimirTablero(tablero, fila, col + 1);
    }
}

