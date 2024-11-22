public class Division {
    public static int dividirRecursivamente(int a, int b) {
        if (a < b){
            return 0;
        } else{
            return dividirRecursivamente(a-b, b) + 1; // Fijarse especialmente en el caso recursivo
        }
    }

}
