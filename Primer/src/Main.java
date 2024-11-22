import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        //int num1 = 1001;
        //int num2 = 10;
        //int m = 3;
        //int n = 5;
        //System.out.println(sumarNums(num2));
        //System.out.println(contarDigitos(num1));
        //System.out.println(sumarNaM(m,n));
        //moverHanoi(3,'A','B','C');

        ArrayList<Object> listaDuplicados = new ArrayList<>();
        listaDuplicados.add("Daniela"); listaDuplicados.add(20); listaDuplicados.add("Daniela");
        listaDuplicados.add(30); listaDuplicados.add(20); listaDuplicados.add("David");
        listaDuplicados.add("David"); listaDuplicados.add("Camilo"); listaDuplicados.add("Daniela");

        ArrayList<Object> listaNoDupicados = new ArrayList<>();
        System.out.println(eliminarDuplicados(listaDuplicados, 0, listaNoDupicados));

    }
    public static ArrayList<Object> eliminarDuplicados(ArrayList<Object> listaDuplicados, int i,
                                                       ArrayList<Object> listaNoDuplicados){
        if(i == listaDuplicados.size()){
            return listaNoDuplicados;
        }
        // Object elemento = listaDuplicados.get(i);
        if(!listaNoDuplicados.contains(listaDuplicados.get(i))){
            listaNoDuplicados.add(listaDuplicados.get(i));
        }

            return eliminarDuplicados(listaDuplicados, i + 1, listaNoDuplicados);
        }

        /*
        *  Prueba de escritorio de la eliminación de elementos repetidos de una lista
        *
        *  Nombre           Caso Base         i         Retorno
        *   E(0)              False           0          E(0+1)
        *   E(1)              False           1          E(1+1)
        *   E(2)              False           2          E(2+1)
        *   E(3)              False           3          E(3+1)
        *   E(4)              False           4          E(4+1)
         *  E(5)              False           5          E(5+1)
         *  E(6)              False           6          E(6+1)
         *  E(7)              False           7          E(7+1)
         *  E(8)              False           8          E(8+1)
         *  E(9)              True            9          ArrayList
         *
         */



        /* static  int sumarNums(int num2){
        if(num2 == 0){
            return 0;
        }
        else {
            return sumarNums(num2 - 1) + num2;
        }
    }

    public static int contarDigitos(int num1){
        if(num1/10 == 0){
            return 1;
        }
        else{
            return contarDigitos(num1/10) + 1;
        }
    }

    // Sumar numeros de m y n

    public static int sumarNaM(int m, int n){
        if(n == m){
            return n;
        }
        else{
            return sumarNaM(m, n-1) + n ;
        }
    }

    /* Nombre            caso base            n            retorno
    *   S(5)               falso              5            5 + S(4)
    *   S(4)               falso              4            4 + S(3)
    *   S(3)               falso              3            3 + S(2)
    *   S(2)               falso              2            2 + S(1)
    *   S(1)               verdadero          1               1
    *
    *
    *  Torres de Hanoi:    Caso base H(n-1) - Origen ----> Destino
    *                      */

    /* public static void moverHanoi(int n, char origen, char auxiliar, char destino){
        // Caso base
        if(n == 1){
            System.out.println("Mover disco 1 de la varilla " + origen + " a la varilla " + destino);
            return;
        }
        moverHanoi(n-1, origen, destino, auxiliar);
        System.out.println("Mover disco "+ n + " de la varilla "+ origen + " a la varilla " + destino);
        moverHanoi(n-1, auxiliar, origen, destino);
    }

    Array ----> Declaración y asignación de tamaño
                int[] numeros = new int[5];

                Declaración e inicialización con valores
                int[] otrosNumeros = {1, 2, 3, 4, 5};

                int primerNumero = numeros[0]; Accede al primer elemento
                numeros[1] = 10; Asigna el valor 10 al segundo elemento

                System.out.println(Arrays.toString(otrosNumeros)); Imprime [1, 2, 3, 4, 5]

                Arrays.sort(otrosNumeros); Ordena los elementos

                int index = Arrays.binarySearch(otrosNumeros, 3); Devuelve el índice del elemento 3

                int[] copia = Arrays.copyOf(otrosNumeros, 10);  Crea una copia con longitud 10

                boolean sonIguales = Arrays.equals(numeros, otrosNumeros);

                Iteración
                for (int i = 0; i < numeros.length; i++) {
                        System.out.println(numeros[i]);
                }

    Array bidimensional ---->   Declaración de un array bidimensional
                                int[][] matriz = new int[3][4];

                                 Declaración e inicialización con valores
                                 int[][] matrizValores = {
                                    {1, 2, 3, 4},
                                    {5, 6, 7, 8},
                                    {9, 10, 11, 12}
                                 };

                                 int valor = matriz[1][2]; Accede al elemento en la fila 1, columna 2
                                 matriz[0][0] = 7; Asigna el valor 7 al primer elemento

                                 Recorrer
                                 for (int i = 0; i < matriz.length; i++) {
                                        for (int j = 0; j < matriz[i].length; j++) {
                                        System.out.print(matriz[i][j] + " ");
                                        }
                                      System.out.println();
                                  }

    Strings ----> int longitud = saludo.length(); Devuelve 12
                    char caracter = saludo.charAt(0); Devuelve 'H'
                    String subcadena = saludo.substring(0, 4); Devuelve "Hola"
                    boolean esIgual = saludo.equals(otroSaludo); Devuelve true
                    boolean esIgualIgnorandoCaso = saludo.equalsIgnoreCase("hola, mundo!"); Devuelve true (ignora mayucculas y min)
                    String mayusculas = saludo.toUpperCase(); // Devuelve "HOLA, MUNDO!"
                    String minusculas = saludo.toLowerCase(); // Devuelve "hola, mundo!"
                    String conEspacios = "  Hola, mundo!  ";
                    String sinEspacios = conEspacios.trim(); // Devuelve "Hola, mundo!"
                    String reemplazada = saludo.replace('o', 'a'); // Devuelve "Hala, munda!"
                    String[] palabras = saludo.split(" "); // Devuelve ["Hola,", "mundo!"]



        */
}



