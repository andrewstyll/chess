import java.util.*;
import pieceDef.*;

public class Board {
    private static HashMap<Character, Piece> pieces = new HashMap<Character, Piece>();


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
        
        Moves.possibleMovesWhite(pieces);
        Moves.showMoves();
        Moves.clearMoves();
        Moves.possibleMovesBlack(pieces);
        Moves.showMoves();
        Moves.clearMoves();
    }
    
    //makes a move based on encoded input
    /*public static long makeMove(String move, long location) {
        int startLocation = 0, endLocation = 0;
        long base = 0L;

        if(move.charAt(3) != 'P') { //regular move
            startLocation = Character.getNumericValue(move.charAt(0))*8 + Character.getNumericValue(move.charAt(1));//starting location on grid
            endLocation = Character.getNumericValue(move.charAt(2))*8 + Character.getNumericValue(move.charAt(3));//starting location on grid
            if(((location>>startLocation)&1) == 1) { //remove piece existing on board (only true for correct board)
                base = powerOf2(startLocation);
                location &= ~base;
                base = powerOf2(endLocation);
                location |= base;
            } else { //remove deleted piece wherever it exists
                base = powerOf2(endLocation);
                location |= ~base;
            }
        } else { //pawn promotion //not only must i know black and white, i must also know what board i am adding the promoted pawn to
            
        }
    }*/

    //must get moves to update captures as captures are determined by potential moves 
    //store the moves differently, seperate them by their piece. for every move I will have to cycle through every
    //piece bitboard. I will need to know what color made the move.

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
        long base = 1L;
        return (base << i);
    }
    
    static void drawBoard(long num) {
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
