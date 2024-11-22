import java.util.Arrays;

public class OrdenarArrayMezcla {

   /* public static int[] ordenarArray(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        return mergeSort(arr);
    }

    private static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        int middle = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);

        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left, right);
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        while (i < left.length) {
            result[k++] = left[i++];
        }
        while (j < right.length) {
            result[k++] = right[j++];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] myArray = {5, 3, 8, 4, 2};
        int[] sortedArray = ordenarArray(myArray);
        for (int num : sortedArray) {
            System.out.println(num);
        }
    } */

    public static int[] ordenarArray(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        return mergeSort(arr);
    }

    private static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }

        int middle = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        mergeRecursive(left, right, result, 0, 0, 0);
        return result;
    }

    private static void mergeRecursive(int[] left, int[] right, int[] result, int i, int j, int k) {
        if (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k] = left[i];
                mergeRecursive(left, right, result, i + 1, j, k + 1);
            } else {
                result[k] = right[j];
                mergeRecursive(left, right, result, i, j + 1, k + 1);
            }
        } else if (i < left.length) {
            result[k] = left[i];
            mergeRecursive(left, right, result, i + 1, j, k + 1);
        } else if (j < right.length) {
            result[k] = right[j];
            mergeRecursive(left, right, result, i, j + 1, k + 1);
        }
    }

    public static void main(String[] args) {
        int[] myArray = {5, 3, 8, 4, 2};
        int[] sortedArray = ordenarArray(myArray);
        for (int num : sortedArray) {
            System.out.println(num);
        }
    }
}
