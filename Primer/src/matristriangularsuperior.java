package co.edu.uniquindio.taller1;

public class matristriangularsuperior {
    public static void main(String[] args) {
        int [][] matriz={{7,5,3},
                         {2,8,4},
                         {3,5,8}};
       //recorrerMatriz(matriz,0,0);
        triangularsuperior(matriz,0,0);
        triangularinferior(matriz,0,0);
    }
    public static void   triangularsuperior(int[][] matriz, int i, int j) {
        if (i <= matriz.length - 1) {
            if(j <= matriz[i].length-1) {
                if (j >= i){
                    System.out.print(matriz[i][j] + " ");
                }
                if (j== matriz[i].length-1){
                    j=0+i;
                    i++;
                    System.out.println();
                }else {
                    j++;
                    //System.out.print(matriz[i][j] + " ");
                }
                triangularsuperior(matriz,i,j);
            }
        }
    }
    public static void   triangularinferior(int[][] matriz, int i, int j) {
        if (i <= matriz.length - 1) {
            if(j <= matriz[i].length-1) {
                if (j <= i){
                    System.out.print(matriz[i][j] + " ");
                }
                if (j== matriz[i].length-1){
                    j=0;
                    i++;
                    System.out.println();
                }else {
                    j++;
                    //System.out.print(matriz[i][j] + " ");
                }
                triangularinferior(matriz,i,j);
            }
        }
    }

}
