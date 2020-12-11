package day1

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day1Test {
    @Test
    fun `finds numbers that add up to 2020`() {
        // given
        val numbers = setOf<Long>(2000, 20, 2)

        // when
        val result = findAddends(2020, numbers, 2)

        // then
        assertEquals(setOf<Long>(20, 2000), result.toSet())
    }
}
