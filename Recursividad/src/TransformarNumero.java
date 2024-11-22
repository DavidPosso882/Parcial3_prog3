public class TransformarNumero {
    public static int transformarBinario(int num){
        int base = 10;
        int exp = 0;
        return transformarBinario(num,base,exp);
    }

   /* public static int transformarBinario(int num, int base, int exp){
        int res;
        if(num == 0){
            res = 0;
        }else{
            int residuo = num%2;
            num = num/2;
            res = (residuo*(int)(Math.pow(base,exp)) + transformarBinario(num,base,exp+1));
        }
        return res;
    }*/

    public static int transformarBinario(int numero, int base, int exp){
        if(numero == 0){
            return 0;
        }else{
            int residuo = numero%2;
            numero = numero/2;
            return (residuo*(int)(Math.pow(base,exp))+transformarBinario(numero,base,exp+1));
        }
    }
}
