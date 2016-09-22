package pieceDef;

public class Knight extends Piece {

    public Knight(Side s) {
        location = 0L;
        team = s;
        isAlive = true;
        potentialCaptures = 0L;
    }
    
    private long kMoves(long i) {
        
        long NE = 0L, SE = 0L, NW = 0L, SW = 0L;
        long knightMoves = 0L;
        long knight = 1L<<i;

        
        NE = knight>>6 | knight>>15;
        SE = knight<<10 | knight<<17;
        NW = knight>>10 | knight>>17;
        SW = knight<<6 | knight<<15;
        
        knightMoves = NE | SE | NW | SW;
        if(i%8 < 2) {
            knightMoves = knightMoves &~ rSide;
            if(i%8 < 1) {
                knightMoves = knightMoves &~ (rSide>>1);
            }
        }
        if(i%8 > 5) {
            knightMoves = knightMoves &~ lSide;
            if(i%8 > 6) {
                knightMoves = knightMoves &~ (lSide<<1);
            }
        }
        return knightMoves;
    }

    public int[] getMoves(long piecesB, long piecesW) {
        
        int[] moves = new int[218];
        int movesIndex = 0;

        long allPieces = piecesB | piecesW;
        this.potentialCaptures = 0L;

        for(int i = 0; i < 64; i++) { //look through the location for every location on board for the knight
            long knightMoves = 0L;
            if (((location>>i) & 1) == 1) { //we have found a knight!
                
                knightMoves = kMoves(i);

                if(this.team == Side.WHITE) {
                    knightMoves = knightMoves &~ piecesW;
                } else {
                    knightMoves = knightMoves &~ piecesB;
                }
                this.potentialCaptures |= knightMoves;
                for(int j = 0; j < 64; j++) {
                    if (((knightMoves>>j) & 1) == 1) { //we have found a knight move!!
                        moves[movesIndex++] = encodeMove(i/8, i%8, j/8, j%8, ' ');
                    }
                }
            }
        }
        return moves;
    }
}

