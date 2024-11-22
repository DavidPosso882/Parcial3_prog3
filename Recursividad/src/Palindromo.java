public class Palindromo {
    public static String verificarPalindromo(String cad){
        cad = normalizarCadena(cad);
        int izq = 0;
        int der = cad.length()-1;
        return verificarPalindromo(cad,izq,der);
    }

    public static String verificarPalindromo(String cad, int izq, int der){
        String res;
        if(der<izq){
            res = "Es Palindromo";
        }else{
            char actIzq = cad.charAt(izq);
            char actDer = cad.charAt(der);
            if(actIzq != actDer){
                res = "No es Palindromo";
            }else{
                res = verificarPalindromo(cad, izq+1, der-1);
            }
        }
        return res;
    }

    public static String normalizarCadena(String cad){
        String res = "";
        for(int pos=0; pos<cad.length(); pos++){
            char actual = cad.charAt(pos);
            if(actual != ' ' && actual != ','){
                res = res + actual;
            }
        }
        return res.toLowerCase();
    }
}
