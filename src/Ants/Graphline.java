package Ants;

import static Ants.MyJFrame.var;
import java.awt.Point;
import java.io.Serializable;

public class Graphline implements Serializable {

    private Point start;
    private Point end;
    private double length;
    private final static double primitivefocus = 2;
    private Double localefocus = new Double(0);

    public Point getEnd() {
        return end;
    }

    public double getLength() {
        return length;
    }

    public double getLocalefocus() {
        return localefocus;
    }

    public static double getPrimitivefocus() {
        return primitivefocus;
    }

    public Point getStart() {
        return start;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public boolean equals(Object ob) {
        Graphline obj = (Graphline) ob;
        if (((obj.start.equals(start)) && (obj.end.equals(end))) || ((obj.end.equals(start)) && (obj.start.equals(end)))) {
            return true;
        }
        return false;
    }

    public boolean equals(Point obj) {
        if (obj.equals(start) || (obj.equals(end))) {
            return true;
        }
        return false;
    }

    public Point otherpoint(Point obj) {
        if (obj.equals(start)) {
            return end;
        }
        return start;
    }

    public Graphline(Graphline g) {
        this.start = g.start;
        this.end = g.end;
        this.length();
        this.localefocus = g.getLocalefocus();
    }

    public Graphline(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.length();
        this.setLocalfocus(0);
    }

    public void dvlp(double l) {
        this.setLocalfocus(this.getLocalefocus() + (var.Q / l));
    }

    public void length() {
        length = Math.pow(Math.pow(start.getY() - end.getY(), 2) + Math.pow(start.getX() - end.getX(), 2), 0.5);
    }

    public void setLocalfocus(double quntity) {
        this.localefocus = quntity;
        if (localefocus < primitivefocus) {
            localefocus = primitivefocus;
        }
    }
}
