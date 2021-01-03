package day15

data class Turn(val turn: Int, val value: Int)

data class Game(
    val previous: Turn,
    val numSeen: Int,
    private val history: MutableMap<Int, Int>
) {
    val turn
        get() = previous.turn + 1

    fun takeTurn(): Game =
        when (previous.value) {
            !in history ->                 // new value
                Game(
                    Turn(previous.turn + 1, 0),
                    numSeen + 1,
                    history.apply { put(previous.value, previous.turn) }
                )
            else -> Game(
                Turn(previous.turn + 1, previous.turn - history.getValue(previous.value)),
                numSeen,
                history.apply { put(previous.value, previous.turn) }
            )
        }

    fun takeTurns(numTurns: Int): Game =
        if (numTurns == 0) this else takeTurn().takeTurns(numTurns - 1)

    fun playUntil(finish: (Game) -> Boolean): Game {
        tailrec fun playUntil(game: Game): Game =
            if (finish(game)) game else playUntil(game.takeTurn())
        return playUntil(this)
    }

    companion object {
        fun startingWith(seeds: List<Int>): Game {
            val previous = Turn(seeds.size, seeds.last())
            val historySeeds = seeds.dropLast(1)
            val history = (emptyMap<Int, Int>() + historySeeds.zip(1..historySeeds.size)).toMutableMap()

            return Game(
                previous,
                historySeeds.toSet().size,
                history
            ).also { println(it) }
        }

        fun play2020Turns(seeds: List<Int>) =
            startingWith(seeds)
                .playUntil { g -> g.turn == 2021 }
                .previous.value
    }
}
