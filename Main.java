import java.util.*;
import javax.swing.*;
import pieceDef.*;

public class Main {
    static int SCREEN_WIDTH = 550;
    static int SCREEN_HEIGHT = 570;
    static int inf = Integer.MAX_VALUE;
    static int infNeg = Integer.MIN_VALUE;
    static int CHECK_MATE = 10000;
    

    static long powerOf2(int i) {
        long base = 1L;
        return (base << i);
    }

    static int[] encodeMAS(int move, int score) {
        int[] MAS = new int[2];
        MAS[0] = move;
        MAS[1] = score;
        return MAS;
    }

    static int decodeMASMove(int[] MAS) {
        return MAS[0];
    }
   
    static int decodeMASScore(int[] MAS) {
        return MAS[1];
    }
    
    public static void main(String[] args) {
        Board.initBoard();

        System.out.println("1"); 
        
        JFrame window = new JFrame("ChessBot-1");
        UI ui = new UI();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        window.add(ui);
        window.setVisible(true);

        int[] MAS = new int[2];

        Object[] option = {"Computer", "Human"};
        
        System.out.println("2"); 

        //Board.initBoard();
        //MAS = AlphaBeta.maxAlphaBeta(infNeg, inf, 0, true, 0);
        //System.out.println("final score = " + decodeMASScore(MAS));
        //window.repaint(); 
        System.out.println("3"); 
    }
}
