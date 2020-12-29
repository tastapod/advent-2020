package day14

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day14Test {
    @Test
    fun `creates masking function`() {
        val mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"
        val applyMask = maskingFunction(mask)

        assertEquals(73, applyMask(11))
        assertEquals(101, applyMask(101))
        assertEquals(64, applyMask(0))
    }

    @Test
    fun `executes program`() {
        val program = """
            mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
            mem[8] = 11
            mem[7] = 101
            mem[8] = 0
        """.trimIndent()

        val emu = runProgram(program, MaskingEmulator())
        assertEquals(165, emu.totalValues)
    }

    @Test
    fun `expands floating mask`() {
        val mask = "000F00000000000000000000000000X1001X"

        val expected = setOf(
            "XXX0XXXXXXXXXXXXXXXXXXXXXXXXXX01XX10",
            "XXX0XXXXXXXXXXXXXXXXXXXXXXXXXX01XX11",
            "XXX0XXXXXXXXXXXXXXXXXXXXXXXXXX11XX10",
            "XXX0XXXXXXXXXXXXXXXXXXXXXXXXXX11XX11",
            "XXX1XXXXXXXXXXXXXXXXXXXXXXXXXX01XX10",
            "XXX1XXXXXXXXXXXXXXXXXXXXXXXXXX01XX11",
            "XXX1XXXXXXXXXXXXXXXXXXXXXXXXXX11XX10",
            "XXX1XXXXXXXXXXXXXXXXXXXXXXXXXX11XX11",
        )

        assertEquals(expected, unrollFloatingMask(mask))
    }

    @Test
    fun `runs program with floating emulator`() {
        val program = """
            mask = 000000000000000000000000000000X1001X
            mem[42] = 100
            mask = 00000000000000000000000000000000X0XX
            mem[26] = 1
        """.trimIndent()

        assertEquals(208, runProgram(program, FloatingEmulator()).totalValues)
    }
}

