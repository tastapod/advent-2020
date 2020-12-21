package day12

open class Ship(val facing: Direction, val position: Position) {
    fun navigate(directions: List<String>) =
        directions.map(Leg::parse).fold(this) { ship, leg ->
            ship.travel(leg)
        }

    fun manhattenDistance() = position.manhattenDistance()

    private fun travel(leg: Leg) = leg.travel(this)

    override fun equals(other: Any?) =
        other is Ship && other.facing == this.facing && other.position == this.position

    override fun hashCode() = 31 * facing.hashCode() + position.hashCode()
}
