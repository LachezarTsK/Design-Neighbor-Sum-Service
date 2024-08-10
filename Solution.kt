
class NeighborSum(matrix: Array<IntArray>) {

    private companion object {
        val adjacentMove = arrayOf(intArrayOf(-1, 0), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(0, 1))
        val diagonalMove = arrayOf(intArrayOf(-1, -1), intArrayOf(-1, 1), intArrayOf(1, -1), intArrayOf(1, 1))
    }

    private data class Point(val row: Int, val column: Int) {}

    private var rows: Int = 0
    private var columns: Int = 0
    private lateinit var matrix: Array<IntArray>
    private var valueToCoordinates = arrayOfNulls<Point>(0)

    init {
        rows = matrix.size
        columns = matrix[0].size
        this.matrix = matrix
        valueToCoordinates = arrayOfNulls<Point>(rows * columns)

        for (row in 0..<rows) {
            for (column in 0..<columns) {
                valueToCoordinates[matrix[row][column]] = Point(row, column)
            }
        }
    }

    fun adjacentSum(value: Int): Int {
        val row = valueToCoordinates[value]!!.row
        val column = valueToCoordinates[value]!!.column
        var adjacentSum = 0

        for (move in adjacentMove) {
            val nextRow = row + move[0]
            val nextColumn = column + move[1]
            if (isInMatrix(nextRow, nextColumn)) {
                adjacentSum += matrix[nextRow][nextColumn]
            }
        }

        return adjacentSum
    }

    fun diagonalSum(value: Int): Int {
        val row = valueToCoordinates[value]!!.row
        val column = valueToCoordinates[value]!!.column
        var diagonalSum = 0

        for (move in diagonalMove) {
            val nextRow = row + move[0]
            val nextColumn = column + move[1]
            if (isInMatrix(nextRow, nextColumn)) {
                diagonalSum += matrix[nextRow][nextColumn]
            }
        }

        return diagonalSum
    }

    private fun isInMatrix(row: Int, column: Int): Boolean {
        return row in 0..<rows && column in 0..<columns
    }
}
