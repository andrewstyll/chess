package pieceDef;

public class Queen extends Piece {

    public Queen(Side s) {
        location = 0L;
        team = s;
        isAlive = true;
    }
    public String getMoves(long piecesB, long piecesW) {
        return "";
    }
}

