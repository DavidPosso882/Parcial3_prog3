import java.util.ArrayList;

public class VocalesSeguidas {
    public static ArrayList<String> palabras = new ArrayList<>();
    public static String[][] matriz ={{"vacio","carro"},{"Colombia","casa"}};
    public static void main(String[] args) {
        recorresMatriz(0,0);
        for (int i=0; i < palabras.size();i++){
            System.out.print(palabras.get(i));
        }
    }
    public static boolean recorresMatriz(int i, int j){
        if (i> matriz.length-1){
            return false;
        }else {
            int contador=recorrerPalabra(matriz[i][j],0);
            System.out.print(matriz[i][j] + " tiene mas dos vocales juntas: " + contador);
            System.out.println();
            if (contador>=1){
                palabras.add(matriz[i][j] + " ");
            }
            if (j == matriz[i].length-1){
                i++;
                j=0;
            }else{
                j++;
            }
            return recorresMatriz(i,j);
        }
    }
    public static Integer recorrerPalabra(String palabra,int i){
        if (i > palabra.length()-1){
            return 0;
        }else{
            if (i>0){
                if (isVocal(palabra.charAt(i-1)) && isVocal(palabra.charAt(i))){
                    return recorrerPalabra(palabra,i+1)+1;
                }else{
                    return recorrerPalabra(palabra,i+1);
                }
            }else{
                return recorrerPalabra(palabra,i+1);
            }
        }
    }

    private static boolean isVocal(char c) {
        if (c == 'a' ||c == 'A' ||c == 'e' ||c == 'E' ||
                c == 'i' ||c == 'I' ||c == 'o' ||c == 'O'
                ||c == 'u' ||c == 'U'){
            return true;
        }else {
            return false;
        }
    }
}
