package day12

import java.lang.IllegalArgumentException
import java.lang.Integer.parseInt
import kotlin.math.absoluteValue

enum class Direction {
    NORTH {
        override fun move(start: Position, distance: Int) = start.copy(y = start.y + distance)
        override fun rotate(degrees: Int) =
            mapOf(90 to EAST, 180 to SOUTH, 270 to WEST)[degrees]!!
    },
    SOUTH {
        override fun move(start: Position, distance: Int) = start.copy(y = start.y - distance)
        override fun rotate(degrees: Int) =
            mapOf(90 to WEST, 180 to NORTH, 270 to EAST)[degrees]!!
    },
    EAST {
        override fun move(start: Position, distance: Int) = start.copy(x = start.x + distance)
        override fun rotate(degrees: Int) =
            mapOf(90 to SOUTH, 180 to WEST, 270 to NORTH)[degrees]!!
    },
    WEST {
        override fun move(start: Position, distance: Int) = start.copy(x = start.x - distance)
        override fun rotate(degrees: Int) =
            mapOf(90 to NORTH, 180 to EAST, 270 to SOUTH)[degrees]!!
    };

    abstract fun move(start: Position, distance: Int): Position
    abstract fun rotate(degrees: Int): Direction
}

data class Position(val x: Int, val y: Int) {
    fun manhattenDistance(): Int = x.absoluteValue + y.absoluteValue
}

data class Ship(val facing: Direction, val position: Position) {
    fun navigate(directions: List<String>) =
        directions.map(::parseLeg).fold(this) { ship, leg ->
            ship.travel(leg)
        }

    fun manhattenDistance() = position.manhattenDistance()

    private fun travel(leg: Leg) = leg.travel(this)
}

data class Leg(val instruction: Instruction, val param: Int) {
    fun travel(ship: Ship) = instruction.perform(param, ship)
}

fun parseLeg(input: String) =
    Leg(Instruction.fromChar(input[0]), parseInt(input.drop(1)))

enum class Instruction {

    GO_NORTH {
        override fun perform(param: Int, ship: Ship): Ship =
            travel(Direction.NORTH, param, ship)
    },
    GO_EAST {
        override fun perform(param: Int, ship: Ship): Ship =
            travel(Direction.EAST, param, ship)
    },
    GO_SOUTH {
        override fun perform(param: Int, ship: Ship): Ship =
            travel(Direction.SOUTH, param, ship)
    },
    GO_WEST {
        override fun perform(param: Int, ship: Ship): Ship =
            travel(Direction.WEST, param, ship)
    },
    GO_FORWARD {
        override fun perform(param: Int, ship: Ship): Ship =
            travel(ship.facing, param, ship)
    },
    TURN_LEFT {
        override fun perform(param: Int, ship: Ship) =
            ship.copy(facing = ship.facing.rotate(360-param))
    },
    TURN_RIGHT {
        override fun perform(param: Int, ship: Ship) =
            ship.copy(facing = ship.facing.rotate(+param))
    };

    abstract fun perform(param: Int, ship: Ship): Ship

    protected fun travel(direction: Direction, distance: Int, ship: Ship) =
        ship.copy(position = direction.move(ship.position, distance))

    companion object {
        fun fromChar(ch: Char): Instruction =
            when (ch) {
                'N' -> GO_NORTH
                'S' -> GO_SOUTH
                'E' -> GO_EAST
                'W' -> GO_WEST
                'F' -> GO_FORWARD
                'L' -> TURN_LEFT
                'R' -> TURN_RIGHT
                else -> throw IllegalArgumentException("Unknown instruction: $ch")
            }
    }
}
