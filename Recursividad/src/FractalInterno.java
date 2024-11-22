public class FractalInterno {
    /*public static int trianguloDeSierpinski(int n){
        if(n == 0){
            return 1;
        }else{
            return trianguloDeSierpinski(n-1) + (4*(int)Math.pow(3,n-1));
        }
    }*/

    public static int trianguloDeSierpinski(int n){
        if(n == 0){
            return 1;
        }else{
            return (trianguloDeSierpinski(n-1)*3)+2;
        }
    }
}
