package pieceDef;

public class Rook extends Piece {

    public Rook(Side s) {
        location = 0L;
        team = s;
        isAlive = true;
        potentialCaptures = 0L;
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
        
        int[] moves = new int[218];
        int movesIndex = 0;

        long allPieces = piecesB | piecesW;
        this.potentialCaptures = 0L;

        for(int i = 0; i < 64; i++) { //look through the location for every location on board for the rooks
            long rookMoves = 0L;
            if (((location>>i) & 1) == 1) { //we have found a rook!
                rookMoves = getMoveBoard(piecesB, piecesW, (long)i);
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

