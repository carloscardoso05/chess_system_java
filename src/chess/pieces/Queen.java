package chess.pieces;

import boardgame.Board;
import boardgame.Direction;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece {

    public Queen(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "Q";
    }

    @Override
    public boolean[][] possibleMoves() {
        Board board = getBoard();
        int rows = board.getRows();
        int columns = board.getColumns();
        boolean[][] mat = new boolean[rows][columns];
        for (Direction direction : Direction.values()) {
            validateLineMoves(position, direction.getDirection(), 7, mat);
        }
        return mat;
    }
}
