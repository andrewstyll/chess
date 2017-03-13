package src;

import java.util.*;
import javax.swing.*;

import pieceDef.*;

public class Main {
    
    static int SCREEN_WIDTH = 550;
    static int SCREEN_HEIGHT = 570;
    static int inf = Integer.MAX_VALUE;
    static int infNeg = Integer.MIN_VALUE;
    static int CHECK_MATE = 1000000;

    static boolean botIsWhite = false;

    static long powerOf2(int i) {
        long base = 1L;
        return (base << i);
    }
    
    public static void main(String[] args) {
        
        //initialise the board
        Board.initBoard();
        
        //build the window for the game to live in
        ImageIcon icon = new ImageIcon("Assets/Icon.png");

        JFrame window = new JFrame("ChessBot-1");
        UI ui = new UI();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        window.add(ui);
        window.setVisible(true);
        
        //option window for who is playing what role
        Object[] option = {"You", "Bot"};
        int val = JOptionPane.showOptionDialog(null, "Who will be playing as white?", "ChessBot-1", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon, option, option[0]);
        botIsWhite = (val == 1) ? true : false;
        
        int[] MAS = new int[2];

        //if the bot is white let it go first. This will make the bots first move
        if(botIsWhite) {
            System.out.println("thinking......");
            MAS = AlphaBeta.maxAlphaBeta(infNeg, inf, 0, botIsWhite, 0);
            System.out.println("Move Score is : " + Moves.decodeMASScore(MAS));
            //System.out.println(Integer.toHexString(Moves.decodeMASMove(MAS)));
            Moves.makeMoveAllPieces(Moves.decodeMASMove(MAS), true);
            window.repaint();
        }
    }
}
