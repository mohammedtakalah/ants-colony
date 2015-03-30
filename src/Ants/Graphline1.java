package Ants;

import static Ants.MyJFrame.var;
import java.io.Serializable;

public class Graphline1 extends Graphline implements Serializable{

    private double p;
    private double p1;

    public Graphline1(Graphline s) {
        super(s);
        this.p1();
    }

    public void setP(double p) {
        this.p = p;
    }

    public void setP1(double p1) {
        this.p1 = p1;
    }

    public double getP() {
        return p;
    }

    public double getP1() {
        p1();
        return p1;
    }

    public void p1() {
        p1 = (Math.pow(super.getLocalefocus(), var.alpha) * Math.pow((1 / super.getLength()), var.beta));
    }

}
