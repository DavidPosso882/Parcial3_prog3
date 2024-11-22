public class Cuenta {
    double saldo;
    public Cuenta() {
        saldo = 0;
    }
    public void ingresar(double c){
        saldo += c;
    }
    public void extraer(double c) throws SaldoInsuficienteException {
        if (saldo < c) throw new SaldoInsuficienteException("Numeros rojos");
        else saldo -= c;
    }
    public double getSaldo() {
        return saldo;
    }
}
