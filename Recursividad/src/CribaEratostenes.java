import java.util.ArrayList;
import java.util.Arrays;

public class CribaEratostenes {
    public static ArrayList<Integer> buscarNumPrimos(int n){
        boolean arr[] = new boolean[n+1];
        Arrays.fill(arr,true);
        generarCriba(arr,n,2);
        ArrayList<Integer> lista = new ArrayList<>();
        for(int pos = 2; pos<arr.length; pos++){
            if(arr[pos] == true){
                lista.add(pos);
            }
        }
        return lista;
    }
    private static void generarCriba(boolean arr[], int limite, int actual){
        if(actual <= (int)Math.sqrt(limite)){
            if(arr[actual] == true){
                marcarMultiplos(arr,limite,actual,2);
            }
            generarCriba(arr,limite,actual+1);
        }
    }
    private static void marcarMultiplos(boolean arr[], int limite, int actual, int mult){
        int multiplo = actual*mult;
        if(multiplo <= limite){
            arr[multiplo] = false;
            marcarMultiplos(arr,limite,actual,mult+1);
        }
    }
}
