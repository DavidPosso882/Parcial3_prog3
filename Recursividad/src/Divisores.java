public class Divisores {

    public static int contarDivisores(int num, int cont) {
        if (cont > num) {
            return 0;
        } else {
            if (num % cont == 0) {
                return 1 + contarDivisores(num, cont + 1);
            } else {
                return contarDivisores(num, cont + 1);
            }
        }
    }

}
