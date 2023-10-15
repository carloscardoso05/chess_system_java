package chess.pieces;

import boardgame.Board;
import boardgame.Direction;
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
        validateLineMoves(position, Direction.UP.getDirection(), rows, mat);
        // down
        validateLineMoves(position, Direction.DOWN.getDirection(), rows, mat);
        //left
        validateLineMoves(position, Direction.LEFT.getDirection(), columns, mat);
        //right
        validateLineMoves(position, Direction.RIGHT.getDirection(), columns, mat);
        return mat;
    }
}
