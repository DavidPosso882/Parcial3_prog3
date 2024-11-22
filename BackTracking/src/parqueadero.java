public class parqueadero {
    // Definir la matriz parqueadero
    static char[][] parqueadero = {
            {' ', ' ', 'R', 'L', 'L', 'R', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', 'R', 'L', 'L', 'R', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', 'L', 'L', 'L', 'L', ' ', ' '},
            {' ', ' ', 'R', 'L', 'L', 'R', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', 'R', 'L', 'L', 'R', ' ', ' '}
    };

    // Función de backtracking para parquear los carros
    public static boolean parquearCarros(int fila, int columna, int carroActual) {
        // Verificar si se ha parqueado el segundo carro
        if (carroActual > 2) {
            return true;  // Parqueo completado con éxito
        }
        // Verificar los movimientos posibles: derecha, abajo, arriba, izquierda
        int[][] movimientos = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        for (int[] movimiento : movimientos) {
            int nuevaFila = fila + movimiento[0];
            int nuevaColumna = columna + movimiento[1];
            // Verificar si la nueva posición está dentro del parqueadero
            if (nuevaFila >= 0 && nuevaFila < parqueadero.length && nuevaColumna >= 0 && nuevaColumna < parqueadero[0].length) {
                // Verificar si la nueva posición es un espacio libre o reservado
                if (parqueadero[nuevaFila][nuevaColumna] == 'R' || parqueadero[nuevaFila][nuevaColumna] == 'L') {
                    // Marcar el camino con "+"
                    parqueadero[nuevaFila][nuevaColumna] = '+';
                    // Intentar parquear el siguiente carro en la nueva posición
                    if (parquearCarros(nuevaFila, nuevaColumna, carroActual + 1)) {
                        return true;  // Parqueo completado con éxito
                    }
                    // Retroceder si el parqueo no fue posible
                    parqueadero[nuevaFila][nuevaColumna] = 'L';  // Limpiar el camino
                }
            }
        }
        return false;  // No se pudo completar el parqueo
    }

    public static void main(String[] args) {
        // Iniciar el parqueo desde la posición de entrada
        int entradaFila = 0, entradaColumna = 0;  // Suponiendo que la entrada está en la posición (0, 0)
        parqueadero[entradaFila][entradaColumna] = '+';  // Marcar la posición de entrada
        if (parquearCarros(entradaFila, entradaColumna, 1)) {
            System.out.println("Parqueo completado con éxito:");
            for (char[] fila : parqueadero) {
                for (char c : fila) {
                    System.out.print(c + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No se pudo completar el parqueo");
        }
    }
}
