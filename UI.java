import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UI extends JPanel implements MouseListener, MouseMotionListener {
    
    static int squareWidth = 64;

    public void paintComponent(Graphics g) {
        super.paintComponent(g); //allows for partial painting of a canvas
        this.setBackground(Color.LIGHT_GRAY);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        //now paint the squares
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if((i+j)%2 == 0) {
                    g.setColor(new Color(255, 248, 220));
                } else {
                    g.setColor(new Color(139, 69, 19));
                }
                g.fillRect(i*squareWidth+20, j*squareWidth+18, squareWidth, squareWidth);
            }
        }
    }

    public void mouseMoved(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    public void mouseDragged(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
}
