package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
    private final Color color;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public ChessPosition getChessPosition() {
        return ChessPosition.fromPosition(this.position);
    }

    public Color getColor() {
        return color;
    }

    protected boolean isThereOpponentPiece(Position position) {
        ChessPiece piece = (ChessPiece) getBoard().piece(position);
        return piece != null && piece.getColor() != this.color;
    }

    protected void validateLineMoves(Position position, Position offsetDir, int distance, boolean[][] possibleMoves) {
        Board board = getBoard();
        for (int i = 1; i <= distance; i++) {
            Position offset = new Position(offsetDir.getRow() * i, offsetDir.getColumn() * i);
            Position relativePos = position.getRelativePosition(offset);
            if (!board.positionExists(relativePos)) break;
            if (board.thereIsAPiece(relativePos) && !isThereOpponentPiece(relativePos)) break;
            possibleMoves[relativePos.getRow()][relativePos.getColumn()] = true;
            if (isThereOpponentPiece(relativePos)) break;
        }
    }
}
