package pieceDef;
import java.util.Stack;
import src.*;

public class Bishop extends Piece {

    public Bishop(Side s) {
        moveHistory = new Stack<Long>();
        location = 0L;
        team = s;
    }
    
    public long getMoveBoard(long piecesB, long piecesW, long i) {
        long bishopMoves = 0L;
        long allPieces = piecesB | piecesW;

        bishopMoves = diagonalAntiDiagonalHQ(allPieces, (int)i); //contains the location of the rook moves
        if(this.team == Side.WHITE) {
            bishopMoves = bishopMoves &~ piecesW;//all bishop moves without white collisions
        } else {
            bishopMoves = bishopMoves &~ piecesB;
        }
        return bishopMoves;
    }

    public int[] getMoves(long piecesB, long piecesW) {
        
        int[] moves = new int[218];
        int movesIndex = 0;

        long allPieces = piecesB | piecesW;

        for(int i = 0; i < 64; i++) { //look through the location for every location on board for the rooks
            long bishopMoves = 0L;
            if (((location>>i) & 1) == 1) { //we have found a rook!
                bishopMoves = getMoveBoard(piecesB, piecesW, i);
                for(int j = 0; j < 64; j++) {
                    if (((bishopMoves>>j) & 1) == 1) { //we have found a rook move!!
                        moves[movesIndex++] = Moves.encodeMove(i/8, i%8, j/8, j%8, ' ');
                    }
                }
            }
        }
        return moves;
    }
}

