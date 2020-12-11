package day8

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day8Test {
    @Test
    fun `runs a program`() {
        val program = listOf("acc 2")
        assertEquals(ExitState(2, ExitCondition.END_OF_PROGRAM), Handheld(program).execute())
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
        assertEquals(ExitState(5, ExitCondition.LOOP_DETECTED), Handheld(program.lines()).execute())
    }

    @Test
    fun `mutates JMP to NOP to resolve loop`() {
        val program = """
            acc +1
            jmp -1 // converts to nop to escape
        """.trimIndent()

        assertEquals(ExitState(1, ExitCondition.END_OF_PROGRAM), Handheld(program.lines()).mutateUntilExit())
    }

    @Test
    fun `mutates NOP to JMP to resolve loop`() {
        val program = """
            acc +1
            nop +2 // converts to jmp to escape
            jmp -2
        """.trimIndent()

        assertEquals(ExitState(1, ExitCondition.END_OF_PROGRAM), Handheld(program.lines()).mutateUntilExit())
    }
}
