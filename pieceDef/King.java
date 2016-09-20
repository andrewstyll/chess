package pieceDef;
import java.util.Arrays;

public class King extends Piece {

    private long kingMask;

    public King(Side s) {
        location = 0L;
        team = s;
        isAlive = true;
        potentialCaptures = 0L;
    }

    private long kMoves(long i) {
        
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
        return kingMoves;
    }

    public String getMoves(long piecesB, long piecesW) {
        
        String moves = "";
        long allPieces = piecesB | piecesW;
        this.potentialCaptures = 0L;

        for(int i = 0; i < 64; i++) { //look through the location for every location on board for the king
            long kingMoves = 0L;
            if (((location>>i) & 1) == 1) { //we have found a king!
                
                kingMoves = kMoves(i);

                if(this.team == Side.WHITE) {
                    kingMoves = kingMoves &~ piecesW;
                } else {
                    kingMoves = kingMoves &~ piecesB;
                }
                this.potentialCaptures |= kingMoves;
                for(int j = 0; j < 64; j++) {
                    if (((kingMoves>>j) & 1) == 1) { //we have found a king move!!
                        moves += "" + (i/8) + (i%8) + (j/8) + (j%8) + " ";
                    }
                }
            }
        }
        return moves;
    }
}

