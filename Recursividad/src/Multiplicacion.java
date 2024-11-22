public class Multiplicacion {

    public static int multiplicarRecursivamente(int a, int b) {
        if(b == 0){
            return b;
        }
        else{
            return a + multiplicarRecursivamente(a,b - 1);
        }
    }
}
