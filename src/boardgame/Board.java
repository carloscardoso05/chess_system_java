package boardgame;

public class Board {
    private int rows;
    private int columns;
    private final Piece[][] pieces;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.pieces = new Piece[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Piece piece(int row, int column) {
        return pieces[row][column];
    }

    public Piece piece(Position position) {
        return pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece, Position position) {
        if (piece(position) == null) {
            pieces[position.getRow()][position.getColumn()] = piece;
        }
    }

    public Piece removePiece(Position position) {
        Piece piece = piece(position);
        pieces[position.getRow()][position.getColumn()] = null;
        return piece;
    }


}