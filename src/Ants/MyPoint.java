package Ants;

import static Ants.MyJFrame.temp1;
import static Ants.MyJFrame.var;
import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class MyPoint extends JLabel {

    static boolean ok1 = true;
    static boolean ok2 = true;
    static Point food;
    static Point nest;
    String[] items = {"Food", "Nest"};
    JList List = new JList(items);
    private static Point lastpoint;
    int compres = 0;
    private static int count = 0;
    private MyPoint test;

    public MyPoint() {

        super();
        this.setSize(1, 1);
        MyPoint test = this;
        setLayout(new FlowLayout());

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                if (SwingUtilities.isLeftMouseButton(evt)) {
                    if (compres != 5 && compres != 6) {
                        test.compres = 1;
                        if (count % 2 != 0 && test.getLocation().equals(lastpoint) == false) {
                            var.edges.add(new Graphline(lastpoint, (Point) test.getLocation().clone()));
                            count = count + 1;
                            lastpoint = (Point) test.getLocation().clone();
                        } else {
                            if (count % 2 == 0) {
                                count = count + 1;
                                lastpoint = (Point) test.getLocation().clone();
                            }
                        }
                    }
                } else if (SwingUtilities.isRightMouseButton(evt)) {
                    if (compres == 1) {
                        add(List);
                        int i = 0;
                        temp1.setText((i + 1) + "");
                    }
                }
            }
        });

        this.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                if (compres == 0) {
                    test.compres = 2;
                } else {
                    if (compres == 1) {
                        test.compres = 1;
                    } else {
                        if (compres == 5) {
                            compres = 5;
                        } else {
                            if (compres == 6) {
                                compres = 6;
                            }
                        }
                    }
                }
            }
        });

        this.addMouseListener(new MouseAdapter() {
            public void mouseExited(MouseEvent evt) {
                if (compres == 1 || compres == 5 || compres == 6) {

                } else {
                    test.compres = 0;
                }
            }

        });

        List.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                if (SwingUtilities.isLeftMouseButton(evt)) {
                    if (List.getSelectedIndex() == 0) {
                        MyPoint test1 = null;

                        for (int i = 0; i < 224; i++) {
                            test1 = (MyPoint) MyJFrame.Panel1.getComponent(i);
                            if (test1.compres == 5) {
                                test1.compres = 1;
                            }
                        }
                        food = test.getLocation();
                        test.compres = 5;
                        List.hide();
                        temp1.setText("2");
                    }
                    if (List.getSelectedIndex() == 1) {

                        MyPoint test1 = null;

                        for (int i = 0; i < 224; i++) {
                            test1 = (MyPoint) MyJFrame.Panel1.getComponent(i);
                            if (test1.compres == 6) {
                                test1.compres = 1;
                            }
                        }
                        nest = test.getLocation();
                        test.compres = 6;
                        List.hide();
                        temp1.setText("2");
                    }
                }
            }
        });

    }

    public void drawing() {
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (compres == 1) {
            g.setColor(Color.green);
            g.fillOval(15, 15, 15, 15);
        } else {
            if (compres == 2) {
                g.setColor(Color.yellow);
                g.fillOval(15, 15, 15, 15);
            } else {
                if (compres == 0) {
                    g.setColor(Color.GRAY);
                    g.fillOval(15, 15, 15, 15);
                } else {
                    if (compres == 5) {
                        g.setColor(Color.red);
                        g.fillRect(18, 18, 18, 18);
                    } else {
                        if (compres == 6) {
                            g.setColor(Color.blue);
                            g.fillRect(18, 18, 18, 18);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }

}
