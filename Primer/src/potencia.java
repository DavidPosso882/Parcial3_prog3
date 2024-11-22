package co.edu.uniquindio.taller1;

public class potencia {
    public static void main(String[] args) {
        int numero=2;
        int potencia=3;
        System.out.println(calcularPotenciae(numero,potencia));
    }

    private static int calcularPotenciae(int numero, int potencia) {
        if (potencia==0){
            return 1;
        }else{
            return calcularPotenciae(numero,potencia-1)*numero;
        }
    }
}
