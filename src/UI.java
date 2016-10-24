package src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;
import pieceDef.*;

public class UI extends JPanel implements MouseListener, MouseMotionListener {
    
    static int mouseX1, mouseY1, mouseX2, mouseY2;
    static int squareWidth = 64;
    static int xOffset = 20, yOffset = 18;

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
                g.fillRect(i*squareWidth+xOffset, j*squareWidth+yOffset, squareWidth, squareWidth);
            }
        }
        
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
                        g.drawImage(chessPiecesImage, (i%8)*squareWidth+xOffset, (i/8)*squareWidth+yOffset, (i%8+1)*squareWidth+xOffset, (i/8+1)*squareWidth+yOffset, j*60, k*60, (j+1)*60, (k+1)*60, this); 
                        j = -1;
                        k = -1;
                    }
                }
            }
        }
    }

    public void mouseMoved(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {
        int xLocation = (e.getX() - xOffset)/squareWidth;
        int yLocation = (e.getY() - yOffset)/squareWidth; 
        if( xLocation < 8 && yLocation < 8 ) {
            mouseX1 = xLocation;
            mouseY1 = yLocation;
            System.out.println("x: " + mouseX1 + " y: " + mouseY1);
            this.repaint();
        }
    }

    public void mouseReleased(MouseEvent e) {
        int xLocation = (e.getX() - xOffset)/squareWidth;
        int yLocation = (e.getY() - yOffset)/squareWidth; 

        int[] moves = new int[Moves.MAX_MOVES];
        int move;
        
        if(xLocation < 8 && yLocation < 8) {
            mouseX2 = xLocation;
            mouseY2 = yLocation;

            if(e.getButton() == MouseEvent.BUTTON1) {

                //did i move a pawn??
                if( mouseY2 == 0 && mouseY1 == 1 /*insert check to see if i moved a pawn here*/ ) {
                    if(Main.botIsWhite == 1) { //is the bot black or white??
                        //these are all assuming queen promotion, we can let the user choose here
                        move = Moves.encodeMove(mouseY1, mouseX1, mouseY2, mouseX2, 'q');
                    } else {
                        move = Moves.encodeMove(mouseY1, mouseX1, mouseY2, mouseX2, 'Q');
                    }
                } else {
                    //y2x2y1x1
                    move = Moves.encodeMove(mouseY1, mouseX1, mouseY2, mouseX2, ' ');
                    System.out.println(Integer.toHexString(move));
                }
                if(Main.botIsWhite == 1) {
                    moves = Moves.possibleMovesBlack(false);
                } else {
                    moves = Moves.possibleMovesWhite(true);
                }

                if(/*Arrays.asList(moves).contains(move)*/) { //THIS CHECK IS FAILING
                    int[] MAS = new int[2];
                    //if valid move make user move
                    Moves.showMoves(moves);
                    Moves.makeMoveAllPieces(move, true);
                    //TODO::move side based on bot is white or not
                    //make the Bot move
                    /*MAS = AlphaBeta.maxAlphaBeta(Main.infNeg, Main.inf, 0, false, 0);
                    //now make the move here
                    Moves.makeMoveAllPieces(Moves.decodeMASMove(MAS), true);*/
                    this.repaint();
                }
            }
        }
    }

    public void mouseClicked(MouseEvent e) {}

    public void mouseDragged(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
}
