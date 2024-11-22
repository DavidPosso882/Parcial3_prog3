public class ParqueaderoBacktraking {
    
    // Matriz del parqueadero
    static String[][] parqueadero = {
        {"L", " ", "L", "C", "R1", "L", " "},
        {"L", " ", "L", "C", " ", "L", " "},
        {" ", " ", " ", "C", " ", " ", " "},
        {"C", " ", " ", "C", "D", "D", " "},
        {"C", " ", " ", " ", " ", "L", " "},
        {"C", " ", "C", " ", " ", "L", " "},
        {"C", " ", "C", " ", "C", "C", " "},
        {" ", " ", "C", " ", " ", " ", " "},
        {"C", " ", "C", " ", "C", " ", "C"},
        {"C", " ", "R", " ", "C", " ", "R"},
        {" ", " ", "R", " ", "C", "C", "R"}, 
        {"E", " ", "R", " ", " ", " ", "R2"}
    };
    //Matriz solucion
    static int[][] solucion = new int[parqueadero.length][parqueadero[0].length];
    // Dimensiones del parqueadero
    static int filas = parqueadero.length;
    static int columnas = parqueadero[0].length;


    public static void main(String[] args) {

        // Imprime el parqueadero
        System.out.println("========================================");
        imprimirMatriz(parqueadero);
        System.out.println("========================================");


        // Encontrar la posición de entrada
        int[] entrada = encontrarPosicion("E");
        if (entrada == null) {
            System.out.println("No se encontró la posición de entrada.");
            return;
        }

        // Intenta estacionar el primer carro en R1
        int[] r1_pos = encontrarPosicion("R1"); //se encuentra cual es la posicion de la reserva
        if (r1_pos != null && estacionarCarro(solucion, entrada[0], entrada[1], r1_pos)) { // comprueba si hay reserva y si se puede estacionar el carro
            System.out.println("Carro estacionado en R1");
            parqueadero[r1_pos[0]][r1_pos[1]] = "C"; // si encontro la reserva y se estaciono coloca un carro ahi en el parqueadero
        } else {
            System.out.println("No se pudo estacionar en R1");
        }

        // Imprime la matriz solucion que seria el camino que tomo el carro
        System.out.println("========================================");
        imprimirMatrizS(solucion);
        System.out.println("========================================");

        // Limpiar la ruta del primer carro en este caso la matriz solucion
        limpiarRuta();

        // Imprime el parqueadero con el carro estacionado
        System.out.println("========================================");
        imprimirMatriz(parqueadero);
        System.out.println("========================================");


        // Intenta estacionar el segundo carro en R2
        int[] r2_pos = encontrarPosicion("R2");
        if (r2_pos != null && estacionarCarro(solucion, entrada[0], entrada[1], r2_pos)) {
            System.out.println("Carro estacionado en R2");
            parqueadero[r2_pos[0]][r2_pos[1]] = "C";
        } else {
            System.out.println("No se pudo estacionar en R2");
        }

        //Imprime la matriz solucion que seria el camino que tomo el carro
        System.out.println("========================================");
        imprimirMatrizS(solucion);
        System.out.println("========================================");

        // Imprime el parqueadero con el carro estacionado
        System.out.println("========================================");
        imprimirMatriz(parqueadero);
        System.out.println("========================================");
        
    }

    // Función para encontrar la posición de un elemento en la matriz
    public static int[] encontrarPosicion(String elemento) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (parqueadero[i][j].equals(elemento)) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    // Backtracking para intentar estacionar un carro
    public static boolean estacionarCarro(int[][] solucion, int x, int y, int[]posición) {

        // recurso de parada es cuando llegamos a la posicion de la reserva
        if (x==posición[0] && y==posición[1]) {
            solucion[x][y] = 1;
            return true;
        }
        
        if (isSafe(x, y, solucion)) {
            solucion[x][y] = 1;
             // Moverse hacia la izquierda
            if (estacionarCarro(solucion, x, y - 1, posición)) {
                solucion[x][y] = 1;
                return true;
            }
            
            // Moverse hacia abajo
            if (estacionarCarro(solucion, x + 1, y, posición)) {
                solucion[x][y] = 1;
                return true;
            }
            // Moverse hacia derecha
            if (estacionarCarro(solucion, x, y + 1, posición)) {
                solucion[x][y] = 1;
                return true;
            }
            
            // Moverse hacia arriba
            if (estacionarCarro(solucion, x - 1, y, posición)) {
                solucion[x][y] = 1;
                return true;
            } 
            solucion[x][y] = 0;
            return false;
        }

        return false;
    }
    public static boolean isSafe(int x, int y, int[][] solucion) {
        return (x >= 0 && x < filas && y >= 0 && y < columnas && (parqueadero[x][y].equals(" ") ||  parqueadero[x][y].equals("E")) && !parqueadero[x][y].equals("L") && !parqueadero[x][y].equals("R") && !parqueadero[x][y].equals("C") && !parqueadero[x][y].equals("D") && solucion[x][y]!=1);
    }

    // Función para limpiar las marcas de la ruta anterior
    public static void limpiarRuta() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (solucion[i][j]==1) {
                    solucion[i][j] = 0;
                }
            }
        }
    }
    public static void imprimirMatriz(String[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                // Verifica si el valor en la matriz es un espacio vacío
                if (matriz[i][j].equals(" ")) {
                    System.out.print("=" + " ");  // Imprime "p" en lugar de " "
                } else {
                    System.out.print(matriz[i][j] + " ");  // Imprime el valor original
                }
            }
            System.out.println();  // Salta a la siguiente línea después de imprimir una fila
        }
    }
    public static void imprimirMatrizS(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if(matriz[i][j]==1){
                    System.out.print("+" + " ");
                }
                else{
                    System.out.print(matriz[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    
    
}
