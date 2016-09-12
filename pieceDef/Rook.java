package pieceDef;

public class Rook extends Piece {

    public Rook(Side s) {
        location = 0L;
        team = s;
        isAlive = true;
    }
    
    public String getMovesW(String history, long piecesB, long piecesW, long lSide, long rSide, long forwards) {
        return "";
    }
}

