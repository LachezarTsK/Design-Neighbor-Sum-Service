
public class NeighborSum {

    private static final int[][] adjacentMove = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final int[][] diagonalMove = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    private record Point(int row, int column){}

    private final int rows;
    private final int columns;
    private final int[][] matrix;
    private final Point[] valueToCoordinates;

    public NeighborSum(int[][] matrix) {
        this.matrix = matrix;
        rows = matrix.length;
        columns = matrix[0].length;
        valueToCoordinates = new Point[rows * columns];

        for (int row = 0; row < rows; ++row) {
            for (int column = 0; column < columns; ++column) {
                valueToCoordinates[matrix[row][column]] = new Point(row, column);
            }
        }
    }

    public int adjacentSum(int value) {
        int row = valueToCoordinates[value].row;
        int column = valueToCoordinates[value].column;
        int adjacentSum = 0;

        for (int[] move : adjacentMove) {
            int nextRow = row + move[0];
            int nextColumn = column + move[1];
            if (isInMatrix(nextRow, nextColumn)) {
                adjacentSum += matrix[nextRow][nextColumn];
            }
        }

        return adjacentSum;
    }

    public int diagonalSum(int value) {
        int row = valueToCoordinates[value].row;
        int column = valueToCoordinates[value].column;
        int diagonalSum = 0;

        for (int[] move : diagonalMove) {
            int nextRow = row + move[0];
            int nextColumn = column + move[1];
            if (isInMatrix(nextRow, nextColumn)) {
                diagonalSum += matrix[nextRow][nextColumn];
            }
        }

        return diagonalSum;
    }

    private boolean isInMatrix(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }
}
