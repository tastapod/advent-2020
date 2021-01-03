package day15

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day15Test {
    @Test
    fun `seeds a game`() {
        val seeds = listOf(0, 3, 6)
        assertEquals(Game(
            Turn(3, 6),
            2,
            mutableMapOf(0 to 1, 3 to 2)
        ),
        Game.startingWith(seeds))
    }

    @Test
    fun `turn 4 - recognises a new value`() {
        val game = Game.startingWith(listOf(0, 3, 6))

        assertEquals(Game(
            Turn(4, 0),
            3,
            mutableMapOf(0 to 1, 3 to 2, 6 to 3)
        ),
        game.takeTurn())
    }

    @Test
    fun `turn 5 - recognises a previous value`() {
        val game = Game.startingWith(listOf(0, 3, 6))

        assertEquals(Game(
            Turn(5, 3),
            3,
            mutableMapOf(0 to 4, 3 to 2, 6 to 3)
        ),
        game.takeTurns(2))
    }

    @Test
    fun `turn 6`() {
        val game = Game.startingWith(listOf(0, 3, 6))

        assertEquals(Game(
            Turn(6, 3),
            3,
            mutableMapOf(0 to 4, 3 to 5, 6 to 3)
        ),
        game.takeTurns(3))
    }

    @Test
    fun `turn 7`() {
        val game = Game.startingWith(listOf(0, 3, 6))

        assertEquals(Game(
            Turn(7, 1),
            3,
            mutableMapOf(0 to 4, 3 to 6, 6 to 3)
        ),
        game.takeTurns(4))
    }

    @Test
    fun `turn 8`() {
        val game = Game.startingWith(listOf(0, 3, 6))

        assertEquals(Game(
            Turn(8, 0),
            4,
            mutableMapOf(0 to 4, 1 to 7, 3 to 6, 6 to 3)
        ),
        game.takeTurns(5))
    }

    @Test
    fun `plays until condition`() {
        val game = Game.startingWith(listOf(0, 3, 6))
        val finished = game.playUntil { g -> g.turn == 27 }
        assertEquals(27, finished.turn)
    }

    @Test
    fun `plays until 2020 values`() {
        val game = Game.startingWith(listOf(0, 3, 6))
        val finished = game.playUntil { g -> g.turn == 2021 }
        assertEquals(436, finished.previous.value)
    }

    @Test
    fun `plays with other seed lists`() {
        assertEquals(1, Game.play2020Turns(listOf(1, 3, 2)))
        assertEquals(10, Game.play2020Turns(listOf(2, 1, 3)))
        assertEquals(27, Game.play2020Turns(listOf(1, 2, 3)))
        assertEquals(78, Game.play2020Turns(listOf(2, 3, 1)))
        assertEquals(1836, Game.play2020Turns(listOf(3, 1, 2)))
    }

}

