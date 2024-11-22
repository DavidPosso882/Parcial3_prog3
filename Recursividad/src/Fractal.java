public class Fractal{
    public static int copoNieve(int n){
        if(n == 0){
            return 3;
        }else{
            return copoNieve(n-1) + (3*(int)Math.pow(2,2*(n-1)));
        }
    }
}
