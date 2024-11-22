package co.edu.uniquindio.taller1;

public class numerosPrimos{
    public static void main(String[] args) {
        int numero=11;
        int i=2;
        int cont=numeroprimo(numero,i);
        if (cont == 0){
            System.out.println("El "+numero+" Es un numero primo");
        }else {
            System.out.println("El "+numero+" No es un numero primo");
        }
    }
    public static int numeroprimo(int numero, int i){
        if (i >= numero/2){
            return 0;
        }else{
            if (numero %i ==0){
                return numeroprimo(numero,i+1)+1;
            }else {
                return numeroprimo(numero,i+1);
            }
        }
    }
}
