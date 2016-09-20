package pieceDef;

public class Bishop extends Piece {

    public Bishop(Side s) {
        location = 0L;
        team = s;
        isAlive = true;
        potentialCaptures = 0L;
    }
    
    public String getMoves(long piecesB, long piecesW) {
        
        String moves = "";
        long allPieces = piecesB | piecesW;
        this.potentialCaptures = 0L;

        for(int i = 0; i < 64; i++) { //look through the location for every location on board for the rooks
            long bishopMoves = 0L;
            if (((location>>i) & 1) == 1) { //we have found a rook!
                bishopMoves = diagonalAntiDiagonalHQ(allPieces, i); //contains the location of the rook moves
                if(this.team == Side.WHITE) {
                    bishopMoves = bishopMoves &~ piecesW;
                    this.potentialCaptures |= bishopMoves & piecesB;
                } else {
                    bishopMoves = bishopMoves &~ piecesB;
                    this.potentialCaptures |= bishopMoves & piecesW;
                }
                for(int j = 0; j < 64; j++) {
                    if (((bishopMoves>>j) & 1) == 1) { //we have found a rook move!!
                        moves += "" + (i/8) + (i%8) + (j/8) + (j%8) + " ";
                    }
                }
            }
        }
        return moves;
    }
}

