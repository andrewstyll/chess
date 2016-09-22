import java.util.*;
import pieceDef.*;
// what is the purpose of the move class....
// the purpose of this class is to handle the movements of all of the pieces
// this class should get the moves available to all of the pieces, and compile them inside of the moves array.
// each move will be stored as an integer. starting(number between 0 and 64), ending(number between 0 and 64)
// captured and 

public class Moves {

    static int MAX_MOVES = 218; //this is supposed to be the maximum number of moves for any position

    static int[] moves = new int[MAX_MOVES];
    
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
   
    // i want to store this in an array. I need to come up with values so that when I 
    // how will I make moves?
    public static void possibleMovesWhite(HashMap<Character, Piece> pieces) {

        int[] pawnMoves = new int[MAX_MOVES];
        int[] rookMoves = new int[MAX_MOVES];
        int[] knightMoves = new int[MAX_MOVES];
        int[] bishopMoves = new int[MAX_MOVES];
        int[] queenMoves = new int[MAX_MOVES];
        int[] kingMoves = new int[MAX_MOVES];

        long piecesB = getBlackPosition(pieces);
        long piecesW = getWhitePosition(pieces);
        
        pawnMoves = pieces.get('P').getMoves(piecesB, piecesW);
        rookMoves = pieces.get('R').getMoves(piecesB, piecesW);
        knightMoves = pieces.get('K').getMoves(piecesB, piecesW);
        bishopMoves = pieces.get('B').getMoves(piecesB, piecesW);
        queenMoves = pieces.get('Q').getMoves(piecesB, piecesW);
        kingMoves = pieces.get('A').getMoves(piecesB, piecesW);
    
        compileMoves(pawnMoves, rookMoves, knightMoves, bishopMoves, queenMoves, kingMoves);
    }
    
    public static void possibleMovesBlack(HashMap<Character, Piece> pieces) {

        int[] pawnMoves = new int[MAX_MOVES];
        int[] rookMoves = new int[MAX_MOVES];
        int[] knightMoves = new int[MAX_MOVES];
        int[] bishopMoves = new int[MAX_MOVES];
        int[] queenMoves = new int[MAX_MOVES];
        int[] kingMoves = new int[MAX_MOVES];
        
        long piecesB = getBlackPosition(pieces);
        long piecesW = getWhitePosition(pieces);
        
        pawnMoves = pieces.get('p').getMoves(piecesB, piecesW);
        rookMoves = pieces.get('r').getMoves(piecesB, piecesW);
        knightMoves = pieces.get('k').getMoves(piecesB, piecesW);
        bishopMoves = pieces.get('b').getMoves(piecesB, piecesW);
        queenMoves = pieces.get('q').getMoves(piecesB, piecesW);
        kingMoves = pieces.get('a').getMoves(piecesB, piecesW);

        compileMoves(pawnMoves, rookMoves, knightMoves, bishopMoves, queenMoves, kingMoves);
    }
    
    static long getBlackPosition(HashMap<Character, Piece> pieces) {
        long gBP = (pieces.get('p').getLocation() | pieces.get('r').getLocation() | pieces.get('k').getLocation() | 
                    pieces.get('b').getLocation() | pieces.get('q').getLocation() | pieces.get('a').getLocation());
        return gBP;
    }

    static long getWhitePosition(HashMap<Character, Piece> pieces) {
        long gWP = (pieces.get('P').getLocation() | pieces.get('R').getLocation() | pieces.get('K').getLocation() | 
                    pieces.get('B').getLocation() | pieces.get('Q').getLocation() | pieces.get('A').getLocation());
        return gWP;
    }

    //all places on board where a piece can be captured (use to check king check status)
    static long getBlackCaptures(HashMap<Character, Piece> pieces) {
        long gBC = (pieces.get('p').getPCaptures() | pieces.get('r').getPCaptures() | pieces.get('k').getPCaptures() | 
                    pieces.get('b').getPCaptures() | pieces.get('q').getPCaptures() | pieces.get('a').getPCaptures());
        return gBC;
    }
    
    static long getWhiteCaptures(HashMap<Character, Piece> pieces) {
        long gWC = (pieces.get('P').getPCaptures() | pieces.get('R').getPCaptures() | pieces.get('K').getPCaptures() | 
                    pieces.get('B').getPCaptures() | pieces.get('Q').getPCaptures() | pieces.get('A').getPCaptures());
        return gWC;
    }

    private static void compileMoves(int[] p, int[] r, int[] k, int[] b, int[] q, int[] a) {
        int pI = 0, rI = 0, kI = 0, bI = 0, qI = 0, aI = 0;
        for(int i = 0; i < MAX_MOVES; i++) {
            if (p[pI] != 0 && pI < MAX_MOVES) {
                moves[i] = p[pI++];
            } else if (r[rI] != 0 && rI < MAX_MOVES) {
                moves[i] = r[rI++];
            } else if (k[kI] != 0  && kI < MAX_MOVES) {
                moves[i] = k[kI++];
            } else if (b[bI] != 0 && bI < MAX_MOVES) {
                moves[i] = b[bI++];
            } else if (q[qI] != 0 && qI < MAX_MOVES) {
                moves[i] = q[qI++];
            } else if (a[aI] != 0 && aI < MAX_MOVES) {
                moves[i] = a[aI++];
            } else {
                break;
            }
        }
    }

    public static void showMoves() {
        for(int i = 0; i < MAX_MOVES; i++) {
            if(moves[i] != 0) {
                System.out.print(Integer.toHexString(moves[i]) + " ");
            }
        }
        System.out.println("");
    }

    public static void clearMoves() {
        for(int i = 0; i < MAX_MOVES; i++) {
            moves[i] = 0;
        }
    }
}
