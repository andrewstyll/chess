package pieceDef;

public class Rook extends Piece {

    public Rook(Side s) {
        location = 0L;
        team = s;
        isAlive = true;
    }
    
    public String getMoves(long piecesB, long piecesW) {
        return "";
    }
}

