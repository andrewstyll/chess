package pieceDef;

public class King extends Piece {

    private long kingMask;

    public King(Side s) {
        location = 0L;
        team = s;
        isAlive = true;
    }
    public String getMoves(long piecesB, long piecesW) {
        return "";
    }
}

