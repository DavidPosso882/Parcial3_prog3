public class backtracking {
    public static int soluciones =0;

    //metodo de inicio
    public static void main(String[] args)
    {
        int laberinto[][] =
                {
                        { 0, 0, 0, 0, 0, 1, 0, 0},
                        { 0, 1, 1, 0, 1, 1, 0, 1},
                        { 0, 0, 1, 0, 0, 0, 0, 0},
                        { 1, 0, 0, 0, 0, 0, 0, 0},
                        { 1, 0, 0, 0, 0, 0, 0, 0},
                        { 0, 0, 0, 0, 0, 0, 0, 0},
                        { 1, 0, 1, 0, 1, 1, 0, 0},
                };

        String solucion[][] =
                {
                        { "*", "*", "*", "*", "*", "*", "*", "*"},
                        { "*", "*", "*", "*", "*", "*", "*", "*"},
                        { "*", "*", "*", "*", "*", "*", "*", "*"},
                        { "*", "*", "*", "*", "*", "*", "*", "*"},
                        { "*", "*", "*", "*", "*", "*", "*", "*"},
                        { "*", "*", "*", "*", "*", "*", "*", "*"},
                        { "*", "*", "*", "*", "*", "*", "*", "*"},
                };

        //llamado a nuestro metodo de backtracking
        resolverLaberinto(laberinto, solucion, 0, 0);
        System.out.println(soluciones);
    }

    public static boolean resolverLaberinto(int[][] laberinto, String[][] solucion, int x, int y) {
        //caso base
        if (x == laberinto.length - 1 && y == laberinto[0].length - 1 && laberinto[x][y] == 0) {
            //al encontrar la solucion, la imprime.
            soluciones++;
            solucion[x][y] = "S";
            imprimirSolucion(solucion, laberinto);
            return true;
        }
        //validacion de casilla
        if (esCasillaValida(laberinto, x, y, solucion)) {

            // Moverse hacia la izquierda
            solucion[x][y] = "←";
            if (resolverLaberinto(laberinto, solucion, x, y - 1))
            {
                return true;
            }

            // Moverse hacia abajo
            solucion[x][y] = "↓";
            if (resolverLaberinto(laberinto, solucion, x + 1, y))
            {
                return true;
            }

            // Moverse hacia la derecha
            solucion[x][y] = "→";
            if (resolverLaberinto(laberinto, solucion, x, y + 1))
            {
                return true;
            }

            // Moverse hacia arriba
            solucion[x][y] = "↑";
            if (resolverLaberinto(laberinto, solucion, x - 1, y))
            {
                return true;
            }

            //Desmarcar para ir un paso atrás e intentar otra solucion.
            solucion[x][y] = "*";
            return false;
        }

        return false;
    }

    //valida que la casilla sea válida.
    public static boolean esCasillaValida(int[][] laberinto, int x, int y, String[][] solucion) {
        return (x >= 0 && x < laberinto.length && y >= 0 && y < laberinto[0].length && laberinto[x][y] == 0 && solucion[x][y].equals("*"));
    }

    //imprime la matriz solucion sobre la matriz de laberinto
    public static void imprimirSolucion(String[][] solucion, int[][] laberinto){
        System.out.println();
        System.out.println("Solucion hallada #"+soluciones);


        for(int i = 0 ;i< solucion.length; i++){
            for(int j = 0 ;j< solucion[i].length; j++){
                if(laberinto[i][j]==1){
                    System.out.print("X ");
                }else{
                    System.out.print(solucion[i][j] + " ");

                }
            }
            System.out.println();
        }

    }

}

