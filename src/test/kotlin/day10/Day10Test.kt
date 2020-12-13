package day10

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day10Test {
    private val shortInput = """
            16
            10
            15
            5
            1
            11
            7
            19
            6
            12
            4
        """.trimIndent().lines().map(String::toLong)

    private val longerInput = """
            28
            33
            18
            42
            31
            14
            46
            20
            48
            47
            24
            23
            49
            45
            19
            38
            39
            11
            1
            32
            25
            35
            8
            17
            7
            9
            4
            2
            34
            10
            3
        """.trimIndent().lines().map(String::toLong)

    @Test
    fun `finds deltas in a valid chain of adapters`() {
        assertEquals(mapOf(1L to 7, 3L to 5), countJoltageDeltas(shortInput))
    }

    @Test
    fun `finds deltas in a longer valid chain of adapters`() {
        assertEquals(mapOf(1L to 22, 3L to 10), countJoltageDeltas(longerInput))
    }

    @Test
    fun `removes item from a list`() {
        assertEquals(listOf(11, 33, 44), without(listOf(11, 22, 33, 44), 1))
        assertEquals(listOf(22, 33, 44), without(listOf(11, 22, 33, 44), 0))
    }

    @Test
    fun `finds all valid subsequences in short valid chain`() {
        assertEquals(
            mapOf<Long, List<Long>>(
                22L to listOf(19),
                19L to listOf(16),
                16L to listOf(15),
                15L to listOf(12),
                12L to listOf(11, 10),
                11L to listOf(10),
                10L to listOf(7),
                7L to listOf(6, 5, 4),
                6L to listOf(5, 4),
                5L to listOf(4),
                4L to listOf(1),
                1L to listOf(0),
            ),
            findValidPredecessors(shortInput, 3))
    }
}
