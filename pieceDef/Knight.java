package pieceDef;

public class Knight extends Piece {

    public Knight(Side s) {
        location = 0L;
        team = s;
        isAlive = true;
    }
    public String getMoves(long piecesB, long piecesW) {
        return "";
    }
}

