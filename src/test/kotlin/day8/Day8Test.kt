package day8

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day8Test {
    @Test
    fun `runs a program`() {
        val program = listOf("acc 2")

        assertEquals(2, Handheld(program).execute().acc)
    }

    @Test
    fun `runs a program until it detects a loop`() {
        val program = """
            nop +0
            acc +1
            jmp +4
            acc +3
            jmp -3
            acc -99
            acc +1
            jmp -4
            acc +6
        """.trimIndent()

        assertEquals(5, Handheld(program.lines()).execute().acc)
    }
}
