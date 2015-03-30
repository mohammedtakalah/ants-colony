package Ants;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MyJFrame extends JFrame {

    static MyJPanel Panel1;
    static variable var = new variable();
    static JButton StartSimulate = new JButton("Start Simulation");
    static JButton Restart = new JButton("Restart Map");
    static JSlider SpeedAnts = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
    static JSlider NumberAnts = new JSlider(JSlider.HORIZONTAL, 1, 500, 1);
    static JSlider Evaporate = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
    static JSlider FormonAmount = new JSlider(JSlider.HORIZONTAL, 0, 50, 0);
    static JSlider Alpha = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
    static JSlider Beta = new JSlider(JSlider.HORIZONTAL, -100, 0, -100);
    static JLabel LabelSpeed = new JLabel("Speed Ants = ");
    static JLabel LabelNumber = new JLabel("Number Ants = ");
    static JLabel LabelEvaporate = new JLabel("Evaporate Operator = ");
    static JLabel LabelFormonAmount = new JLabel("Feromon Amount = ");
    static JLabel LabelAlpha = new JLabel("Alpha = ");
    static JLabel LabelBeta = new JLabel("Beta = " + -1.0);
    static JLabel temp1 = new JLabel();
    static JLabel temp2 = new JLabel();

    public MyJFrame() {

        super("The Projcet");

        setSize(1024, 768);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        
        Panel1 = new MyJPanel();
        add(Panel1, BorderLayout.CENTER);

        JPanel Panel2 = new JPanel();
        Panel2.setSize(256, 768);
        Panel2.setLocation(768, 0);

        JPanel Panel3 = new JPanel();
        JPanel temp = new JPanel();
        Panel3.setLayout(new GridLayout(15, 1));
        Panel2.setLayout(new GridLayout(2, 3));

        Panel3.add(LabelSpeed);
        Panel3.add(SpeedAnts);
        Panel3.add(LabelNumber);
        Panel3.add(NumberAnts);
        Panel3.add(LabelEvaporate);
        Panel3.add(Evaporate);
        Panel3.add(LabelFormonAmount);
        Panel3.add(FormonAmount);
        Panel3.add(LabelAlpha);
        Panel3.add(Alpha);
        Panel3.add(LabelBeta);
        Panel3.add(Beta);
        Panel3.add(temp);
        Panel2.add(Panel3);

        JPanel Panel4 = new JPanel();
        Panel4.setLayout(new GridLayout(3, 2));

        Panel4.add(StartSimulate);
        Panel4.add(Restart);
        Panel4.add(temp1);
        Panel2.add(Panel4);

        add(Panel2, BorderLayout.EAST);

        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Program");
        JMenuItem OpenFile = new JMenuItem("Open file");
        JMenuItem SaveFile = new JMenuItem("Save file");
        JMenuItem TheRules = new JMenuItem("The Rules");
        JMenuItem Exit = new JMenuItem("Exit");
        menu.add(OpenFile);
        menu.add(SaveFile);
        menu.add(TheRules);
        menu.add(Exit);
        menubar.add(menu);
        setJMenuBar(menubar);

        OpenFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Point nest = null, food = null;
                try {
                    ObjectInputStream h = new ObjectInputStream(new FileInputStream("Map.bin"));
                    var.edges = (ArrayList<Graphline>) h.readObject();
                    nest = (Point) h.readObject();
                    food = (Point) h.readObject();
                } catch (Exception ex) {
                    Logger.getLogger(MyJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (Graphline o : var.edges) {
                    o.setLocalfocus(0);
                    MyPoint test1 = null;
                    for (int i = 0; i < 225; i++) {
                        test1 = (MyPoint) MyJFrame.Panel1.getComponent(i);
                        if (o.equals(test1.getLocation())) {
                            test1.compres = 1;
                        }
                    }
                }
                MyPoint test1 = null;
                for (int i = 0; i < 224; i++) {
                    test1 = (MyPoint) MyJFrame.Panel1.getComponent(i);
                    if (nest.equals(test1.getLocation())) {
                        test1.compres = 6;
                    } else if (food.equals(test1.getLocation())) {
                        test1.compres = 5;
                    }
                }
            }
        });

        SaveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ObjectOutputStream h = new ObjectOutputStream(new FileOutputStream("Map.bin"));
                    h.writeObject(var.edges);
                    h.writeObject(MyPoint.nest);
                    h.writeObject(MyPoint.food);
                } catch (IOException ex) {

                }
            }
        });

        TheRules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f = new JFrame("Rules");
                f.setSize(550, 400);
                f.setLocationRelativeTo(null);
                JPanelRules rule1 = new JPanelRules();
                f.add(rule1);
                f.show();
            }
        });

        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        SpeedAnts.setMajorTickSpacing(1);
        SpeedAnts.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                var.Speed = SpeedAnts.getValue();
                int value = SpeedAnts.getValue();
                LabelSpeed.setText("Speed Ants = " + value);
            }
        });
        SpeedAnts.setValue(6);

        NumberAnts.setMajorTickSpacing(1);
        NumberAnts.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                var.setSum(NumberAnts.getValue());
                int value = NumberAnts.getValue();
                LabelNumber.setText("Number Ants = " + value);
            }
        });
        NumberAnts.setValue(150);

        Evaporate.setMajorTickSpacing(1);
        Evaporate.setPaintTicks(true);
        Evaporate.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                var.b = (double) Evaporate.getValue() / 100;
                double value = Evaporate.getValue();
                LabelEvaporate.setText("Evaporate Operator = " + value / 100);
            }
        });
        Evaporate.setValue(70);

        FormonAmount.setMajorTickSpacing(1);
        FormonAmount.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                var.Q = FormonAmount.getValue() * 1000;
                int value = FormonAmount.getValue();
                LabelFormonAmount.setText("Feromon Amount = " + value);
            }
        });
        FormonAmount.setValue(25);

        Alpha.setMajorTickSpacing(1);
        Alpha.setPaintTicks(true);
        Alpha.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                var.alpha = Alpha.getValue() / 10;
                double value = Alpha.getValue();
                LabelAlpha.setText("Alpha = " + value / 10);
            }
        });
        Alpha.setValue(30);

        Beta.setMajorTickSpacing(1);
        Beta.setPaintTicks(true);
        Beta.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                var.beta = Beta.getValue() / 100;
                double value = Beta.getValue();
                LabelBeta.setText("Beta = " + value / 100);
            }
        });
        Beta.setValue(-100);

        
        StartSimulate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (SwingUtilities.isLeftMouseButton(evt)) {
                    if (MyPoint.nest != null && MyPoint.food != null && var.ok == true) {
                        var.ok = false;
                        System.gc();
                        MyPoint test = null;
                        for (int i = 0; i < 225; i++) {
                            test = (MyPoint) MyJFrame.Panel1.getComponent(i);
                            if (test.compres == 1 || test.compres == 5 || test.compres == 6) {

                            } else {
                                test.hide();
                            }
                        }
                        for (int i = 0; i < var.getSum(); i++) {
                            var.ant[i] = new Ant();
                        }
                        for (int i = var.getSum(); i < 500; i++) {
                            var.ant[i] = null;
                        }
                        System.gc();
                        var.t = true;
                        Ant.run();
                        NumberAnts.disable();
                    }
                }
            }
        });

        Restart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (SwingUtilities.isLeftMouseButton(evt)) {
                    var.ok = true;
                    MyPoint test = null;
                    var.edges.clear();
                    for (int i = 0; i < 224; i++) {
                        test = (MyPoint) MyJFrame.Panel1.getComponent(i);
                        test.compres = 0;
                        if (test.compres == 1 || test.compres == 5 || test.compres == 6) {

                        } else {
                            test.show();
                        }
                    }
                    Panel1.hide();
                    Panel1 = new MyJPanel();
                    add(Panel1, BorderLayout.CENTER);
                    var.t = false;
                    Ant.number = 0;
                    NumberAnts.enable();
                    MyPoint.nest = null;
                    MyPoint.food = null;
                }
            }
        });
    }

    
//////////////////////////////*********Main***********//////////////////////////////////////    
    public static void main(String[] args) {

        MyJFrame Project = new MyJFrame();
        Project.setDefaultCloseOperation(2);

    }

}
