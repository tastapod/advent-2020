package day11.part1

import day11.Grid

fun nextGrid(grid: Grid) =
    grid.mapIndexed { iRow, row ->
        row.mapIndexed { iCol, seat ->
            when {
                seat == 'L' && countImmediateNeighbours(grid, iRow, iCol) == 0 -> '#'
                seat == '#' && countImmediateNeighbours(grid, iRow, iCol) >= 4 -> 'L'
                else -> seat
            }
        }
    }

/**
 * ...
 * .x.
 * ...
 */
fun countImmediateNeighbours(grid: Grid, iRow: Int, iCol: Int): Int {
    fun count(row: Int, col: Int) =
        if (row in grid.indices &&
            col in grid[0].indices &&
            grid[row][col] == '#'
        ) 1 else 0

    return (iRow - 1..iRow + 1).sumBy { row ->
        (iCol - 1..iCol + 1).sumBy { col ->
            count(row, col)
        }
    } - count(iRow, iCol)
}

fun stabliiseGrid(
    grid: Grid,
    maxTurns: Int = 10000
): Grid? {
    tailrec fun stabiliseGrid(grid: Grid, turnsLeft: Int): Grid? {
        val nextGrid = nextGrid(grid)
        return when {
            turnsLeft == 0 -> null
            grid == nextGrid -> grid
            else -> stabiliseGrid(nextGrid, turnsLeft - 1)
        }
    }
    return stabiliseGrid(grid, maxTurns)
}

fun countOccupiedSeats(grid: Grid) =
    grid.sumBy { row -> row.count { it == '#' } }
