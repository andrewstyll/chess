package pieceDef;
import java.util.Arrays;

public class Pawn extends Piece {

    public Pawn(Side s) {
        location = 0L;
        team = s;
        isAlive = true;
        potentialCaptures = 0L;
    }

    //i need my location, I need where the enemy pieces are, and where the spaces ard where the edges of the
    //board are.
    //y1 x1 y2 x2
    public String getMoves(long piecesB, long piecesW) {
        
        long twoHop = 1095216660480L; 
        long allPieces = piecesB | piecesW;
        this.potentialCaptures = 0L;

        String moves = "";
        //move up
        long uBoard = (location>>8) &~ allPieces &~ topRow;
        //move up 2
        long u2Board = (location>>16) & twoHop &~ allPieces &~ (allPieces>>8);
        //capture right
        long cRBoard = (location>>7) & piecesB &~ lSide &~ topRow;
        //capture left
        long cLBoard = (location>>9) & piecesB &~ rSide &~ topRow;

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
        
        this.potentialCaptures |= cRBoard | cLBoard;

        //again with promotions
        uBoard = (location>>8) &~ piecesB &~ piecesW & topRow;
        cRBoard = (location>>7) & piecesB &~ lSide & topRow;
        cLBoard = (location>>9) & piecesB &~ rSide & topRow;
        
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

        this.potentialCaptures |= cRBoard | cLBoard;
        
        return moves;   
    }
}
