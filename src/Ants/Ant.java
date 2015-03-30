package Ants;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import static Ants.MyJFrame.var;

public class Ant {

    static Random o = new Random();
    static int number = 0;
    int index;
    boolean havefood = false;
    Point loca = MyPoint.nest;
    ArrayList<Integer> edgesvisited1 = new ArrayList<>();
    ArrayList<Integer> edgesvisited2 = new ArrayList<>();
    double l;
    double totalpossibility;
    Graphline x = null;

    public Ant() {
        index = number;
        number++;
    }

    public static void run() {
        for (int i = 0; i < var.getSum(); i++) {
            var.ant[i].dotest();
        }
    }

    public void dotest() {
        if (var.t == true) {
            if (loca.equals(MyPoint.nest)) {
                this.dvlp();
                havefood = false;
                l = 0;
                edgesvisited1.clear();
                edgesvisited2.clear();
                x = this.choiseedges(loca);
                l = l + x.getLength();
                edgesvisited1.add(var.edges.indexOf(x));
                edgesvisited2.add(var.edges.indexOf(x));
            } else if (loca.equals(MyPoint.food) || (havefood == true)) {
                havefood = true;
                x = var.edges.get(edgesvisited1.get(edgesvisited1.size() - 1));
                edgesvisited1.remove(edgesvisited1.size() - 1);
            } else {
                x = this.choiseedges(loca);
                if (x == null) {
                    x = var.edges.get(edgesvisited1.get(edgesvisited1.size() - 1));
                    edgesvisited1.remove(edgesvisited1.size() - 1);
                } else {
                    l = l + x.getLength();
                    edgesvisited1.add(var.edges.indexOf(x));
                    edgesvisited2.add(var.edges.indexOf(x));
                }
            }
            trans(loca, x.otherpoint(loca));
            var.t = true;
            MyJFrame.Panel1.animate(index);
            loca = x.otherpoint(loca);
        }
    }

    public void trans(Point x1, Point x2) {
        var.x1[index] = (int) x1.getX();
        var.y1[index] = (int) x1.getY();
        var.x2[index] = (int) x2.getX();
        var.y2[index] = (int) x2.getY();
        var.m[index] = (double) (var.y2[index] - var.y1[index]) / (double) (var.x2[index] - var.x1[index]);
        var.x[index] = var.x1[index];
        var.y[index] = var.y1[index];
        var.distance[index] = Math.pow(Math.pow(x1.getY() - x2.getY(), 2) + Math.pow(x1.getX() - x2.getX(), 2), 0.5);
    }

    public Graphline choiseedges(Point local) {
        ArrayList<Graphline1> edch = new ArrayList<>();
        double tp = 0, t1 = 0;
        for (Graphline ob : var.edges) {
            if (ob.equals(loca) && (edgesvisited2.indexOf(var.edges.indexOf(ob)) < 0)) {
                Graphline1 h = new Graphline1(ob);
                edch.add(h);
                tp += h.getP1();
            }
        }
        if (tp == 0) {
            return null;
        }

        double d = o.nextDouble();

        for (Graphline1 ob : edch) {
            ob.setP(ob.getP1() / tp);
            if (d < (ob.getP() + t1)) {
                return ob;
            } else {
                t1 += ob.getP();
            }
        }
        return edch.get(edch.size() - 1);
    }

    public void dvlp() {
        for (Graphline ob : var.edges) {
            if ((edgesvisited2.indexOf(var.edges.indexOf(ob)) < 0) || (havefood == false)) {
                ob.setLocalfocus(var.b * ob.getLocalefocus());
            } else {
                ob.setLocalfocus(var.b * ob.getLocalefocus());
                ob.dvlp(l);
            }
        }
    }

}
