package day11

typealias Grid = List<List<Char>>

fun nextGrid(grid: Grid) =
    grid.mapIndexed { iRow, row ->
        row.mapIndexed { iCol, col ->
            when {
                col == 'L' && countNeigbours(grid, iRow, iCol) == 0 -> '#'
                col == '#' && countNeigbours(grid, iRow, iCol) >= 4 -> 'L'
                else -> col
            }
        }
    }

/**
 * ...
 * .x.
 * ...
 */
fun countNeigbours(grid: Grid, iRow: Int, iCol: Int): Int {
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

fun stabliiseGrid(grid: Grid, maxTurns: Int = 10000): Grid? {
    fun matches(g1: Grid, g2: Grid) = g1.zip(g2).all { it.first == it.second }

    tailrec fun stabiliseGrid(grid: Grid, turns: Int): Grid? {
        val nextGrid = nextGrid(grid)
        return when {
            turns == maxTurns -> null
            matches(grid, nextGrid) -> grid
            else -> stabiliseGrid(nextGrid, turns + 1)
        }
    }
    return stabiliseGrid(grid, 0)
}

fun countOccupiedSeats(grid: Grid) =
    grid.sumBy { row -> row.count { it == '#' } }

fun grid(input: String): Grid =
    input.trimIndent().lines().map(String::toList)
