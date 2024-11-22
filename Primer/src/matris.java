package co.edu.uniquindio.taller1;

public class matris {
    public static void main(String[] args) {
        int [][] matriz={{7,5,3},
                         {2,8,4},
                         {3,5,8}};
       int diagonal=sumatoriadiagonalprincipal(matriz,0,0);
       System.out.println(diagonal);
    }
    public static int  sumatoriadiagonalprincipal(int[][] matris, int i, int j) {
        int suma=0;
        if (i == matris.length - 1) {
            return 0;
        } else {
            if (j==i){
                suma=matris[i][j];
            }
            if (j == matris[i].length - 1) {
                i++;
                j = 0;
            } else {
                j++;
            }
            return sumatoriadiagonalprincipal(matris, i, j)+suma;
        }
    }
}
