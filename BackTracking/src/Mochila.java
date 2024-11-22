public class Mochila {

    public static void main(String[] args) {
        int[] pesos = {2, 3, 4, 5};
        int[] valores = {3, 4, 5, 6};
        int capacidad = 5;

        int maxValor = resolverMochila(pesos, valores, capacidad, 0);
        System.out.println("El valor máximo que se puede obtener es: " + maxValor);
    }

    public static int resolverMochila(int[] pesos, int[] valores, int capacidad, int indice) {
        if (indice == pesos.length) {
            return 0;
        }

        if (pesos[indice] > capacidad) {
            return resolverMochila(pesos, valores, capacidad, indice + 1);
        } else {
            int incluir = valores[indice] + resolverMochila(pesos, valores, capacidad - pesos[indice], indice + 1);
            int excluir = resolverMochila(pesos, valores, capacidad, indice + 1);
            return Math.max(incluir, excluir);
        }
    }
}

