public class NumeroPar {
    public static boolean decidirPar(int num) {
        boolean esPar = true;
        if(num>0){
            esPar = decidirimPar(num-1);
        }
        return esPar;
    }
    public static boolean decidirimPar(int num){
        boolean esimPar = false;
        if(num>0){
            esimPar = decidirPar(num-1);
        }
        return esimPar;
    }
}
