package pieceDef;

public class Queen extends Piece {

    public Queen(Side s) {
        location = 0L;
        team = s;
        isAlive = true;
    }
    
    public String getMoves(long piecesB, long piecesW) {
        
        String moves = "";
        long allPieces = piecesB | piecesW;

        for(int i = 0; i < 64; i++) { //look through the location for every location on board for the rooks
            long queenMoves = 0L;
            if (((location>>i) & 1) == 1) { //we have found a rook!
                queenMoves = (verticalHorizontalHQ(allPieces, i)|diagonalAntiDiagonalHQ(allPieces, i)); //contains the location of the rook moves
                if(this.team == Side.WHITE) {
                    queenMoves = queenMoves &~ piecesW;
                } else {
                    queenMoves = queenMoves &~ piecesB;
                }
                for(int j = 0; j < 64; j++) {
                    if (((queenMoves>>j) & 1) == 1) { //we have found a rook move!!
                        moves += "" + (i/8) + (i%8) + (j/8) + (j%8) + " ";
                    }
                }
            }
        }
        return moves;
    }
}

