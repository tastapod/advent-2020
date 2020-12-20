package day11.part2

import day11.Grid

data class Loc(val row: Int, val col: Int)
data class Seat(val isOccupied: Boolean, val neighbours: Set<Loc>)typealias SeatMap = Map<Loc, Seat>

fun findSeats(grid: Grid): Set<Loc> =
    grid.foldIndexed(emptySet()) { iRow, accRow, row ->
        accRow + row.foldIndexed(emptyList()) { iCol, accCol, cell ->
            if (cell == 'L') accCol + Loc(iRow, iCol) else accCol
        }
    }

fun buildSeatMap(seats: Set<Loc>, numRows: Int, numCols: Int): SeatMap {
    fun maybeLoc(maybeRow: Int, maybeCol: Int) =
        if (maybeRow in 0..numRows && maybeCol in 0..numCols) Loc(maybeRow, maybeCol) else null

    fun lineOfSight(start: Loc, rowInc: Int, colInc: Int): Sequence<Loc> =
        generateSequence(maybeLoc(start.row + rowInc, start.col + colInc)) {
            maybeLoc(it.row + rowInc, it.col + colInc)
        }

    return seats.fold(emptyMap()) { acc, loc ->
        val visibleNeighbours = (-1..1).fold(emptySet<Loc>()) { neighbours, rowInc ->
            neighbours + (-1..1).fold(emptySet()) { rowAcc, colInc ->
                val visibleNeighbour = lineOfSight(loc, rowInc, colInc).find { it in seats }
                if (visibleNeighbour != null) rowAcc + visibleNeighbour else rowAcc
            }
        } - loc
        acc + Pair(loc, Seat(false, visibleNeighbours))
    }
}

fun nextSeatMap(seatMap: SeatMap) =
    seatMap.entries.map { (loc, seat) ->
        val occupiedNeighbours = seat.neighbours.count { seatMap[it]!!.isOccupied }
        when {
            !seat.isOccupied && occupiedNeighbours == 0 ->
                Pair(loc, seat.copy(isOccupied = true))
            seat.isOccupied && occupiedNeighbours >= 5 ->
                Pair(loc, seat.copy(isOccupied = false))
            else ->
                Pair(loc, seat)
        }
    }.toMap()

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

fun countOccupiedSeats(seatMap: SeatMap) = seatMap.values.count { it.isOccupied }
