package day12

import java.lang.IllegalArgumentException
import kotlin.math.absoluteValue

data class Position(val x: Int, val y: Int) {
    fun manhattenDistance(): Int = x.absoluteValue + y.absoluteValue
    fun jumpOffset(count: Int, offset: Position) =
        Position(x + count * offset.x, y + count * offset.y)
}

fun rotateWaypoint(degrees: Int, waypoint: Position) =
    when (degrees) {
        90 -> Position(+waypoint.y, -waypoint.x)
        180 -> Position(-waypoint.x, -waypoint.y)
        270 -> Position(-waypoint.y, +waypoint.x)
        else -> throw IllegalArgumentException("Cannot rotate $degrees degrees")
    }


class ShipWithWaypoint(facing: Direction, position: Position, val waypoint: Position) :
    Ship(facing, position)

data class Leg(val instruction: Instruction, val param: Int) {
    fun travel(ship: Ship) = instruction.perform(param, ship)

    companion object {
        fun parse(input: String) =
            Leg(Instruction.fromChar(input[0]), Integer.parseInt(input.drop(1)))
    }
}

