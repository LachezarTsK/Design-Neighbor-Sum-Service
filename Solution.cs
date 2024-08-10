
using System;

public class NeighborSum
{
    private static readonly int[][] adjacentMove = {
        new int[]{ -1, 0 }, new int[]{ 1, 0 }, new int[]{ 0, -1 }, new int[]{ 0, 1 } };

    private static readonly int[][] diagonalMove = {
        new int[]{ -1, -1 }, new int[]{ -1, 1 }, new int[]{ 1, -1 }, new int[]{ 1, 1 } };

    private record Point(int row, int column){}

    private readonly int rows;
    private readonly int columns;
    private readonly int[][] matrix;
    private readonly Point[] valueToCoordinates;

    public NeighborSum(int[][] matrix)
    {
        this.matrix = matrix;
        rows = matrix.Length;
        columns = matrix[0].Length;
        valueToCoordinates = new Point[rows * columns];

        for (int row = 0; row < rows; ++row)
        {
            for (int column = 0; column < columns; ++column)
            {
                valueToCoordinates[matrix[row][column]] = new Point(row, column);
            }
        }
    }

    public int AdjacentSum(int value)
    {
        int row = valueToCoordinates[value].row;
        int column = valueToCoordinates[value].column;
        int adjacentSum = 0;

        foreach (int[] move in adjacentMove)
        {
            int nextRow = row + move[0];
            int nextColumn = column + move[1];
            if (IsInMatrix(nextRow, nextColumn))
            {
                adjacentSum += matrix[nextRow][nextColumn];
            }
        }

        return adjacentSum;
    }

    public int DiagonalSum(int value)
    {
        int row = valueToCoordinates[value].row;
        int column = valueToCoordinates[value].column;
        int diagonalSum = 0;

        foreach (int[] move in diagonalMove)
        {
            int nextRow = row + move[0];
            int nextColumn = column + move[1];
            if (IsInMatrix(nextRow, nextColumn))
            {
                diagonalSum += matrix[nextRow][nextColumn];
            }
        }

        return diagonalSum;
    }

    private bool IsInMatrix(int row, int column)
    {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }
}
