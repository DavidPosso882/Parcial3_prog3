public class Sudoku {

    public static void main(String[] args) {
        int[][] tablero = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        if (resolverSudoku(tablero, 0, 0)) {
            imprimirTablero(tablero, 0, 0);
        } else {
            System.out.println("No se encontró solución.");
        }
    }

    public static boolean resolverSudoku(int[][] tablero, int fila, int col) {
        int n = tablero.length;
        if (fila == n) {
            return true;
        }
        if (col == n) {
            return resolverSudoku(tablero, fila + 1, 0);
        }
        if (tablero[fila][col] != 0) {
            return resolverSudoku(tablero, fila, col + 1);
        }

        return intentarNumeros(tablero, fila, col, 1);
    }

    public static boolean intentarNumeros(int[][] tablero, int fila, int col, int num) {
        int n = tablero.length;
        if (num > n) {
            return false;
        }

        if (esSeguro(tablero, fila, col, num)) {
            tablero[fila][col] = num;
            if (resolverSudoku(tablero, fila, col + 1)) {
                return true;
            }
            tablero[fila][col] = 0; // Backtracking
        }

        return intentarNumeros(tablero, fila, col, num + 1);
    }

    public static boolean esSeguro(int[][] tablero, int fila, int col, int num) {
        return verificarFila(tablero, fila, col, num) &&
                verificarColumna(tablero, fila, col, num) &&
                verificarCaja(tablero, fila, col, num);
    }

    public static boolean verificarFila(int[][] tablero, int fila, int col, int num) {
        if (col == 0) {
            return true;
        }
        if (tablero[fila][col - 1] == num) {
            return false;
        }
        return verificarFila(tablero, fila, col - 1, num);
    }

    public static boolean verificarColumna(int[][] tablero, int fila, int col, int num) {
        if (fila == 0) {
            return true;
        }
        if (tablero[fila - 1][col] == num) {
            return false;
        }
        return verificarColumna(tablero, fila - 1, col, num);
    }

    public static boolean verificarCaja(int[][] tablero, int fila, int col, int num) {
        int cajaFilaInicio = fila - fila % 3;
        int cajaColInicio = col - col % 3;
        return verificarCajaRecursiva(tablero, cajaFilaInicio, cajaColInicio, num, 0, 0);
    }

    public static boolean verificarCajaRecursiva(int[][] tablero, int filaInicio, int colInicio, int num, int i, int j) {
        if (i == 3) {
            return true;
        }
        if (j == 3) {
            return verificarCajaRecursiva(tablero, filaInicio, colInicio, num, i + 1, 0);
        }
        if (tablero[filaInicio + i][colInicio + j] == num) {
            return false;
        }
        return verificarCajaRecursiva(tablero, filaInicio, colInicio, num, i, j + 1);
    }

    public static void imprimirTablero(int[][] tablero, int fila, int col) {
        int n = tablero.length;
        if (fila == n) {
            return;
        }
        if (col == n) {
            System.out.println();
            imprimirTablero(tablero, fila + 1, 0);
            return;
        }
        System.out.print(tablero[fila][col] + " ");
        imprimirTablero(tablero, fila, col + 1);
    }
}

