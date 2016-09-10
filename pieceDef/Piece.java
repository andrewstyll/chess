package pieceDef;

public abstract class Piece {
    public enum Side{BLACK, WHITE};

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
}
