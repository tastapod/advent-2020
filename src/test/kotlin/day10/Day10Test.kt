package day10

import org.junit.jupiter.api.Test
import java.lang.Integer.parseInt
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
        """.trimIndent().lines().map(::parseInt)

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
        """.trimIndent().lines().map(::parseInt)

    @Test
    fun `finds deltas in a valid chain of adapters`() {
        assertEquals(mapOf(1 to 7, 3 to 5), countJoltageDeltas(adapterList(shortInput)))
    }

    @Test
    fun `finds deltas in a longer valid chain of adapters`() {
        assertEquals(mapOf(1 to 22, 3 to 10), countJoltageDeltas(adapterList(longerInput)))
    }

    @Test
    fun `removes item from a list`() {
        assertEquals(listOf(11, 33, 44), without(listOf(11, 22, 33, 44), 1))
        assertEquals(listOf(22, 33, 44), without(listOf(11, 22, 33, 44), 0))
    }

    @Test
    fun `finds all valid backlinks in short valid chain`() {
        assertEquals(
            mapOf(
                22 to listOf(19),
                19 to listOf(16),
                16 to listOf(15),
                15 to listOf(12),
                12 to listOf(11, 10),
                11 to listOf(10),
                10 to listOf(7),
                7 to listOf(6, 5, 4),
                6 to listOf(5, 4),
                5 to listOf(4),
                4 to listOf(1),
                1 to listOf(0),
            ),
            findBackLinks(adapterList(shortInput), 3)
        )
    }

    @Test
    fun `counts valid sequences`() {
        assertEquals(1, countValidSequences(adapterList(2)))
        assertEquals(7, countValidSequences(adapterList(1, 2, 3, 4)))
        assertEquals(8, countValidSequences(adapterList(shortInput)))
        assertEquals(19208, countValidSequences(adapterList(longerInput)))
    }
}
