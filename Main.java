import java.util.*;
import javax.swing.*;
import pieceDef.*;

public class Main {
    static int SCREEN_WIDTH = 550;
    static int SCREEN_HEIGHT = 570;

    static long powerOf2(int i) {
        long base = 1L;
        return (base << i);
    }
    
    public static void main(String[] args) {
        /*JFrame window = new JFrame("ChessBot-1");
        UI ui = new UI();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        window.add(ui);
        window.setVisible(true);*/

        Board.initBoard();
        Moves.movesTest();
    }
}
