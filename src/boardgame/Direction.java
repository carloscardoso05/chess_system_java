package boardgame;

public enum Direction {
    UP(new Position(-1, 0)),
    DOWN(new Position(1,0)),
    LEFT(new Position(0,-1)),
    RIGHT(new Position(0,1)),
    UPLEFT(new Position(-1,-1)),
    UPRIGHT(new Position(-1,1)),
    DOWNLEFT(new Position(1,-1)),
    DOWNRIGHT(new Position(1,1)),
    ;

    private final Position direction;
    Direction(Position direction) {
        this.direction = direction;
    }

    public Position getDirection() {
        return direction;
    }
}
