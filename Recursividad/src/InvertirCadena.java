public class InvertirCadena {

    public static String invertirCadena(String palabra) {
        return invertirCadena(palabra, "", palabra.length()-1);
    }

    private static String invertirCadena(String palabra, String palabraInvertida, int i) {
        if (i < 0) {
            return palabraInvertida;
        } else {
            palabraInvertida += palabra.charAt(i);
            return invertirCadena(palabra, palabraInvertida, i - 1);
        }
    }

}
