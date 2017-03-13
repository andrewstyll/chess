package pieceDef;
import java.util.Arrays;
import java.util.Stack;
import src.*;

public class Rook extends Piece {

    public Rook(Side s) {
        moveHistory = new Stack<Long>();
        location = 0L;
        team = s;
    }
   
    public long getMoveBoard(long piecesB, long piecesW, long i) { //i is the rook location
        long rookMoves = 0L;
        long allPieces = piecesB | piecesW;
        
        rookMoves = verticalHorizontalHQ(allPieces, (int)i); //contains the location of the rook moves
        if(this.team == Side.WHITE) {
            rookMoves = rookMoves &~ piecesW;
        } else {
            rookMoves = rookMoves &~ piecesB;
        }
        return rookMoves; 
    }

    public int[] getMoves(long piecesB, long piecesW) {
        
        int[] tmpMoves = new int[218];
        int[] moves;
        int movesIndex = 0;

        long allPieces = piecesB | piecesW;

        for(int i = 0; i < 64; i++) { //look through the location for every location on board for the rooks
            long rookMoves = 0L;
            if (((location>>i) & 1) == 1) { //we have found a rook!
                rookMoves = getMoveBoard(piecesB, piecesW, (long)i);
                for(int j = 0; j < 64; j++) {
                    if (((rookMoves>>j) & 1) == 1) { //we have found a rook move!!
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

