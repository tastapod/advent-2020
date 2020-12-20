package day12

import day12.part1.Ship
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day12Test {
    @Test
    fun `navigates using directions`() {
        val directions = """
            F10
            N3
            F7
            R90
            F11
        """.trimIndent().lines()

        val ship = Ship(Direction.EAST, Position(0, 0))

        assertEquals(Ship(Direction.SOUTH, Position(+17, -8)), ship.navigate(directions))
    }
}
