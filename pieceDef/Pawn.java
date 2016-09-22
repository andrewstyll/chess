package pieceDef;
import java.util.Arrays;

public class Pawn extends Piece {

    public Pawn(Side s) {
        location = 0L;
        team = s;
        isAlive = true;
        potentialCaptures = 0L;
    }

    //y1 x1 y2 x2
    public int[] getMoves(long piecesB, long piecesW) {
        
        int[] moves = new int[218];
        int movesIndex = 0;

        long twoHopW = 1095216660480L; 
        long twoHopB = 4278190080L; 
        long uBoard = 0L, u2Board = 0L, cRBoard = 0L, cLBoard = 0L;
        
        long allPieces = piecesB | piecesW;
        this.potentialCaptures = 0L;

        if(this.team == Side.WHITE) {
            //move up
            uBoard = (location>>8) &~ allPieces &~ topRow;
            //move up 2
            u2Board = (location>>16) & twoHopW &~ allPieces &~ (allPieces>>8);
            //capture right
            cRBoard = (location>>7) & piecesB &~ lSide &~ topRow;
            //capture left
            cLBoard = (location>>9) & piecesB &~ rSide &~ topRow;
            
            for(int i = 0; i < 64; i++) {
                if(((uBoard>>i) & 1) == 1) { //if this is a one, we've found a move
                    moves[movesIndex++] = encodeMove(i/8+1, i%8, i/8, i%8, ' ');
                }
                if(((u2Board>>i) & 1) == 1) { //if this is a one, we've found a move
                    moves[movesIndex++] = encodeMove(i/8+2, i%8, i/8, i%8, ' ');
                }
                if(((cRBoard>>i) & 1) == 1) { //if this is a one, we've found a move
                    moves[movesIndex++] = encodeMove(i/8+1, i%8-1, i/8, i%8, ' ');
                }
                if(((cLBoard>>i) & 1) == 1) { //if this is a one, we've found a move
                    moves[movesIndex++] = encodeMove(i/8+1, i%8+1, i/8, i%8, ' ');
                }
            }
        
            uBoard = (location>>8) &~ piecesB &~ piecesW & topRow;
            cRBoard = (location>>7) & piecesB &~ lSide & topRow;
            cLBoard = (location>>9) & piecesB &~ rSide & topRow;
       
            for(int i = 0; i < 64; i++) {
                if(((uBoard>>i) & 1) == 1) { //if this is a one, we've found a move
                    moves[movesIndex++] = encodeMove(1, i%8, 0, i%8, 'Q');
                    moves[movesIndex++] = encodeMove(1, i%8, 0, i%8, 'R');
                    moves[movesIndex++] = encodeMove(1, i%8, 0, i%8, 'B');
                    moves[movesIndex++] = encodeMove(1, i%8, 0, i%8, 'K');
                }
                if(((cRBoard>>i) & 1) == 1) { //if this is a one, we've found a move
                    moves[movesIndex++] = encodeMove(1, i%8-1, 0, i%8, 'Q');
                    moves[movesIndex++] = encodeMove(1, i%8-1, 0, i%8, 'R');
                    moves[movesIndex++] = encodeMove(1, i%8-1, 0, i%8, 'B');
                    moves[movesIndex++] = encodeMove(1, i%8-1, 0, i%8, 'K');
                }
                if(((cLBoard>>i) & 1) == 1) { //if this is a one, we've found a move
                    moves[movesIndex++] = encodeMove(1, i%8+1, 0, i%8, 'Q');
                    moves[movesIndex++] = encodeMove(1, i%8+1, 0, i%8, 'R');
                    moves[movesIndex++] = encodeMove(1, i%8+1, 0, i%8, 'B');
                    moves[movesIndex++] = encodeMove(1, i%8+1, 0, i%8, 'K');
                }
            }

            cLBoard = (location>>9) &~ rSide;
            cRBoard = (location>>7) &~ lSide;
        } else {
            //move up
            uBoard = (location<<8) &~ allPieces &~ bottomRow;
            //move up 2
            u2Board = (location<<16) & twoHopB &~ allPieces &~ (allPieces<<8);
            //capture right
            cRBoard = (location<<9) & piecesW &~ lSide &~ bottomRow;
            //capture left
            cLBoard = (location<<7) & piecesW &~ rSide &~ bottomRow;
            
            for(int i = 0; i < 64; i++) {
                if(((uBoard>>i) & 1) == 1) { //if this is a one, we've found a move
                    moves[movesIndex++] = encodeMove(i/8-1, i%8, i/8, i%8, ' ');
                }
                if(((u2Board>>i) & 1) == 1) { //if this is a one, we've found a move
                    moves[movesIndex++] = encodeMove(i/8-2, i%8, i/8, i%8, ' ');
                }
                if(((cRBoard>>i) & 1) == 1) { //if this is a one, we've found a move
                    moves[movesIndex++] = encodeMove(i/8-1, i%8-1, i/8, i%8, ' ');
                }
                if(((cLBoard>>i) & 1) == 1) { //if this is a one, we've found a move
                    moves[movesIndex++] = encodeMove(i/8-1, i%8+1, i/8, i%8, ' ');
                }
            }
        
            uBoard = (location<<8) &~ piecesW &~ piecesW & bottomRow;
            cRBoard = (location<<9) & piecesW &~ lSide & bottomRow;
            cLBoard = (location<<7) & piecesW &~ rSide & bottomRow;
            
            for(int i = 0; i < 64; i++) {
                if(((uBoard>>i) & 1) == 1) { //if this is a one, we've found a move
                    moves[movesIndex++] = encodeMove(6, i%8, 7, i%8, 'Q');
                    moves[movesIndex++] = encodeMove(6, i%8, 7, i%8, 'R');
                    moves[movesIndex++] = encodeMove(6, i%8, 7, i%8, 'B');
                    moves[movesIndex++] = encodeMove(6, i%8, 7, i%8, 'K');
                }
                if(((cRBoard>>i) & 1) == 1) { //if this is a one, we've found a move
                    moves[movesIndex++] = encodeMove(6, i%8-1, 7, i%8, 'Q');
                    moves[movesIndex++] = encodeMove(6, i%8-1, 7, i%8, 'R');
                    moves[movesIndex++] = encodeMove(6, i%8-1, 7, i%8, 'B');
                    moves[movesIndex++] = encodeMove(6, i%8-1, 7, i%8, 'K');
                }
                if(((cLBoard>>i) & 1) == 1) { //if this is a one, we've found a move
                    moves[movesIndex++] = encodeMove(6, i%8+1, 7, i%8, 'Q');
                    moves[movesIndex++] = encodeMove(6, i%8+1, 7, i%8, 'R');
                    moves[movesIndex++] = encodeMove(6, i%8+1, 7, i%8, 'B');
                    moves[movesIndex++] = encodeMove(6, i%8+1, 7, i%8, 'K');
                }
            }
            
            cRBoard = (location<<9) &~ lSide;
            cLBoard = (location<<7) &~ rSide;
        }

        this.potentialCaptures |= cRBoard | cLBoard;
        
        return moves;   
    }
}
