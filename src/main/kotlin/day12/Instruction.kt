package day12

import java.lang.IllegalArgumentException

enum class Instruction {
    NORTH {
        override fun perform(param: Int, ship: Ship): Ship =
            processDirection(Direction.NORTH, param, ship)
    },
    SOUTH {
        override fun perform(param: Int, ship: Ship): Ship =
            processDirection(Direction.SOUTH, param, ship)
    },
    EAST {
        override fun perform(param: Int, ship: Ship) =
            processDirection(Direction.EAST, param, ship)
    },
    WEST {
        override fun perform(param: Int, ship: Ship): Ship =
            processDirection(Direction.WEST, param, ship)
    },
    FORWARD {
        override fun perform(param: Int, ship: Ship) =
            if (ship is ShipWithWaypoint) ShipWithWaypoint(
                ship.facing, ship.position.jumpOffset(param, ship.waypoint), ship.waypoint
            )
            else processDirection(ship.facing, param, ship)
    },
    LEFT {
        override fun perform(param: Int, ship: Ship) =
            if (ship is ShipWithWaypoint) ShipWithWaypoint(
                ship.facing, ship.position, rotateWaypoint(360 - param, ship.waypoint)
            )
            else Ship(ship.facing.rotate(360 - param), ship.position)
    },
    RIGHT {
        override fun perform(param: Int, ship: Ship) =
            if (ship is ShipWithWaypoint) ShipWithWaypoint(
                ship.facing, ship.position, rotateWaypoint(param, ship.waypoint)
            )
            else Ship(ship.facing.rotate(param), ship.position)
    };

    abstract fun perform(param: Int, ship: Ship): Ship

    companion object {
        fun fromChar(ch: Char): Instruction =
            when (ch) {
                'N' -> NORTH
                'S' -> SOUTH
                'E' -> EAST
                'W' -> WEST
                'F' -> FORWARD
                'L' -> LEFT
                'R' -> RIGHT
                else -> throw IllegalArgumentException("Unknown instruction: $ch")
            }

        private fun processDirection(direction: Direction, param: Int, ship: Ship) =
            if (ship is ShipWithWaypoint) ShipWithWaypoint(
                ship.facing,
                ship.position,
                direction.move(param, ship.waypoint)
            )
            else Ship(ship.facing, direction.move(param, ship.position))
    }
}
