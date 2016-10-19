import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;
import pieceDef.*;

public class UI extends JPanel implements MouseListener, MouseMotionListener {
    
    static int mouseX1, mouseY1, mouseX2, mouseY2;
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
        
        //Image chessPiecesImage = new ImageIcon("Assets/ChessPieces.png").getImage();
        Image chessPiecesImage = new ImageIcon("Assets/Chess.png").getImage();
     
        //look through every piece and set a j and k value for each one
        HashMap<Character, Piece> pieces = Board.getPieces();
        for(Map.Entry<Character, Piece> entry : pieces.entrySet()) {
            char key = entry.getKey();
            Piece chessPiece = entry.getValue();

            long location = chessPiece.getLocation();
            
            int j = -1, k = -1;
            for(int i = 0; i < 64; i++) {
                if(((location>>i) & 1) == 1) {
                    //WE HAVE FOUND A PIECE on the board
                    switch(key) {
                        case 'P': j = 5; k = 0;
                            break;
                        case 'R': j = 4; k = 0;
                            break; 
                        case 'K': j = 3; k = 0;
                            break;
                        case 'B': j = 2; k = 0;
                            break;
                        case 'Q': j = 1; k = 0;
                            break;
                        case 'A': j = 0; k = 0;
                            break;
                        
                        case 'p': j = 5; k = 1;
                            break;
                        case 'r': j = 4; k = 1;
                            break;
                        case 'k': j = 3; k = 1;
                            break;
                        case 'b': j = 2; k = 1;
                            break;
                        case 'q': j = 1; k = 1;
                            break;
                        case 'a': j = 0; k = 1;
                            break;
                    }
                    if(j != -1 && k != -1) {
                        g.drawImage(chessPiecesImage, (i%8)*squareWidth+20, (i/8)*squareWidth+18, (i%8+1)*squareWidth+20, (i/8+1)*squareWidth+18, j*60, k*60, (j+1)*60, (k+1)*60, this); 
                        j = -1;
                        k = -1;
                    }
                }
            }
        }
    }

    public void mouseMoved(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {
        if( e.getX() < 8*squareWidth && e.getY() < 8*squareWidth ) {
            mouseX1 = e.getX();
            mouseY1 = e.getY();
            this.repaint();
        }
    }

    public void mouseReleased(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    public void mouseDragged(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
}
