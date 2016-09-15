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
    public String getMoves(long piecesB, long piecesW) {
        
        long twoHop = 1095216660480L; 
        long allPieces = piecesB | piecesW;

        String moves = "";
        //move up
        //long uBoard = (location>>8) &~ piecesB &~ piecesW &~ promo;
        long uBoard = (location>>8) &~ allPieces &~ topRow;
        //move up 2
        long u2Board = (location>>16) & twoHop &~ allPieces &~ (allPieces>>8);
        //capture right
        long cRBoard = (location>>7) & piecesB &~ rSide &~ topRow;
        //capture left
        long cLBoard = (location>>9) & piecesB &~ lSide &~ topRow;

        for(int i = 0; i < 64; i++) {
            if(((uBoard>>i) & 1) == 1) { //if this is a one, we've found a move
                moves += "" + (i/8+1) + (i%8) + (i/8) + (i%8) + " ";  
            }
            if(((u2Board>>i) & 1) == 1) { //if this is a one, we've found a move
                moves += "" + (i/8+2) + (i%8) + (i/8) + (i%8) + " ";  
            }
            if(((cRBoard>>i) & 1) == 1) { //if this is a one, we've found a move
                moves += "" + (i/8+1) + (i%8-1) + (i/8) + (i%8) + " ";  
            }
            if(((cLBoard>>i) & 1) == 1) { //if this is a one, we've found a move
                moves += "" + (i/8+1) + (i%8+1) + (i/8) + (i%8) + " ";  
            }
        }

        //again with promotions
        uBoard = (location>>8) &~ piecesB &~ piecesW & topRow;
        cRBoard = (location>>7) & piecesB &~ rSide & topRow;
        cLBoard = (location>>9) & piecesB &~ lSide & topRow;
        
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
