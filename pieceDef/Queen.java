package pieceDef;
import java.util.Arrays;
import java.util.Stack;
import src.*;

public class Queen extends Piece {

    public Queen(Side s) {
        moveHistory = new Stack<Long>();
        location = 0L;
        team = s;
    }
    
    public long getMoveBoard(long piecesB, long piecesW, long i) {
        long queenMoves = 0L;
        long allPieces = piecesB | piecesW;

        queenMoves = (verticalHorizontalHQ(allPieces, (int)i)|diagonalAntiDiagonalHQ(allPieces, (int)i)); //contains the location of the rook moves
        if(this.team == Side.WHITE) {
            queenMoves = queenMoves &~ piecesW;
        } else {
            queenMoves = queenMoves &~ piecesB;
        }
        return queenMoves;
    }

    public int[] getMoves(long piecesB, long piecesW) {
        
        int[] tmpMoves = new int[218];
        int[] moves;
        int movesIndex = 0;

        long allPieces = piecesB | piecesW;

        for(int i = 0; i < 64; i++) { //look through the location for every location on board for the queen
            long queenMoves = 0L;
            if (((location>>i) & 1) == 1) { //we have found a queen!
                queenMoves = getMoveBoard(piecesB, piecesW, (long)i);
                for(int j = 0; j < 64; j++) {
                    if (((queenMoves>>j) & 1) == 1) { //we have found a queen move!!
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

