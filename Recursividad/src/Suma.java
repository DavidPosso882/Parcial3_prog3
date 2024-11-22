public class Suma {
    public static int sumarRecursivamente(int n) {
        if (n == 1) {
            return n;
        }
        else{
            return n + sumarRecursivamente(n - 1);
        }
    }
}
