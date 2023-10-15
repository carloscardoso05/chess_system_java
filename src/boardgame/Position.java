package boardgame;

public class Position {
    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    void setValues(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Position addPositions(Position position, Position offset) {
        final int newRow = position.getRow() + offset.getRow();
        final int newColumn = position.getColumn() + offset.getColumn();
        return new Position(newRow, newColumn);
    }

    @Override
    public String toString() {
        return row + ", " + column;
    }
}
