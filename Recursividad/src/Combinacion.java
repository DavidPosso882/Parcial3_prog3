import java.util.ArrayList;

public class Combinacion {

    public static ArrayList<ArrayList<Integer>> buscarCombinaciones(int arr[], int x){

        boolean[] utilizados = new boolean[arr.length];
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        buscarCombinaciones(arr, x, utilizados, 0 , res);
        return res;
    }
    private static void buscarCombinaciones(int arr[], int x, boolean[] utilizados, int posAct, ArrayList<ArrayList<Integer>> res){
        if(x == 0){
            ArrayList<Integer> combinacion = new ArrayList<>();
            for(int i=0; i<arr.length; i++){
                if(utilizados[i] == true){
                    combinacion.add(arr[i]);
                }
            }
            res.add(combinacion);
        }else{
            for(int pos = posAct; pos<arr.length; pos++){
                utilizados[pos] = true;
                buscarCombinaciones(arr,x-1, utilizados, pos+1, res);
                utilizados[pos] = false;
            }
        }
    }
}
