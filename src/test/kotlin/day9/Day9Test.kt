package day9

import org.junit.jupiter.api.Test
import java.lang.Integer.parseInt
import kotlin.test.assertEquals

class Day9Test {
    @Test
    fun `finds number without sum in window`() {
        val input = """
            35
            20
            15
            25
            47
            40
            62
            55
            65
            95
            102
            117
            150
            182
            127
            219
            299
            277
            309
            576
        """.trimIndent().lines().map(String::toLong)

        assertEquals(127, firstInvalidXmasNumber(input, 5))
    }
}

