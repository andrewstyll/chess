package src;
import java.util.*;
import pieceDef.*;

/*
 * The purpose of this class is to initialise the board. This is where I also put my board interaction functions
 * (printing the board during debugging and such)
*/
public class Board {
    //stores all pieces in the game
    private static HashMap<Character, Piece> pieces = new HashMap<Character, Piece>();

    //initialisation board
    //WHITE ARE THE CAPITALISED ONES
    static char charBoard[][] = {
        {'r', 'k', 'b', 'q', 'a', 'b', 'k', 'r'},
        {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
        {'R', 'K', 'B', 'Q', 'A', 'B', 'K', 'R'},
    };
    /*static char charBoard[][] = {
        {' ', ' ', ' ', 'r', 'a', 'b', ' ', ' '},
        {' ', ' ', ' ', 'p', ' ', 'p', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', 'B', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', 'Q', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    };*/

    //initialise the board and all the piece objects 
    public static void initBoard() {
        initPieces();
        charBoardToBitBoard();
    }
    
    //create all of the peice objects
    static void initPieces() {
        pieces.put('p', new Pawn(Piece.Side.BLACK));
        pieces.put('r', new Rook(Piece.Side.BLACK));
        pieces.put('k', new Knight(Piece.Side.BLACK));
        pieces.put('b', new Bishop(Piece.Side.BLACK));
        pieces.put('q', new Queen(Piece.Side.BLACK));
        pieces.put('a', new King(Piece.Side.BLACK));
        pieces.put('P', new Pawn(Piece.Side.WHITE));
        pieces.put('R', new Rook(Piece.Side.WHITE));
        pieces.put('K', new Knight(Piece.Side.WHITE));
        pieces.put('B', new Bishop(Piece.Side.WHITE));
        pieces.put('Q', new Queen(Piece.Side.WHITE));
        pieces.put('A', new King(Piece.Side.WHITE));
    }

    //create an initial location for all of the pieces on the board
    static void charBoardToBitBoard() {
        Piece tmpPiece;
        for(int i = 0; i < 64; i++) {
            switch(charBoard[i/8][i%8]) {
                case 'P':
                    tmpPiece = pieces.get('P');
                    tmpPiece.addLocation(Main.powerOf2(i));
                    break;
                case 'p':
                    tmpPiece = pieces.get('p');
                    tmpPiece.addLocation(Main.powerOf2(i));
                    break;
                case 'K': 
                    tmpPiece = pieces.get('K');
                    tmpPiece.addLocation(Main.powerOf2(i));
                    break;
                case 'k': 
                    tmpPiece = pieces.get('k');
                    tmpPiece.addLocation(Main.powerOf2(i));
                    break;
                case 'B': 
                    tmpPiece = pieces.get('B');
                    tmpPiece.addLocation(Main.powerOf2(i));
                    break;
                case 'b': 
                    tmpPiece = pieces.get('b');
                    tmpPiece.addLocation(Main.powerOf2(i));
                    break;
                case 'R':
                    tmpPiece = pieces.get('R');
                    tmpPiece.addLocation(Main.powerOf2(i));
                    break;
                case 'r': 
                    tmpPiece = pieces.get('r');
                    tmpPiece.addLocation(Main.powerOf2(i));
                    break;
                case 'Q': 
                    tmpPiece = pieces.get('Q');
                    tmpPiece.addLocation(Main.powerOf2(i));
                    break;
                case 'q': 
                    tmpPiece = pieces.get('q');
                    tmpPiece.addLocation(Main.powerOf2(i));
                    break;
                case 'A': 
                    tmpPiece = pieces.get('A');
                    tmpPiece.addLocation(Main.powerOf2(i));
                    break;
                case 'a': 
                    tmpPiece = pieces.get('a');
                    tmpPiece.addLocation(Main.powerOf2(i));
                    break;
            }   
        }
    }
    
    //accessor for the pieces objects
    public static HashMap<Character, Piece> getPieces() {
        return pieces;
    }

    public static boolean pawnCheck(int x, int y, boolean botIsWhite) {
        int index = y*8 + x; 
        long location = 0l;
        long checkLocation = Main.powerOf2(index);

        if(botIsWhite) {
            location = getPieces().get('p').getLocation();        
            if( (location & checkLocation) == 0) {
                return false;
            } else {
                return true;
            }
        } else {
            location = getPieces().get('P').getLocation();        
            if( (location & checkLocation) == 0) {
                return false;
            } else {
                return true;
            }
        }
    }

    //for debug, draws the board and fills all the spaces with peices in them with the character K
    public static void drawBoard(long num) {
        char board[][] = new char[8][8];

        for(int i = 0; i<64; i++) {
            if(((num>>i) & 1) == 1) {
                board[i/8][i%8] = 'K';
            } else {
                board[i/8][i%8] = ' ';
            }
        }
        for(int i = 0; i < 8; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println("----------------");
    }

}
