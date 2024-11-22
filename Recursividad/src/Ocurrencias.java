public class Ocurrencias {
    public static int contarOcurrencias(String palabra, char letra, int i) {
        if (i==palabra.length()) {
            return 0;
        }else{
            if (letra==palabra.charAt(i)){
                return 1 + contarOcurrencias(palabra,letra,i+1);
            }
            return contarOcurrencias(palabra,letra,i+1);
        }
    }
}
