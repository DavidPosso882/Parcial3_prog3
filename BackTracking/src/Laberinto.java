public class Laberinto{


    public char[][] laberinto={
            //y
            {'#', ' ', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', '#', ' ', '#', '#', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', ' ', ' ', ' ', '#', '#', '#', '#'},
            {' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' '},
            {'#', '#', '#', '#', '#', ' ', '#', '#', '#', '#'},
            {' ', ' ', ' ', ' ', '#', ' ', '#', ' ', '#', '#'},
            {'#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', ' '},
    };


    /* --------------------- PRUEBA DEL ALGORITMO --------------------- */
    public static void main(String[] args) {
        Laberinto m = new Laberinto(); 												// construimos un objeto de la clase Laberinto por defecto
        m.laberinto[8][1] = 'X'; 													// introducimos en este caso, la salida (X) en las coordenadas (1,1)
        m.laberinto[5][9] = 'X';
        m.laberinto[7][7] = 'X';
        m.laberinto[5][0] = 'X';
        m.resuelve(1, 1);														// ahora, introducimos la entrada (S) en las coordenadas (8,1) y llamamos al algoritmo
        // imprimimos el laberinto ya resuelto (si tiene solución)
    }




    /* ----------------- IMPLEMENTACIÓN DEL ALGORITMO ----------------- */
    public void resuelve(int x, int y){ 				// permite introducir unas coordenadas (x, y)
        if (paso(x, y)) { 								// intentará resolver el laberinto en estas coordenadas
            laberinto[x][y] = 'X'; 						// introduce en las coordenadas (x, y) la entrada
        }
    }

    private boolean paso(int x, int y)
    {
        if (x<0 || y<0 || x > laberinto.length-1 || y > laberinto[0].length-1){
            return false;
        }
        if (laberinto[x][y]=='X'){ // si hemos llegado a X quiere decir que hemos encontrado solución
            System.out.println(imprimirLaberinto(0,0));
            System.out.println("-----final-----");
            return false; // luego, el algoritmo termina
        }


        if (laberinto[x][y]=='#'||laberinto[x][y]=='*' ||laberinto[x][y]=='f') { // si llegamos a una pared o al mismo punto,
            return false; // entonces el laberinto no puede resolverse y termina.
        }

        // si no se cumple ninguna de estas dos situaciones, quiere decir que nos encontramos en un
        // caso intermedio, por lo tanto, que empezamos a recorrer o todavía no hemos llegado a nada
        laberinto[x][y]='*'; // marcamos la posición como visitada (si es la primera, en las coordenadas x e y


         // intentamos ir hacia ARRIBA. Segunda llamada recursiva
        if (paso(x-1, y)) return true; // si el resultado es el final, entonces el algoritmo termina

        // intentamos ir hacia la IZQUIERDA. Tercera llamada recursiva
        if (paso(x, y-1)) return true; // si el resultado es el final, entonces el algoritmo termina

        // intentamos ir hacia la DERECHA. Primera llamada recursiva
        if (paso(x, y+1))return true; // si el resultado es el final, entonces el algoritmo termina

         // intentamos ir hacia ABAJO. Cuarta llamada recursiva
        if (paso(x+1, y)) return true; // si el resultado es el final, entonces el algoritmo termina

        // si no hemos encontrado la solución en estos cuatros movimientos, volvemos atrás, aunque hay que
        // considerar que en este punto, todas las llamadas recursivas han finalizado. Si en ninguna de ellas
        // se ha conseguido el éxito, entonces el algoritmo termina y devuelve false.
        laberinto[x][y]='f'; // en el caso de no ser el resultado, se marca con +. Ya fue pisado
        return false; // vuelta atrás si la solución no se encuentra aquí


    }

    private String imprimirLaberinto(int i, int j) { // imprimiremos nuestra solución. Debido a que la clase Arrays no tiene implementado
        String salida = "";
        if (i == laberinto.length){
            return salida;
        }else {
            salida +=laberinto[i][j] + " ";
            if (j == laberinto[i].length-1){
                j=0;
                i++;
                salida += "\n";
            }else {
                j++;
            }
            return salida + imprimirLaberinto(i,j);
        }
    }

}
