public class IdentificarNumero {

    public static String verificarNumero(int numero){
        int div = 1;
        int sumatoria = sumarDivisoresPropios(numero,div);
        String res = "";
        if(sumatoria == numero){
            res = "Es un numero Perfecto";
        }else{
            if(sumatoria < numero){
                res = "Es un numero Defectivo";
            }else{
                res = "Es un numero Abundante";
            }
        }
        return res;
    }
    public static int sumarDivisoresPropios(int numero, int div){
        if(div > (numero/2)){
            return 0;
        }else{
            int aux = 0;
            if(numero % div == 0){
                aux = div;
            }
            return aux + sumarDivisoresPropios(numero, div + 1);
        }
    }
}
