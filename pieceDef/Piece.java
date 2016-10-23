package pieceDef;
import java.util.*;

public abstract class Piece {
    public enum Side{BLACK, WHITE};
   
    //these are the same for every piece
    protected long rSide = -9187201950435737472L;
    protected long lSide = 72340172838076673L;
    protected long topRow = 255L;
    protected long bottomRow = -72057594037927936L;

    protected Stack<Long> moveHistory;
    protected long location;
    protected Side team;

    //keep in mind, should match the system used by the board
    private long[] verticalMask = {
        0x101010101010101L, 0x202020202020202L, 0x404040404040404L, 0x808080808080808L,
        0x1010101010101010L, 0x2020202020202020L, 0x4040404040404040L, 0x8080808080808080L
    };
    private long[] horizontalMask = {
        0xFFL, 0xFF00L, 0xFF0000L, 0xFF000000L, 0xFF00000000L, 
        0xFF0000000000L, 0xFF000000000000L, 0xFF00000000000000L   
    };
    private long[] diagonalMask = {
        0x1L, 0x102L, 0x10204L, 0x1020408L, 0x102040810L, 0x10204081020L, 0x1020408102040L, 0x102040810204080L,
        0x204081020408000L, 0x408102040800000L, 0x810204080000000L, 0x1020408000000000L, 0x2040800000000000L, 
        0x4080000000000000L, 0x8000000000000000L
    };
    private long[] antiDiagonalMask = {
        0x80L, 0x8040L, 0x804020L, 0x80402010L, 0x8040201008L, 0x804020100804L, 0x80402010080402L, 
        0x8040201008040201L, 0x4020100804020100L, 0x2010080402010000L, 0x1008040201000000L, 
        0x804020100000000L, 0x402010000000000L, 0x201000000000000L, 0x100000000000000L
    };

    //so given a position and occupied spaces grid...
    protected long verticalHorizontalHQ(long occupied, int index) { //take in the index of the square(so like 0->63)
        long left, right, vertical, horizontal;

        long base = 1L;
        long slider = base << index; //just like the init
        
        left = (occupied&verticalMask[index%8]) - 2*slider; 
        right = Long.reverse(Long.reverse(occupied&verticalMask[index%8]) - 2*Long.reverse(slider));
        vertical = left^right;

        left = (occupied - 2 * slider);
        right = Long.reverse((Long.reverse(occupied)-2*Long.reverse(slider)));
        horizontal = left^right;
        
        return (vertical&verticalMask[index%8])|(horizontal&horizontalMask[index/8]);
    }

    protected long diagonalAntiDiagonalHQ(long occupied, int index) {
        long left, right, diag, antiDiag;

        long base = 1L;
        long slider = base << index;

        left = (occupied&diagonalMask[index%8 + index/8]) - 2*slider; 
        right = Long.reverse(Long.reverse(occupied&diagonalMask[index%8 + index/8]) - 2*Long.reverse(slider));
        diag = left^right;

        left = (occupied&antiDiagonalMask[index/8 - index%8 + 7]) - 2*slider; 
        right = Long.reverse(Long.reverse(occupied&antiDiagonalMask[index/8 - index%8 + 7]) - 2*Long.reverse(slider));
        antiDiag = left^right;
    
        return (diag&diagonalMask[index%8 + index/8])|(antiDiag&antiDiagonalMask[index/8 - index%8 + 7]);
    }
    
    //return either the default location of the piece if the stack is empty, or the most recent "current" location
    public long getLocation() {
        if(moveHistoryEmpty()) {
            return location;
        } else {
            return moveHistory.peek(); 
        }
    }

    public void addLocation(long l) {
        location += l;
    }

    public void setLocation(long l) {
        location = l;
    }

    public Side getSide() {
        return team;
    }

    //i need this to update with the current location, so I can't really store it unless i want to use a stack..
    public int getPieceCount() {
        int count = 0;
        long board = getLocation();
        for(int i = 0; i < 64; i++) {
            if(((board>>i) & 1) == 1) {
                count++;
            }
        }
        return count;
    }

    public boolean moveHistoryEmpty() {
        return moveHistory.empty();
    }

    public void pushMove(long move) {
        moveHistory.push(move);
    }

    public void popMove() {
        moveHistory.pop();
    }

    //This returns a bitboard storing all of the potential locations a piece can move to  
    public abstract long getMoveBoard(long piecesB, long piecesW, long i);
    //These are all the moves that can be made for a certain piece
    public abstract int[] getMoves(long a, long b);
}
