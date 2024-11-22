public class Jardin {
    public int contarSectores(char[][] map) {
        return contarSectoresRecursivo(map, 0, 0);
    }

    private int contarSectoresRecursivo(char[][] map, int fila, int col) {
        if (fila >= map.length) {
            return 0;
        }
        if (col >= map[fila].length) {
            return contarSectoresRecursivo(map, fila + 1, 0);
        }
        int cont = 0;
        if (map[fila][col] == ' ') {
            regar(fila, col, map);
            cont = 1;
        }
        return cont + contarSectoresRecursivo(map, fila, col + 1);
    }

    private void regar(int fila, int col, char[][] map){
        if(esValida(fila,col,map) && map[fila][col] == ' '){
            map[fila][col] = 'R';
            regar(fila, col+1, map);//derecha
            regar(fila, col-1, map);//izquierda
            regar(fila-1, col, map);//arriba
            regar(fila+1, col, map);//abajo
        }
    }

    private boolean esValida(int fila, int col, char[][]map){
        return fila>=0 && fila<map.length && col>=0 && col<map[fila].length;
    }

    private void imprimirMatriz(char[][] map) {
        for (int fila = 0; fila < map.length; fila++) {
            for (int col = 0; col < map[fila].length; col++) {
                System.out.print(map[fila][col] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String args[]){
        char[][] map = {{' ',' ',' ','W',' ',' '},
                        {' ','W','W','W','W',' '},
                        {' ','W',' ',' ','W',' '},
                        {'W','W','W',' ','W',' '},
                        {' ',' ','W',' ','W','W'},
                        {' ',' ','W',' ',' ',' '}};
        Jardin jardin = new Jardin();

        System.out.println("sectores contados: " + jardin.contarSectores(map));
        System.out.println("Matriz final:");
        jardin.imprimirMatriz(map);
    }
}
