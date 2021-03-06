package pieceDef;
import java.util.Arrays;
import java.util.Stack;
import src.*;

public class Knight extends Piece {

    public Knight(Side s) {
        moveHistory = new Stack<Long>();
        location = 0L;
        team = s;
    }
    
    public long getMoveBoard(long piecesB, long piecesW, long i) {
        
        long NE = 0L, SE = 0L, NW = 0L, SW = 0L;
        long knightMoves = 0L;
        long knight = 1L<<i;

        
        NE = knight>>6 | knight>>15;
        SE = knight<<10 | knight<<17;
        NW = knight>>10 | knight>>17;
        SW = knight<<6 | knight<<15;
        
        knightMoves = NE | SE | NW | SW;
        if(i%8 < 2) {
            knightMoves = knightMoves &~ rSide;
            if(i%8 < 1) {
                knightMoves = knightMoves &~ (rSide>>1);
            }
        }
        if(i%8 > 5) {
            knightMoves = knightMoves &~ lSide;
            if(i%8 > 6) {
                knightMoves = knightMoves &~ (lSide<<1);
            }
        }
        if(this.team == Side.WHITE) {
            knightMoves = knightMoves &~ piecesW;
        } else {
            knightMoves = knightMoves &~ piecesB;
        }
        return knightMoves;
    }

    public int[] getMoves(long piecesB, long piecesW) {
        
        int[] tmpMoves = new int[218];
        int[] moves;
        int movesIndex = 0;

        long allPieces = piecesB | piecesW;

        for(int i = 0; i < 64; i++) { //look through the location for every location on board for the knight
            long knightMoves = 0L;
            if (((location>>i) & 1) == 1) { //we have found a knight!
                
                knightMoves = getMoveBoard(piecesB, piecesW, i);
                for(int j = 0; j < 64; j++) {
                    if (((knightMoves>>j) & 1) == 1) { //we have found a knight move!!
                        tmpMoves[movesIndex++] = Moves.encodeMove(i/8, i%8, j/8, j%8, ' ');
                    }
                }
            }
        }
        if(movesIndex > 0) {
            moves = Arrays.copyOfRange(tmpMoves, 0, movesIndex);
        } else {
            //there is nothing to copy
            moves = Arrays.copyOfRange(tmpMoves, 0, 0);
        }
        return moves;
    }
}

