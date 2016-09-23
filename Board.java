import java.util.*;
import pieceDef.*;

public class Board {
    private static HashMap<Character, Piece> pieces = new HashMap<Character, Piece>();


    //WHITE IS THE CAPITALISED ONES
    static char charBoard[][] = {
        {'r', 'k', 'b', 'q', 'a', 'b', 'k', 'r'},
        {'p', 'p', 'p', 'p', 'p', 'P', 'p', 'p'},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {'P', 'P', 'p', 'P', 'P', 'P', 'P', 'P'},
        {'R', 'K', 'B', 'Q', 'A', 'B', 'K', 'R'},
    };
    
    public static void initBoard() {
        initPieces();
        charBoardToBitBoard();
    }
    
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

    //static void charBoardToBitBoard(char[][] charBoard, long wPawn, long bPawn, long wKnight, long bKnight, wBishop, bBishop, wRook, bRook, wQueen, bQueen, wKing, bKing);
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
   
    public static HashMap<Character, Piece> getPieces() {
        return pieces;
    }

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
    }

}
