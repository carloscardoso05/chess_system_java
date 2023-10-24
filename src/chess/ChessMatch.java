package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessMatch {
    private final Board board;
    private int turn;
    private Color currentPlayer;
    private boolean check;
    private final List<ChessPiece> capturedPieces = new ArrayList<>();
    private final List<ChessPiece> piecesOnBoard = new ArrayList<>();
    private boolean checkMate;
    private ChessPiece enPassantVulnerable;
    private ChessPiece promoted;

    public ChessMatch() {
        this.board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int row = 0; row < board.getRows(); row++) {
            for (int column = 0; column < board.getColumns(); column++) {
                mat[row][column] = (ChessPiece) board.piece(row, column);
            }
        }
        return mat;
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        ChessPiece capturedPiece = (ChessPiece) makeMove(source, target);
        if (testCheck(currentPlayer)) {
            undoMove(source, target, capturedPiece);
            throw new ChessException("You can't put yourself in check");
        }

        check = testCheck(opponent(currentPlayer));

        nextTurn();
        return capturedPiece;
    }

    private void nextTurn() {
        turn++;
        currentPlayer = opponent(currentPlayer);
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        ChessPosition chessPosition = new ChessPosition(column, row);
        Position position = chessPosition.toPosition();
        board.placePiece(piece, position);
        piecesOnBoard.add(piece);
    }

    private Piece makeMove(Position source, Position target) {
        Piece piece = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        if (capturedPiece != null) {
            piecesOnBoard.remove((ChessPiece) capturedPiece);
            capturedPieces.add((ChessPiece) capturedPiece);
        }
        board.placePiece(piece, target);
        return capturedPiece;
    }

    private void undoMove(Position source, Position target, ChessPiece capturedPiece) {
        ChessPiece piece = (ChessPiece) board.removePiece(target);
        board.placePiece(piece, source);
        if (capturedPiece != null) {
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnBoard.add(capturedPiece);
        }
    }

    private void validateSourcePosition(Position position) {
        if (!board.thereIsAPiece(position)) {
            throw new ChessException("There is no piece on source position " + position);
        }
        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessException("There is no possible move for the selected piece");
        }
        if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
            throw new ChessException("This piece is not yours");
        }
    }

    private void validateTargetPosition(Position source, Position target) {
        Piece piece = board.piece(source);
        if (!piece.possibleMove(target)) {
            throw new ChessException("This chosen piece can't move to target position");
        }
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    private Color opponent(Color color) {
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private ChessPiece king(Color color) {
        for (ChessPiece piece : piecesOnBoard) {
            if (piece.getColor() == color && piece instanceof King) {
                return piece;
            }
        }
        throw new IllegalStateException("There is no " + color + " King on the board");
    }

    private boolean testCheck(Color color) {
        Position kingPosition = king(color).getChessPosition().toPosition();
        List<ChessPiece> opponents = piecesOnBoard.stream().filter(piece -> piece.getColor() == opponent(color)).toList();
        for (ChessPiece opponent : opponents) {
            boolean[][] possibleMoves = opponent.possibleMoves();
            if (possibleMoves[kingPosition.getRow()][kingPosition.getColumn()]) {
                return true;
            }
        }
        return false;
    }

    private void initialSetup() {
        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('c', 2, new Queen(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Knight(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Queen(board, Color.BLACK));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Knight(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public List<ChessPiece> getCapturedPieces() {
        return capturedPieces;
    }

    public boolean isCheck() {
        return check;
    }
}
