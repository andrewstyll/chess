import java.util.*;
import pieceDef.*;

public class Board {
    static long wPawn = 0l, bPawn = 0l;
    static long wKnight = 0l, bKnight = 0l;
    static long wBishop = 0l, bBishop = 0l;
    static long wRook = 0l, bRook = 0l;
    static long wQueen = 0l, bQueen = 0l;
    static long wKing = 0l, bKing = 0l;
    static HashMap<Character, Piece> pieces = new HashMap<Character, Piece>();

    //WHITE IS THE CAPITALISED ONES
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
                    tmpPiece.addLocation(powerOf2((i-63)*-1));
                    break;
                case 'p':
                    tmpPiece = pieces.get('p');
                    tmpPiece.addLocation(powerOf2((i-63)*-1));
                    break;
                case 'K': 
                    tmpPiece = pieces.get('K');
                    tmpPiece.addLocation(powerOf2((i-63)*-1));
                    break;
                case 'k': 
                    tmpPiece = pieces.get('k');
                    tmpPiece.addLocation(powerOf2((i-63)*-1));
                    break;
                case 'B': 
                    tmpPiece = pieces.get('B');
                    tmpPiece.addLocation(powerOf2((i-63)*-1));
                    break;
                case 'b': 
                    tmpPiece = pieces.get('b');
                    tmpPiece.addLocation(powerOf2((i-63)*-1));
                    break;
                case 'R': 
                    tmpPiece = pieces.get('R');
                    tmpPiece.addLocation(powerOf2((i-63)*-1));
                    break;
                case 'r': 
                    tmpPiece = pieces.get('r');
                    tmpPiece.addLocation(powerOf2((i-63)*-1));
                    break;
                case 'Q': 
                    tmpPiece = pieces.get('Q');
                    tmpPiece.addLocation(powerOf2((i-63)*-1));
                    break;
                case 'q': 
                    tmpPiece = pieces.get('q');
                    tmpPiece.addLocation(powerOf2((i-63)*-1));
                    break;
                case 'A': 
                    tmpPiece = pieces.get('A');
                    tmpPiece.addLocation(powerOf2((i-63)*-1));
                    break;
                case 'a': 
                    tmpPiece = pieces.get('a');
                    tmpPiece.addLocation(powerOf2((i-63)*-1));
                    break;
            }   
        }
    }

    static long powerOf2(int i) {
        long base = 1;
        return (base << i);
    }
}
