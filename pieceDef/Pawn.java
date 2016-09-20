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
        
        long twoHopW = 1095216660480L; 
        long twoHopB = 4278190080L; 
        long uBoard = 0L, u2Board = 0L, cRBoard = 0L, cLBoard = 0L;
        
        long allPieces = piecesB | piecesW;
        this.potentialCaptures = 0L;

        String moves = "";
        
        if(this.team == Side.WHITE) {
            //move up
            uBoard = (location>>8) &~ allPieces &~ topRow;
            //move up 2
            u2Board = (location>>16) & twoHopW &~ allPieces &~ (allPieces>>8);
            //capture right
            cRBoard = (location>>7) & piecesB &~ lSide &~ topRow;
            //capture left
            cLBoard = (location>>9) & piecesB &~ rSide &~ topRow;
        } else {
            //move up
            uBoard = (location<<8) &~ allPieces &~ bottomRow;
            //move up 2
            u2Board = (location<<16) & twoHopB &~ allPieces &~ (allPieces<<8);
            //capture right
            cRBoard = (location<<9) & piecesW &~ lSide &~ bottomRow;
            //capture left
            cLBoard = (location<<7) & piecesW &~ rSide &~ bottomRow;
        }

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
        if(this.team == Side.WHITE) {
            uBoard = (location>>8) &~ piecesB &~ piecesW & topRow;
            cRBoard = (location>>7) & piecesB &~ lSide & topRow;
            cLBoard = (location>>9) & piecesB &~ rSide & topRow;
        } else {
            uBoard = (location<<8) &~ piecesW &~ piecesW & bottomRow;
            cRBoard = (location<<9) & piecesW &~ lSide & bottomRow;
            cLBoard = (location<<7) & piecesW &~ rSide & bottomRow;
        }
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

        //update captures
        if(this.team == Side.WHITE) {
            cLBoard = (location>>9) &~ rSide;
            cRBoard = (location>>7) &~ lSide;
        } else {
            cRBoard = (location<<9) &~ lSide;
            cLBoard = (location<<7) &~ rSide;
        }
        this.potentialCaptures |= cRBoard | cLBoard;
        
        return moves;   
    }
}
