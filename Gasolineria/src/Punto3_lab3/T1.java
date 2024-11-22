package Punto3_lab3;

public class T1 extends Thread{
    private ConditionMonitor conditionMonitor;

    public T1(ConditionMonitor c) {
        conditionMonitor = c;
    }

    @Override
    public void run() {
        while(true) {
            conditionMonitor.waitForCondition();
            System.out.println(("El hilo 1 puede continuar, con la condición " + conditionMonitor.condition()));
            conditionMonitor.condition++;
        }
    }
}
