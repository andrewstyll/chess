package pieceDef;

public class Rook extends Piece {

    public Rook(Side s) {
        location = 0L;
        team = s;
        isAlive = true;
    }
    
    public String getMoves(long piecesB, long piecesW) {
        
        String moves = "";
        long allPieces = piecesB | piecesW;

        for(int i = 0; i < 64; i++) { //look through the location for every location on board for the rooks
            long rookMoves = 0L;
            if (((location>>i) & 1) == 1) { //we have found a rook!
                rookMoves = verticalHorizontalHQ(allPieces, i); //contains the location of the rook moves
                
                for(int j = 0; j < 64; j++) {
                    if (((rookMoves>>j) & 1) == 1) { //we have found a rook move!!
                        moves += "" + (i/8) + (i%8) + (j/8) + (j%8) + " ";
                    }
                }
            }
        }
        return moves;
    }
}

