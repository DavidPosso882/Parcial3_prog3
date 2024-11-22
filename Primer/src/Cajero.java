public class Cajero {

    public static void main(String[] args) {
        Cuenta cuenta = new Cuenta();
        try
        {
            cuenta.ingresar(100);
            cuenta.extraer(101);
        }
        catch (SaldoInsuficienteException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
