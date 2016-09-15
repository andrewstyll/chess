package pieceDef;

public class Bishop extends Piece {

    public Bishop(Side s) {
        location = 0L;
        team = s;
        isAlive = true;
    }
    public String getMoves(long piecesB, long piecesW) {
        return "";
    }
}

