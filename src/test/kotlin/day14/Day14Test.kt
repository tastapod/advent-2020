package day14

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day14Test {
    @Test
    fun `creates masking function`() {
        val mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"
        val fMask = maskingFunction(mask)

        assertEquals(73, fMask(11))
        assertEquals(101, fMask(101))
        assertEquals(64, fMask(0))
    }

    @Test
    fun `executes program`() {
        val program = """
            mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
            mem[8] = 11
            mem[7] = 101
            mem[8] = 0
        """.trimIndent()

        val machine = runProgram(program)
        assertEquals(165, machine.totalValues())
    }
}

