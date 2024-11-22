public class Carcel {

    public static void recorrerCarcel(char[][] carcel, int x, int y, int[] p, int filas, int columnas) {
        if (esvalido(carcel, x, y, filas, columnas)) {
            if (carcel[x][y] == 'P') {
                p[0]++;
            }
            carcel[x][y] = 'R'; // Marca la celda como visitada

            // Derecha
            recorrerCarcel(carcel, x, y + 1, p, filas, columnas);
            // Abajo
            recorrerCarcel(carcel, x + 1, y, p, filas, columnas);
            // Arriba
            recorrerCarcel(carcel, x - 1, y, p, filas, columnas);
            // Izquierda
            recorrerCarcel(carcel, x, y - 1, p, filas, columnas);
        }
    }

    public static boolean esvalido(char[][] carcel, int x, int y, int filas, int columnas) {
        return x >= 0 && x < filas && y >= 0 && y < columnas && carcel[x][y] != 'R' && carcel[x][y] != 'X';
    }

    public static void main(String[] args) {
        char[][] carcel = {
                {' ', 'P', 'X', ' ', 'P', 'P', ' ', 'P'},
                {' ', 'P', 'P', ' ', 'P', 'P', ' ', 'P'},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'P', ' ', 'P', 'P', ' ', 'P', 'P', ' '},
                {'P', ' ', 'X', 'P', ' ', 'P', 'P', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', 'X', 'P', ' ', 'P', 'P', ' ', 'P'}
        };

        int[] p = {0};
        recorrerCarcel(carcel, 0, 0, p, carcel.length, carcel[0].length);

        int presosEsperados = 26;
        int presosEscapados = presosEsperados - p[0];

        System.out.println("Total de presos contados: " + p[0]);
        if (presosEscapados > 0) {
            System.out.println("Se han escapado " + presosEscapados + " presos.");
        } else {
            System.out.println("No se han escapado presos.");
        }
    }
}



