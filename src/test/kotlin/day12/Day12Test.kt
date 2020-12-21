package day12

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

    @Test
    fun `waypoint rotates`() {
        val waypoint = Position(+10, +1)

        assertEquals(Position(+1, -10), rotateWaypoint(90, waypoint))
        assertEquals(Position(-10, -1), rotateWaypoint(180, waypoint))
        assertEquals(Position(-1, +10), rotateWaypoint(270, waypoint))
    }

    @Test
    fun `navigates using waypoint`() {
        val directions = """
            F10
            N3
            F7
            R90
            F11
        """.trimIndent().lines()

        val ship = ShipWithWaypoint(Direction.EAST, Position(0, 0), Position(+10, +1))
        assertEquals(
            ShipWithWaypoint(Direction.EAST, Position(214, -72), Position(4, -10)),
            ship.navigate(directions)
        )
    }
}
