package pieceDef;

public class Pawn extends Piece {

    public Pawn(Side s) {
        location = 0L;
        team = s;
        isAlive = true;
    }

    //i need my location, I need where the enemy pieces are, and where the spaces ard where the edges of the
    //board are.
    /*public String getMoves() {
        return null;   
    }*/
}
