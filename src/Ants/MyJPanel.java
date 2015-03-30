package Ants;

import static Ants.MyJFrame.var;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MyJPanel extends JPanel {

    private BufferedImage image;

    public MyJPanel() {
        super();

        setSize(1, 1);
        setLocation(0, 0);
        setBackground(Color.white);
        setLayout(new GridLayout(15, 15));
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                MyPoint ob = new MyPoint();
                add(ob);
                ob.repaint();
            }
        }

        try {
            image = ImageIO.read(new File("ant.png"));
        } catch (IOException ex) {
            // handle exception...
        }
    }

    public void animate(int i) {
        if (var.t) {
            var.tim[i] = 0;
            if (var.timer[i] == null) {
                var.timer[i] = new Timer(10, (ActionEvent e) -> move(i));
                var.timer[i].start();
            } else {
                var.timer[i].start();
            }
        }
    }

    public void move(int i) {
        if (var.t && (var.ant[i] != null)) {
            double dis = var.Speed * var.tim[i];
            if (dis >= var.distance[i]) {
                var.timer[i].stop(); //stop when pass the end poit
                var.ant[i].dotest();
            } else {
                double tx, ty;
                tx = dis * Math.cos(Math.atan(Math.abs(var.m[i])));
                ty = dis * Math.sin(Math.atan(Math.abs(var.m[i])));

                if (var.x2[i] >= var.x1[i]) {
                    var.x[i] = var.x1[i] + (int) tx;
                    if (var.y2[i] >= var.y1[i]) {
                        var.y[i] = var.y1[i] + (int) ty;
                    } else {
                        var.y[i] = var.y1[i] - (int) ty;
                    }
                } else {
                    var.x[i] = var.x1[i] - (int) tx;
                    if (var.y2[i] >= var.y1[i]) {
                        var.y[i] = var.y1[i] + (int) ty;
                    } else {
                        var.y[i] = var.y1[i] - (int) ty;
                    }
                }
                if (var.tim[i] % 20 == 0) {
                    int k = var.edges.indexOf(new Graphline(new Point(var.x1[i], var.y1[i]), new Point(var.x2[i], var.y2[i])));
                    if (k >= 0) {
                        var.edges.get(k).setLocalfocus(var.edges.get(k).getLocalefocus() + 0.1);
                    }
                }
                repaint();
                var.tim[i]++;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        double i = 0;
        for (Graphline graphicsline : var.edges) {
            g.drawLine(((Double) (graphicsline.getStart().getX())).intValue() + 22, ((Double) (graphicsline.getStart().getY())).intValue() + 22, ((Double) (graphicsline.getEnd().getX())).intValue() + 22, ((Double) (graphicsline.getEnd().getY())).intValue() + 22);
            i = graphicsline.getLocalefocus();
            int m = ((Double) i).intValue();
            g.drawString("" + m, ((Double) (Math.abs((graphicsline.getEnd().getX() - graphicsline.getStart().getX()) / 2))).intValue() + ((Double) (Math.min(graphicsline.getStart().getX(), graphicsline.getEnd().getX()))).intValue(), ((Double) (Math.abs((graphicsline.getEnd().getY() - graphicsline.getStart().getY()) / 2))).intValue() + ((Double) (Math.min(graphicsline.getStart().getY(), graphicsline.getEnd().getY()))).intValue());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        repaint();
        Graphics2D g2d = (Graphics2D) g;

// Rotation information
        if (var.t == true) {
            for (int i = 0; i < var.getSum(); i++) {
                double rotationRequired;
                if (((var.x2[i] >= var.x1[i]) && (var.y2[i] < var.y1[i])) || (var.x1[i] < var.x2[i]) && (var.y1[i] <= var.y2[i])) {
                    rotationRequired = Math.toRadians(90) + Math.atan(var.m[i]);
                } else {
                    rotationRequired = -Math.toRadians(90) + Math.atan(var.m[i]);
                }

                double locationX = image.getWidth() / 2;
                double locationY = image.getHeight() / 2;
                AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

// Drawing the rotated image at the required drawing locations
                g2d.drawImage(op.filter(image, null), var.x[i] + 14, var.y[i] + 14, null);
            }
        }
    }

}
