public class Fibonacci {

    public static int fibonacciRecursivo(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacciRecursivo(n - 1) + fibonacciRecursivo(n - 2);
        }
    }
}

