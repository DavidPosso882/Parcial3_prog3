public class Prision {

    public int contarPresos(char[][] carcel) {
        return contarPresosRecursivo(carcel, 0, 0, new boolean[carcel.length][carcel[0].length]);
    }

    private int contarPresosRecursivo(char[][] carcel, int fila, int col, boolean[][] visitado) {
        if (fila < 0 || fila >= carcel.length || col < 0 || col >= carcel[fila].length || visitado[fila][col]) {
            return 0;
        }
        visitado[fila][col] = true;
        int cont = 0;
        if (carcel[fila][col] == 'P') {
            cont = 1;
        }

        int derecha = contarPresosRecursivo(carcel, fila, col + 1, visitado);

        int abajo = contarPresosRecursivo(carcel, fila + 1, col, visitado);

        int arriba = contarPresosRecursivo(carcel, fila - 1, col, visitado);

        int izquierda = contarPresosRecursivo(carcel, fila, col - 1, visitado);

        return cont + derecha + abajo + arriba + izquierda;
    }

    public static void main(String[] args) {
        char[][] carcel = {
                {' ', 'P', 'X', 'P', 'P', 'P', ' ', 'P'},
                {' ', 'P', 'P', ' ', 'P', 'P', ' ', 'P'},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'P', ' ', 'P', 'P', ' ', 'P', 'P', ' '},
                {'P', ' ', 'X', 'P', ' ', 'P', 'P', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', 'X', 'P', ' ', 'P', 'P', 'P', 'P'}
        };

        Prision prision = new Prision();
        int totalPresos = prision.contarPresos(carcel);
        int presosEsperados = 26;
        int presosEscapados = presosEsperados - totalPresos;

        System.out.println("Total de presos contados: " + totalPresos);
        if (presosEscapados > 0) {
            System.out.println("Se han escapado " + presosEscapados + " presos.");
        } else {
            System.out.println("No se han escapado presos.");
        }
    }
}



