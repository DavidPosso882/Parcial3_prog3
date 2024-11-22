public class Caballo {

    private static final int[] movimientosFila = {-2, -1, 1, 2, 2, 1, -1, -2};
    private static final int[] movimientosColumna = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) {
        int n = 8; // Tamaño del tablero de ajedrez
        int[][] tablero = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tablero[i][j] = -1; // Inicializar el tablero con -1
            }
        }

        // Posición inicial del caballo
        int filaInicial = 0;
        int columnaInicial = 0;
        tablero[filaInicial][columnaInicial] = 0; // El primer movimiento es el 0

        if (resolverCaballo(tablero, filaInicial, columnaInicial, 1)) {
            imprimirTablero(tablero, 0, 0);
        } else {
            System.out.println("No se encontró solución.");
        }
    }

    public static boolean resolverCaballo(int[][] tablero, int fila, int columna, int movimiento) {
        int n = tablero.length;
        if (movimiento == n * n) {
            return true;
        }

        return intentarMovimientos(tablero, fila, columna, movimiento, 0);
    }

    public static boolean intentarMovimientos(int[][] tablero, int fila, int columna, int movimiento, int indiceMovimiento) {
        int n = tablero.length;
        if (indiceMovimiento == movimientosFila.length) {
            return false;
        }

        int nuevaFila = fila + movimientosFila[indiceMovimiento];
        int nuevaColumna = columna + movimientosColumna[indiceMovimiento];

        if (esMovimientoValido(tablero, nuevaFila, nuevaColumna)) {
            tablero[nuevaFila][nuevaColumna] = movimiento;
            if (resolverCaballo(tablero, nuevaFila, nuevaColumna, movimiento + 1)) {
                return true;
            }
            tablero[nuevaFila][nuevaColumna] = -1; // Backtracking
        }

        return intentarMovimientos(tablero, fila, columna, movimiento, indiceMovimiento + 1);
    }

    public static boolean esMovimientoValido(int[][] tablero, int fila, int columna) {
        int n = tablero.length;
        return fila >= 0 && fila < n && columna >= 0 && columna < n && tablero[fila][columna] == -1;
    }

    public static void imprimirTablero(int[][] tablero, int fila, int columna) {
        int n = tablero.length;
        if (fila == n) {
            return;
        }
        if (columna == n) {
            System.out.println();
            imprimirTablero(tablero, fila + 1, 0);
            return;
        }
        System.out.print(tablero[fila][columna] + "\t");
        imprimirTablero(tablero, fila, columna + 1);
    }
}

