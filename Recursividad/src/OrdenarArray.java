public class OrdenarArray {

    public static int[] ordenarArray(int[] array) {
        return ordenarArray(array, 0, 0);
    }

    private static int[] ordenarArray(int[] array, int i, int j) {
        if (j <= array.length - 1) {
            if (i < array.length - j - 1) {
                if (array[i] > array[i + 1]) {
                    int aux = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = aux;
                }
                return ordenarArray(array, i + 1, j);
            } else {
                i = 0;
                return ordenarArray(array, i, j + 1);
            }
        } else {
            return array;
        }
    }
}
