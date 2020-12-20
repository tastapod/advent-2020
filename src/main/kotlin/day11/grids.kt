package day11

typealias Grid = List<List<Char>>

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

fun nextSeatMap(seatMap: SeatMap) =
    seatMap.entries.map { (loc, seat) ->
        val occupiedNeighbours = seat.neighbours.count { seatMap[it]!!.isOccupied }
        when {
            !seat.isOccupied && occupiedNeighbours == 0 -> Pair(loc, seat.copy(isOccupied = true))
            seat.isOccupied && occupiedNeighbours >= 5 -> Pair(loc, seat.copy(isOccupied = false))
            else -> Pair(loc, seat)
        }
    }.toMap()

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

data class Loc(val row: Int, val col: Int)
data class Seat(val isOccupied: Boolean, val neighbours: Set<Loc>)
typealias SeatMap = Map<Loc, Seat>

fun findSeats(grid: Grid): Set<Loc> =
    grid.foldIndexed(emptySet()) { iRow, accRow, row ->
        accRow + row.foldIndexed(emptyList()) { iCol, accCol, cell ->
            if (cell == 'L') accCol + Loc(iRow, iCol) else accCol
        }
    }

fun buildSeatMap(seats: Set<Loc>, numRows: Int, numCols: Int): SeatMap {
    fun maybeLoc(maybeRow: Int, maybeCol: Int) =
        if (maybeRow in 0..numRows && maybeCol in 0..numCols) Loc(maybeRow, maybeCol) else null

    fun line(start: Loc, rowInc: Int, colInc: Int): Sequence<Loc> =
        generateSequence(maybeLoc(start.row + rowInc, start.col + colInc)) {
            maybeLoc(it.row + rowInc, it.col + colInc)
        }

    return seats.fold(emptyMap()) { acc, loc ->
        val neighbours = (-1..1).fold(emptySet<Loc>()) { neighbours, rowInc ->
            neighbours + (-1..1).fold(emptySet()) { rowAcc, colInc ->
                val neighbour = line(loc, rowInc, colInc).find { it in seats }
                if (neighbour != null) rowAcc + neighbour else rowAcc
            }
        } - loc
        acc + Pair(loc, Seat(false, neighbours))
    }
}

fun stabliiseSeatMap(
    seatMap: SeatMap,
    maxTurns: Int = 10000
): SeatMap? {
    tailrec fun stabiliseSeatMap(seatMap: SeatMap, turnsLeft: Int): SeatMap? {
        val nextMap = nextSeatMap(seatMap)
        return when {
            turnsLeft == 0 -> null
            seatMap == nextMap -> seatMap
            else -> stabiliseSeatMap(nextMap, turnsLeft - 1)
        }
    }
    return stabiliseSeatMap(seatMap, maxTurns)
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

fun countOccupiedSeats(seatMap: SeatMap) =
    seatMap.values.count { it.isOccupied }

fun grid(input: String): Grid =
    input.trimIndent().lines().map(String::toList)
