package boardgame;

import java.util.Arrays;

public abstract class Piece {
    protected Position position;
    private final Board board;
    public Piece(Board board) {
        this.board = board;
    }

    public abstract boolean[][] possibleMoves();

    public boolean possibleMove(Position position) {
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public boolean isThereAnyPossibleMove() {
        final boolean[][] mat = possibleMoves();
        for (boolean[] booleans : mat) {
            for (boolean possible : booleans) {
                if (possible) return true;
            }
        }
        return false;
    }

    protected Board getBoard() {
        return board;
    }
}
