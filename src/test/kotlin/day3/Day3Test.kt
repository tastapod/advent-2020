package day3

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day3Test {
    @Test
    fun `counts trees in a straight line`() {
        val mapBlock = """
            ..##.......
            #...#...#..
            .#....#..#.
            ..#.#...#.#
            .#...##..#.
            ..#.##.....
            .#.#.#....#
            .#........#
            #.##...#...
            #...##....#
            .#..#...#.#
        """.trimIndent()

        val grid = TreesMap(mapBlock)
        assertEquals(7, grid.countTrees(3, 1))
    }
}