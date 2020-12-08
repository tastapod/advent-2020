package day2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day2Test {
    @Test
    fun `checks count of letters in password`() {
        // given
        val checker = Checker().setRule('a', 1, 3)

        assertEquals(true, checker.isValid("abcde"))
        assertEquals(false, checker.isValid("bbcde"))
    }

    @Test
    fun `parses policy and password`() {
        val actual = parsePuzzleLine("1-3 a: abcde")
        assertEquals("abcde", actual.password)
        assertEquals(MinMaxRule('a', 1, 3), actual.checker.rule)
    }
}
