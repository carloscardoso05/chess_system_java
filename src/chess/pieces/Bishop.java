package chess.pieces;

import boardgame.Board;
import boardgame.Direction;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {
    public Bishop(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public boolean[][] possibleMoves() {
        Board board = getBoard();
        int rows = board.getRows();
        int columns = board.getColumns();
        boolean[][] mat = new boolean[rows][columns];
        validateLineMoves(position, Direction.UPRIGHT.getDirection(), rows, mat);
        validateLineMoves(position, Direction.UPLEFT.getDirection(), rows, mat);
        validateLineMoves(position, Direction.DOWNRIGHT.getDirection(), rows, mat);
        validateLineMoves(position, Direction.DOWNLEFT.getDirection(), rows, mat);
        return mat;
    }
}
