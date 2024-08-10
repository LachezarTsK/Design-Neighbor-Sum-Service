
class NeighborSum {

    private static adjacentMove = [[-1, 0], [1, 0], [0, -1], [0, 1]];
    private static diagonalMove = [[-1, -1], [-1, 1], [1, -1], [1, 1]];

    private matrix: number;
    private rows: number;
    private columns: number;
    private valueToCoordinates: Point[];

    /**
     * @param {number[][]} matrix
     */
    constructor(matrix) {
        this.matrix = matrix;
        this.rows = matrix.length;
        this.columns = matrix[0].length;
        this.valueToCoordinates = new Array(this.rows * this.columns);

        for (let row = 0; row < this.rows; ++row) {
            for (let column = 0; column < this.columns; ++column) {
                this.valueToCoordinates[this.matrix[row][column]] = new Point(row, column);
            }
        }
    }

    adjacentSum(value: number): number {
        const row = this.valueToCoordinates[value].row;
        const column = this.valueToCoordinates[value].column;
        let adjacentSum = 0;

        for (let move of NeighborSum.adjacentMove) {
            const nextRow = row + move[0];
            const nextColumn = column + move[1];
            if (this.isInMatrix(nextRow, nextColumn)) {
                adjacentSum += this.matrix[nextRow][nextColumn];
            }
        }

        return adjacentSum;
    }

    diagonalSum(value: number): number {
        const row = this.valueToCoordinates[value].row;
        const column = this.valueToCoordinates[value].column;
        let diagonalSum = 0;

        for (let move of NeighborSum.diagonalMove) {
            const nextRow = row + move[0];
            const nextColumn = column + move[1];
            if (this.isInMatrix(nextRow, nextColumn)) {
                diagonalSum += this.matrix[nextRow][nextColumn];
            }
        }

        return diagonalSum;
    }

    private isInMatrix(row: number, column: number): boolean {
        return row >= 0 && row < this.rows && column >= 0 && column < this.columns;
    }
}

class Point {
    row: number;
    column: number;

    constructor(row, column) {
        this.row = row;
        this.column = column;
    }
}
