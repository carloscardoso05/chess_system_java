package chess.pieces;

import boardgame.Board;
import boardgame.Direction;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
    }

    @Override
    public boolean[][] possibleMoves() {
        Board board = getBoard();
        int rows = board.getRows();
        int columns = board.getColumns();
        boolean[][] mat = new boolean[rows][columns];
        for (Direction direction : Direction.values()) {
            validateLineMoves(position, direction.getDirection(), 1, mat);
        }
        return mat;
    }
}
