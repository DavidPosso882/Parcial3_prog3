public class VerficarCaracteres {
    public static boolean verificarCadena(String cad){
        int pos = 0;
        String aux = "";
        return verificarCadena(cad.toLowerCase(),pos,aux);
    }
    private static boolean verificarCadena(String cad, int pos, String aux){
        boolean res;
        if(pos == cad.length()){
            res = true;
        }else{
            char actual = cad.charAt(pos);
            if(aux.contains(actual+"")){
                res = false;
            }else{
                aux = aux+actual;
                res = verificarCadena(cad,pos+1,aux);
            }
        }
        return res;
    }
}
