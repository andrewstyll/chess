import java.util.*;
import javax.swing.*;

public class Main {
    static int SCREEN_WIDTH = 550;
    static int SCREEN_HEIGHT = 550;

    public static void main(String[] args) {
        JFrame window = new JFrame("ChessBot-1");
        UI ui = new UI();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        window.add(ui);
        window.setVisible(true);
    }
}
