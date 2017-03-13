package pieceDef;
import java.util.Arrays;
import java.util.Stack;
import src.*;

public class Bishop extends Piece {

    public Bishop(Side s) {
        moveHistory = new Stack<Long>();
        location = 0L;
        team = s;
    }

    //returns all possible moved that can be made for a bishop accounting for pice blocking 
    public long getMoveBoard(long piecesB, long piecesW, long i) {
        long bishopMoves = 0L;
        long allPieces = piecesB | piecesW;

        bishopMoves = diagonalAntiDiagonalHQ(allPieces, (int)i); //contains the location of the bishop moves
        if(this.team == Side.WHITE) {
            bishopMoves = bishopMoves &~ piecesW;//all bishop moves without white collisions
        } else {
            bishopMoves = bishopMoves &~ piecesB;
        }
        return bishopMoves;
    }

    public int[] getMoves(long piecesB, long piecesW) {
        
        int[] tmpMoves = new int[218];
        int[] moves;
        int movesIndex = 0;

        long allPieces = piecesB | piecesW;

        for(int i = 0; i < 64; i++) { //look through the location for every location on board for the bishop
            long bishopMoves = 0L;
            if (((location>>i) & 1) == 1) { //we have found a rook!
                bishopMoves = getMoveBoard(piecesB, piecesW, i);
                for(int j = 0; j < 64; j++) {
                    if (((bishopMoves>>j) & 1) == 1) { //we have found a bishop move!!
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

