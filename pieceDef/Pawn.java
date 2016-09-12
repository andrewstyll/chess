package pieceDef;

public class Pawn extends Piece {

    public Pawn(Side s) {
        location = 0L;
        team = s;
        isAlive = true;
    }

    //i need my location, I need where the enemy pieces are, and where the spaces ard where the edges of the
    //board are.
    //y1 x1 y2 x2
    public String getMovesW(String history, long piecesB, long piecesW, long lSide, long rSide, long promo) {
        

        String moves = "";
        //move up
        long uBoard = (location>>8) &~ piecesB &~ piecesW &~ forwards;
        //move up 2
        long u2Board = (location>>16) &~ 
        //capture right
        long cRBoard = (location>>7) & piecesB &~ rSide &~ forwards;
        //capture left
        long cLBoard = (location>>9) & piecesB &~ lSide &~ forwards;

        for(int i = 0; i < 64; i++) {
            if(((uBoard>>i) & 1) == 1) { //if this is a one, we've found a move
                moves += "" + (i/8+1) + (i%8) + (i/8) + (i%8) + " ";  
            }
            if(((cRBoard>>i) & 1) == 1) { //if this is a one, we've found a move
                moves += "" + (i/8+1) + (i%8-1) + (i/8) + (i%8) + " ";  
            }
            if(((cLBoard>>i) & 1) == 1) { //if this is a one, we've found a move
                moves += "" + (i/8+1) + (i%8+1) + (i/8) + (i%8) + " ";  
            }
        }

        //again with promotions
        uBoard = (location>>8) &~ piecesB &~ piecesW & promo;
        cRBoard = (location>>7) & piecesB &~ rSide & promo;
        cLBoard = (location>>9) & piecesB &~ lSide & promo;
        
        //x1 x2
        for(int i = 0; i < 64; i++) {
            if(((uBoard>>i) & 1) == 1) { //if this is a one, we've found a move
                moves += "" + (i%8) + (i%8) + "QP" + " " + (i%8) + (i%8) + "RP" + " " + (i%8) + (i%8) + "BP" + " " + (i%8) + (i%8) + "KP" + " ";  
            }
            if(((cRBoard>>i) & 1) == 1) { //if this is a one, we've found a move
                moves += "" + (i%8-1) + (i%8) + "QP" + " " + (i%8-1) + (i%8) + "RP" + " " + (i%8-1) + (i%8) + "BP" + " " + (i%8-1) + (i%8) + "KP" + " ";  
            }
            if(((cLBoard>>i) & 1) == 1) { //if this is a one, we've found a move
                moves += "" + (i%8+1) + (i%8) + "QP" + " " + (i%8+1) + (i%8) + "RP" + " " + (i%8+1) + (i%8) + "BP" + " " + (i%8+1) + (i%8) + "KP" + " ";  
            }
        }

        return moves;   
    }
}
