package day11

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day11Test {
    @Test
    fun `fills seat`() {
        val before = grid("""
            ...
            .L.
            ...
        """)

        val after = grid("""
            ...
            .#.
            ...
            """)

        assertEquals(after, nextGrid(before))
    }

    @Test
    fun `fills edge seat`() {
        val before = grid("""
            .L..
            ...L
            L...
            ..L.
        """)

        val after = grid("""
            .#..
            ...#
            #...
            ..#.
        """)

        assertEquals(after, nextGrid(before))
    }

    @Test
    fun `fills corner seat`() {
        val before = grid("""
            L.L
            ...
            L.L
        """)

        val after = grid("""
            #.#
            ...
            #.#
            """)

        assertEquals(after, nextGrid(before))
    }

    @Test
    fun `empties seat`() {
        val before = grid("""
            .#.
            ###
            .#.
        """)

        val after = grid("""
            .#.
            #L#
            .#.
            """)

        assertEquals(after, nextGrid(before))
    }

    @Test
    fun `waits for grid to stabilise`() {
        val grid = grid("""
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
        """)

        assertEquals(37, countOccupiedSeats(stabliiseGrid(grid)!!))
    }

}
