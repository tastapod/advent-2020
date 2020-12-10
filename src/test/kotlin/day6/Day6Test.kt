package day6

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day6Test {
    private val answers = """
        abc
    
        a
        b
        c
    
        ab
        ac
    
        a
        a
        a
        a
    
        b
        """.trimIndent()

    @Test
    fun `sums unique answers`() {
        assertEquals(11, sumOfAnswers(answers))
    }

    @Test
    fun `sums common answers`() {
        assertEquals(6, sumOfCommonAnswers(answers))
    }
}
