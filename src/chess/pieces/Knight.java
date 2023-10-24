package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {
    public Knight(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "N";
    }

    @Override
    public boolean[][] possibleMoves() {
        Board board = getBoard();
        int rows = board.getRows();
        int columns = board.getColumns();
        boolean[][] mat = new boolean[rows][columns];
        validateLineMoves(position, new Position(1, 2), 1, mat);
        validateLineMoves(position, new Position(2, 1), 1, mat);
        validateLineMoves(position, new Position(-1, 2), 1, mat);
        validateLineMoves(position, new Position(-2, 1), 1, mat);
        validateLineMoves(position, new Position(1, -2), 1, mat);
        validateLineMoves(position, new Position(2, -1), 1, mat);
        validateLineMoves(position, new Position(-1, -2), 1, mat);
        validateLineMoves(position, new Position(-2, -1), 1, mat);
        return mat;
    }
}
