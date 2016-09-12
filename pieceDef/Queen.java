package pieceDef;

public class Queen extends Piece {

    public Queen(Side s) {
        location = 0L;
        team = s;
        isAlive = true;
    }
    public String getMovesW(String history, long piecesB, long piecesW, long lSide, long rSide, long forwards) {
        return "";
    }
}

