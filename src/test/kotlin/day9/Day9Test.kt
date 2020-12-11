package day9

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day9Test {
    private val input = """
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

    @Test
    fun `finds number without sum in window`() {
        assertEquals(127, findFirstInvalidXmasNumber(input, 5))
    }

    @Test
    fun `finds contiguous range of numbers that add up to given value`() {
        assertEquals(listOf<Long>(15, 25, 47, 40), findRangeForInvalidValue(input, 127))
    }
}
