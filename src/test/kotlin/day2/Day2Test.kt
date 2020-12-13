package day2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day2Test {
    @Test
    fun `checks count of letters in password`() {
        // given
        val checker = PasswordChecker().setMinMaxRule('a', 1, 3)

        assertEquals(true, checker.isValid("abcde"))
        assertEquals(false, checker.isValid("bbcde"))
    }

    @Test
    fun `parses policy and password`() {
        val actual = PuzzleLine.parseMinMaxPuzzleLine("1-3 a: abcde")
        assertEquals("abcde", actual.password)
        assertEquals(MinMaxRule('a', 1, 3), actual.checker.rule)
    }

    @Test
    fun `checks either-or occurrence in password`() {
        // given
        val checker = PasswordChecker().setEitherOrRule('a', 1, 3)

        assertEquals(true, checker.isValid("abcde"))
        assertEquals(true, checker.isValid("cbade"))
        assertEquals(false, checker.isValid("abade"))
        assertEquals(false, checker.isValid("bbcde"))
    }
}
