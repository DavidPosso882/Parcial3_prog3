public class Trayectoria {
   public int buscarSenderoLargo(char[][] map) {
        return buscarSenderoLargoRecursivo(map, 0, 0, 0);
    }

    private int buscarSenderoLargoRecursivo(char[][] map, int fila, int col, int res) {
        if (fila >= map.length) {
            return res;
        }
        if (col >= map[fila].length) {
            return buscarSenderoLargoRecursivo(map, fila + 1, 0, res);
        }
        if (map[fila][col] == 'U') {
            int parcial = contarCamino(fila, col, map);
            if (parcial > res) {
                res = parcial;
            }
        }
        return buscarSenderoLargoRecursivo(map, fila, col + 1, res);
    }

    private int contarCamino(int fila, int col, char[][] map){
        int res;
        if(esValido(fila,col,map) && map[fila][col] == 'U'){
            map[fila][col] = 'X';
            int der = 1+contarCamino(fila,col+1,map);
            int izq = 1+contarCamino(fila,col-1,map);
            int arriba = 1+contarCamino(fila-1, col, map);
            int abajo  = 1+contarCamino(fila+1, col, map);

            int m1 = Math.max(der,izq);
            int m2 = Math.max(arriba,abajo);

            res = Math.max(m1,m2);
            map[fila][col] = 'U';
        }else{
            res = 0;
        }
        return res;
    }

    private boolean esValido(int fila, int col, char[][] map){
        return fila>=0 && fila<map.length && col>=0 && col<map[fila].length;
    }

    public static void main(String args[]){
        char[][] map = {{'B','B','B','B','B','B','B','B'},
                        {'B','U','U','U','U','U','B','B'},
                        {'B','U','B','B','B','U','U','U'},
                        {'B','B','B','U','U','U','B','B'},
                        {'B','B','B','U','B','U','B','B'},
                        {'B','B','U','U','B','U','U','B'},
                        {'B','B','B','B','B','U','B','B'}};

        Trayectoria trayectoria = new Trayectoria();
        System.out.println("trayectoria mayor: " + trayectoria.buscarSenderoLargo(map));
    }
}
