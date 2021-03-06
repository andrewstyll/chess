package pieceDef;
import java.util.Arrays;
import java.util.Stack;
import src.*;

public class King extends Piece {

    private long kingMask;

    public King(Side s) {
        moveHistory = new Stack<Long>();
        location = 0L;
        team = s;
    }

    public long getMoveBoard(long piecesB, long piecesW, long i) {
        
        long above = 0L, same = 0L, below = 0L;
        long kingMoves = 0L;
        long king = 1L<<i;

        above = king>>7 | king>>8 | king>>9;
        same = king>>1 | king<<1;
        below = king<<7 | king<<8 | king<<9;
        
        kingMoves = above | same | below;
        if(i%8 < 1) {
            kingMoves = kingMoves &~ rSide;
        }
        if(i%8 > 6) {
            kingMoves = kingMoves &~ lSide;
        }
        if(this.team == Side.WHITE) {
            kingMoves = kingMoves &~ piecesW;
        } else {
            kingMoves = kingMoves &~ piecesB;
        }
        return kingMoves;
    }

    public int[] getMoves(long piecesB, long piecesW) {
        
        int[] tmpMoves = new int[218];
        int[] moves;
        int movesIndex = 0;

        long allPieces = piecesB | piecesW;

        for(int i = 0; i < 64; i++) { //look through the location for every location on board for the king
            long kingMoves = 0L;
            if (((location>>i) & 1) == 1) { //we have found a king!
                
                kingMoves = getMoveBoard(piecesB, piecesW, (long)i);
                for(int j = 0; j < 64; j++) {
                    if (((kingMoves>>j) & 1) == 1) { //we have found a king move!!
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

