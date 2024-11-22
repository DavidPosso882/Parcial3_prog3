import java.util.Arrays;

public class BusquedaBinaria {

    public static int buscarElemento(int[] arr, int buscado) {
        Arrays.sort(arr);
        System.out.println("Array ordenado: " + Arrays.toString(arr));
        int li = 0;
        int ls = arr.length - 1;
        return busquedaBinaria(arr, buscado, li, ls);
    }

    private static int busquedaBinaria(int[] arr, int buscado, int li, int ls) {
        if (li <= ls) {
            int medio = (li + ls) / 2;
            if (arr[medio] == buscado) {
                return medio;
            } else if (arr[medio] < buscado) {
                return busquedaBinaria(arr, buscado, medio + 1, ls);
            } else {
                return busquedaBinaria(arr, buscado, li, medio - 1);
            }
        } else {
            return -1;
        }
    }
}
