package day12

enum class Direction {
    NORTH {
        override fun move(distance: Int, from: Position) = from.copy(y = from.y + distance)
        override fun rotate(degrees: Int) =
            mapOf(90 to EAST, 180 to SOUTH, 270 to WEST)[degrees]!!
    },
    SOUTH {
        override fun move(distance: Int, from: Position) = from.copy(y = from.y - distance)
        override fun rotate(degrees: Int) =
            mapOf(90 to WEST, 180 to NORTH, 270 to EAST)[degrees]!!
    },
    EAST {
        override fun move(distance: Int, from: Position) = from.copy(x = from.x + distance)
        override fun rotate(degrees: Int) =
            mapOf(90 to SOUTH, 180 to WEST, 270 to NORTH)[degrees]!!
    },
    WEST {
        override fun move(distance: Int, from: Position) = from.copy(x = from.x - distance)
        override fun rotate(degrees: Int) =
            mapOf(90 to NORTH, 180 to EAST, 270 to SOUTH)[degrees]!!
    };

    abstract fun move(distance: Int, from: Position): Position
    abstract fun rotate(degrees: Int): Direction
}
