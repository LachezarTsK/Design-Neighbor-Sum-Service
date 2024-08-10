
package main

import "fmt"

var adjacentMove [4][2]int = [4][2]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}
var diagonalMove [4][2]int = [4][2]int{{-1, -1}, {-1, 1}, {1, -1}, {1, 1}}

type Point struct {
    row    int
    column int
}

type NeighborSum struct {
    rows               int
    columns            int
    matrix             [][]int
    valueToCoordinates []Point
}

func Constructor(matrix [][]int) NeighborSum {
    neighborSum := NeighborSum{
        rows:               len(matrix),
        columns:            len(matrix[0]),
        matrix:             matrix,
        valueToCoordinates: make([]Point, len(matrix)*len(matrix[0])),
    }

    for row := 0; row < neighborSum.rows; row++ {
        for column := 0; column < neighborSum.columns; column++ {
            neighborSum.valueToCoordinates[matrix[row][column]] = Point{row: row, column: column}
        }
    }
    return neighborSum
}

func (this *NeighborSum) AdjacentSum(value int) int {
    row := this.valueToCoordinates[value].row
    column := this.valueToCoordinates[value].column
    var adjacentSum = 0

    for _, move := range adjacentMove {
        nextRow := row + move[0]
        nextColumn := column + move[1]
        if this.isInMatrix(nextRow, nextColumn) {
            adjacentSum += this.matrix[nextRow][nextColumn]
        }
    }

    return adjacentSum
}

func (this *NeighborSum) DiagonalSum(value int) int {
    row := this.valueToCoordinates[value].row
    column := this.valueToCoordinates[value].column
    var diagonalSum = 0

    for _, move := range diagonalMove {
        nextRow := row + move[0]
        nextColumn := column + move[1]
        if this.isInMatrix(nextRow, nextColumn) {
            diagonalSum += this.matrix[nextRow][nextColumn]
        }
    }

    return diagonalSum
}

func (this *NeighborSum) isInMatrix(row int, column int) bool {
    return row >= 0 && row < this.rows && column >= 0 && column < this.columns
}
