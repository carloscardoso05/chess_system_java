package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

import java.util.function.Function;

public class Rook extends ChessPiece {
    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "R";
    }

    @Override
    public boolean[][] possibleMoves() {
        Board board = getBoard();
        int rows = board.getRows();
        int columns = board.getColumns();
        boolean[][] mat = new boolean[rows][columns];// up
        // up
        validateLineMoves(position, new Position(-1, 0), rows, mat);
        // down
        validateLineMoves(position, new Position(1, 0), rows, mat);
        //left
        validateLineMoves(position, new Position(0, -1), columns, mat);
        //right
        validateLineMoves(position, new Position(0, 1), columns, mat);
        return mat;
    }
}
