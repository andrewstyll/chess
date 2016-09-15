import java.util.*;
import pieceDef.*;

public class Board {
    static HashMap<Character, Piece> pieces = new HashMap<Character, Piece>();

    static long lSide = -9187201950435737472L;
    static long rSide = 72340172838076673L;
    static long topRow = 255L;
    static long bottomRow = -72057594037927936L;

    //WHITE IS THE CAPITALISED ONES
    static char charBoard[][] = {
        {'r', 'k', 'b', 'q', 'a', 'b', 'k', 'r'},
        {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', 'p', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', 'Q', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
        {'R', 'K', 'B', 'Q', 'A', 'B', 'K', 'R'},
    };
    
    public static String possibleMovesWhite() {
        String history = "";
        String moves = "";

        long piecesB = getBlackPosition();
        long piecesW = getWhitePosition();
        long tmp = piecesB|piecesW;
        
        //pieces.get('P').verticalHorizontalHQ(tmp, 36);
        pieces.get('P').diagonalAntiDiagonalHQ(tmp, 36);
        //moves += pieces.get('P').getMovesW(history, piecesB, piecesW, lSide, rSide, topRow);
        
        //System.out.println(moves);
        return ""; 
    }
    
    static long getBlackPosition() {
        long gBP = (pieces.get('p').getLocation() | pieces.get('r').getLocation() | pieces.get('k').getLocation() | 
                    pieces.get('b').getLocation() | pieces.get('q').getLocation() | pieces.get('a').getLocation());
        return gBP;
    }

    static long getWhitePosition() {
        long gWP = (pieces.get('P').getLocation() | pieces.get('R').getLocation() | pieces.get('K').getLocation() | 
                    pieces.get('B').getLocation() | pieces.get('Q').getLocation() | pieces.get('A').getLocation());
        return gWP;
    }
    
    public static void initBoard() {
        initPieces();
        charBoardToBitBoard();
        possibleMovesWhite();
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
                    tmpPiece.addLocation(powerOf2(i));
                    break;
                case 'p':
                    tmpPiece = pieces.get('p');
                    tmpPiece.addLocation(powerOf2(i));
                    break;
                case 'K': 
                    tmpPiece = pieces.get('K');
                    tmpPiece.addLocation(powerOf2(i));
                    break;
                case 'k': 
                    tmpPiece = pieces.get('k');
                    tmpPiece.addLocation(powerOf2(i));
                    break;
                case 'B': 
                    tmpPiece = pieces.get('B');
                    tmpPiece.addLocation(powerOf2(i));
                    break;
                case 'b': 
                    tmpPiece = pieces.get('b');
                    tmpPiece.addLocation(powerOf2(i));
                    break;
                case 'R':
                    tmpPiece = pieces.get('R');
                    tmpPiece.addLocation(powerOf2(i));
                    break;
                case 'r': 
                    tmpPiece = pieces.get('r');
                    tmpPiece.addLocation(powerOf2(i));
                    break;
                case 'Q': 
                    tmpPiece = pieces.get('Q');
                    tmpPiece.addLocation(powerOf2(i));
                    break;
                case 'q': 
                    tmpPiece = pieces.get('q');
                    tmpPiece.addLocation(powerOf2(i));
                    break;
                case 'A': 
                    tmpPiece = pieces.get('A');
                    tmpPiece.addLocation(powerOf2(i));
                    break;
                case 'a': 
                    tmpPiece = pieces.get('a');
                    tmpPiece.addLocation(powerOf2(i));
                    break;
            }   
        }
    }

    static long powerOf2(int i) {
        long base = 1;
        return (base << i);
    }
}
