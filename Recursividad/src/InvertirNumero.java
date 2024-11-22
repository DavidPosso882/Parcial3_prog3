public class InvertirNumero {

    public static int invertirNumero(int num, int res) {

        if(num == 0){
            return res;
        }else{
            int residuo = num%10;
            num = num/10;
            res = (res*10) + residuo;
            return invertirNumero(num,res);
        }
    }

    public static int invertirNumero(int num) {
        int res = 0;
        return invertirNumero(num, res);
    }
}
