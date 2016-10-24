package src;

import java.util.*;
import javax.swing.*;

import pieceDef.*;

public class Main {
    //make a const??? I only want to set this once
    public static int botIsWhite;
    
    static int SCREEN_WIDTH = 550;
    static int SCREEN_HEIGHT = 570;
    static int inf = Integer.MAX_VALUE;
    static int infNeg = Integer.MIN_VALUE;
    static int CHECK_MATE = 10000;

    static long powerOf2(int i) {
        long base = 1L;
        return (base << i);
    }
    
    public static void main(String[] args) {

        ImageIcon icon = new ImageIcon("Assets/Icon.png");

        JFrame window = new JFrame("ChessBot-1");
        UI ui = new UI();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        window.add(ui);
        window.setVisible(true);
        
        Board.initBoard();
        int[] MAS = new int[2];


        Object[] option = {"You", "Bot"};
        botIsWhite = JOptionPane.showOptionDialog(null, "Who will be playing as white?", "ChessBot-1", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon, option, option[0]);

        /*if(botIsWhite == 1) {
            System.out.println("3"); 
            MAS = AlphaBeta.maxAlphaBeta(infNeg, inf, 0, false, 0);
            Moves.makeMoveAllPieces(Moves.decodeMASMove(MAS), true);
            window.repaint();
        }*/
    }
}
