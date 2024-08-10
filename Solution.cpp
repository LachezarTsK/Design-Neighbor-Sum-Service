
#include <array>
#include <vector>
using namespace std;

class NeighborSum {

    struct Point {
        size_t row{};
        size_t column{};

        Point() = default;
        ~Point() = default;
        Point(size_t row, size_t column) : row{ row }, column{ column } {}
    };

    static constexpr array<array<int, 2>, 4> adjacentMove{
            {{-1, 0}, {1, 0}, {0, -1}, {0, 1}} };

    static constexpr array<array<int, 2>, 4> diagonalMove{
            {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}} };

    size_t rows;
    size_t columns;
    vector<vector<int>>& matrix;
    vector<Point> valueToCoordinates;

public:
    explicit NeighborSum(vector<vector<int>>& matrix) : matrix{ matrix } {
        this->rows = matrix.size();
        this->columns = matrix[0].size();
        valueToCoordinates.resize(rows * columns);

        for (size_t row = 0; row < rows; ++row) {
            for (size_t column = 0; column < columns; ++column) {
                valueToCoordinates[matrix[row][column]] = Point(row, column);
            }
        }
    }

    int adjacentSum(int value) const {
        size_t row = valueToCoordinates[value].row;
        size_t column = valueToCoordinates[value].column;
        int adjacentSum = 0;

        for (const auto& move : adjacentMove) {
            size_t nextRow = row + move[0];
            size_t nextColumn = column + move[1];

            if (isInMatrix(nextRow, nextColumn)) {
                adjacentSum += matrix[nextRow][nextColumn];
            }
        }

        return adjacentSum;
    }

    int diagonalSum(int value) const {
        size_t row = valueToCoordinates[value].row;
        size_t column = valueToCoordinates[value].column;
        int diagonalSum = 0;

        for (const auto& move : diagonalMove) {
            size_t nextRow = row + move[0];
            size_t nextColumn = column + move[1];
            if (isInMatrix(nextRow, nextColumn)) {
                diagonalSum += matrix[nextRow][nextColumn];
            }
        }

        return diagonalSum;
    }

    bool isInMatrix(size_t row, size_t column) const {
        return row < rows && column < columns;
    }
};
