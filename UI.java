import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UI extends JPanel implements MouseListener, MouseMotionListener {
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //allows for partial painting of a canvas
        this.setBackground(Color.LIGHT_GRAY);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        //now paint the squares
       
    }

    public void mouseMoved(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    public void mouseDragged(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
}
