package Ants;

import java.util.ArrayList;
import javax.swing.Timer;

public class variable {

    boolean ok = true;
    double Speed = 6;
    private int sum = 500;
    int i = 0;
    Timer[] timer = new Timer[500];
    double[] distance = new double[sum];
    Ant[] ant = new Ant[sum];
    int[] tim = new int[sum];
    int[] x1 = new int[sum], x2 = new int[sum], y1 = new int[sum], y2 = new int[sum], x = new int[sum], y = new int[sum];
    boolean t = false;
    double[] m = new double[sum];
    ArrayList<Graphline> edges = new ArrayList<>();
    double Q = 50000;
    double b = 0.95;
    double alpha = 10;
    double beta = 0;

    public void setSum(int sum) {
        this.sum = sum;
        double[] distance = new double[sum];
        Ant[] ant = new Ant[sum];
        int[] tim = new int[sum];
        int[] x1 = new int[sum], x2 = new int[sum], y1 = new int[sum], y2 = new int[sum], x = new int[sum], y = new int[sum];
        boolean t = false;
        double[] m = new double[sum];

    }

    public int getSum() {
        return sum;
    }

}
