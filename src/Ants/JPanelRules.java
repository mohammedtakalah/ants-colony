package Ants;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class JPanelRules extends JPanel{
    
    private BufferedImage image1;
    private BufferedImage image2;
    private BufferedImage image3;
    
    public JPanelRules() {
        super();
        try {
            image1 = ImageIO.read(new File("1.png"));
            image2 = ImageIO.read(new File("2.png"));
            image3 = ImageIO.read(new File("3.png"));
        } catch (IOException ex) {
            // handle exception...
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        repaint();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image1, 0, 0, Color.yellow, this);
        g2d.drawImage(image2, 0, 100, Color.yellow, this);
        g2d.drawImage(image3, 0, 200, Color.yellow, this);
    }
 
}
