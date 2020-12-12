package day10

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day10Test {

    @Test
    fun `finds deltas in a valid chain of adapters`() {
        val input = """
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
        assertEquals(mapOf(1L to 7, 3L to 5), countJoltageDeltas(input))
    }

    fun `finds deltas in a longer valid chain of adapters`() {
        val input = """
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
        assertEquals(mapOf(1L to 22, 3L to 10), countJoltageDeltas(input))
    }
}