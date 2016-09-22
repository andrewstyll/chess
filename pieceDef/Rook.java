package pieceDef;

public class Rook extends Piece {

    public Rook(Side s) {
        location = 0L;
        team = s;
        isAlive = true;
        potentialCaptures = 0L;
    }
    
    public int[] getMoves(long piecesB, long piecesW) {
        
        int[] moves = new int[218];
        int movesIndex = 0;

        long allPieces = piecesB | piecesW;
        this.potentialCaptures = 0L;

        for(int i = 0; i < 64; i++) { //look through the location for every location on board for the rooks
            long rookMoves = 0L;
            if (((location>>i) & 1) == 1) { //we have found a rook!
                rookMoves = verticalHorizontalHQ(allPieces, i); //contains the location of the rook moves
                if(this.team == Side.WHITE) {
                    rookMoves = rookMoves &~ piecesW;
                } else {
                    rookMoves = rookMoves &~ piecesB;
                }
                this.potentialCaptures |= rookMoves;
                for(int j = 0; j < 64; j++) {
                    if (((rookMoves>>j) & 1) == 1) { //we have found a rook move!!
                        moves[movesIndex++] = encodeMove(i/8, i%8, j/8, j%8, ' ');
                    }
                }
            }
        }
        return moves;
    }
}

