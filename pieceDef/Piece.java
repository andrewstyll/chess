package pieceDef;
import java.util.Arrays;

public abstract class Piece {
    public enum Side{BLACK, WHITE};

    //keep in mind, should match the system used by the board
    protected long[] verticalMask = {
        0x101010101010101L, 0x202020202020202L, 0x404040404040404L, 0x808080808080808L,
        0x1010101010101010L, 0x2020202020202020L, 0x4040404040404040L, 0x8080808080808080L
    };
    protected long[] horizontalMask = {
        0xFFL, 0xFF00L, 0xFF0000L, 0xFF000000L, 0xFF00000000L, 
        0xFF0000000000L, 0xFF000000000000L, 0xFF00000000000000L   
    };
    protected long[] diagonalMask = {
        0x1L, 0x102L, 0x10204L, 0x1020408L, 0x102040810L, 0x10204081020L, 0x1020408102040L, 0x102040810204080L,
        0x204081020408000L, 0x408102040800000L, 0x810204080000000L, 0x1020408000000000L, 0x2040800000000000L, 
        0x4080000000000000L, 0x8000000000000000L
    };
    protected long[] antiDiagonalMask = {
        0x80L, 0x8040L, 0x804020L, 0x80402010L, 0x8040201008L, 0x804020100804L, 0x80402010080402L, 
        0x8040201008040201L, 0x4020100804020100L, 0x2010080402010000L, 0x1008040201000000L, 
        0x804020100000000L, 0x402010000000000L, 0x201000000000000L, 0x100000000000000L
    };

    protected long location;
    protected Side team;
    protected boolean isAlive;

    public long getLocation() {
        return location;
    }
    public void addLocation(long l) {
        location += l;
    }

    public Side getSide() {
        return team;
    }

    public boolean getAlive() {
        return isAlive;
    }

    //so given a p
    public long verticalHorizontalHQ(long occupied, int index) { //take in the index of the square(so like 0->63)
        long left, right, vertical, horizontal;

        long base = 1L;
        long slider = base << index; //just like the init
        
        left = (occupied&verticalMask[index%8]) - 2*slider; 
        right = Long.reverse(Long.reverse(occupied&verticalMask[index%8]) - 2*Long.reverse(slider));
        vertical = left^right;

        left = (occupied - 2 * slider);
        right = Long.reverse((Long.reverse(occupied)-2*Long.reverse(slider)));
        horizontal = left^right;
        
        return(vertical&verticalMask[index%8]);
    }

    public long diagonalAntiDiagonalHQ(long occupied, int index) {
        long left, right, diag, antiDiag;

        long base = 1;
        long slider = base << index;

        left = (occupied&diagonalMask[index%8 + index/8]) - 2*slider; 
        right = Long.reverse(Long.reverse(occupied&diagonalMask[index%8 + index/8]) - 2*Long.reverse(slider));
        diag = left^right;

        left = (occupied&antiDiagonalMask[index/8 - index%8 + 7]) - 2*slider; 
        right = Long.reverse(Long.reverse(occupied&antiDiagonalMask[index/8 - index%8 + 7]) - 2*Long.reverse(slider));
        antiDiag = left^right;
    
        drawBitBoard((diag&diagonalMask[index%8 + index/8])|(antiDiag&antiDiagonalMask[index/8 - index%8 + 7]));    
        return (diag&diagonalMask[index%8 + index/8])|(antiDiag&antiDiagonalMask[index/8 - index%8 + 7]);
    }

    public abstract String getMovesW(String h, long a, long b, long c, long d, long e);
}
