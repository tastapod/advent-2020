package day11

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day11Test {
    @Test
    fun `fills seat`() {
        val before = grid(
            """
            ...
            .L.
            ...
        """
        )

        val after = grid(
            """
            ...
            .#.
            ...
            """
        )

        assertEquals(after, nextGrid(before))
    }

    @Test
    fun `fills edge seat`() {
        val before = grid(
            """
            .L..
            ...L
            L...
            ..L.
        """
        )

        val after = grid(
            """
            .#..
            ...#
            #...
            ..#.
        """
        )

        assertEquals(after, nextGrid(before))
    }

    @Test
    fun `fills corner seat`() {
        val before = grid(
            """
            L.L
            ...
            L.L
        """
        )

        val after = grid(
            """
            #.#
            ...
            #.#
            """
        )

        assertEquals(after, nextGrid(before))
    }

    @Test
    fun `empties seat`() {
        val before = grid(
            """
            .#.
            ###
            .#.
        """
        )

        val after = grid(
            """
            .#.
            #L#
            .#.
            """
        )

        assertEquals(after, nextGrid(before))
    }

    @Test
    fun `waits for grid to stabilise`() {
        val grid = grid(
            """
            L.LL.LL.LL
            LLLLLLL.LL
            L.L.L..L..
            LLLL.LL.LL
            L.LL.LL.LL
            L.LLLLL.LL
            ..L.L.....
            LLLLLLLLLL
            L.LLLLLL.L
            L.LLLLL.LL
        """
        )

        assertEquals(37, countOccupiedSeats(stabliiseGrid(grid)!!))
    }

    @Test
    fun `builds seat map`() {
        val grid = grid(
            """
                .L.
                L..
                LL.
            """
        )

        val expected = mapOf(
            Loc(0, 1) to Seat(false, setOf(Loc(1, 0), Loc(2, 1))),
            Loc(1, 0) to Seat(false, setOf(Loc(0, 1), Loc(2, 0), Loc(2, 1))),
            Loc(2, 0) to Seat(false, setOf(Loc(1, 0), Loc(2, 1))),
            Loc(2, 1) to Seat(false, setOf(Loc(1, 0), Loc(2, 0), Loc(0, 1))),
        )

        assertEquals(expected, buildSeatMap(findSeats(grid), input.size, input[0].size))
    }

    @Test
    fun `waits for map to stabilise`() {
        val grid = grid(
            """
            L.LL.LL.LL
            LLLLLLL.LL
            L.L.L..L..
            LLLL.LL.LL
            L.LL.LL.LL
            L.LLLLL.LL
            ..L.L.....
            LLLLLLLLLL
            L.LLLLLL.L
            L.LLLLL.LL
        """
        )

        assertEquals(
            26,
            countOccupiedSeats(
                stabliiseSeatMap(
                    buildSeatMap(
                        findSeats(grid), grid.size, grid[0].size
                    )
                )!!
            )
        )
    }
}
