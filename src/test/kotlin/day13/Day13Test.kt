package day13

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day13Test {
    @Test
    fun `finds next shuttle`() {
        val input = """
            939
            7,13,x,x,59,x,31,19
        """.trimIndent()

        val parsed = parseInput(input)
        assertEquals(Pair(939, listOf(7, 13, 59, 31, 19)), parsed)

        assertEquals(Pair(5, 59), findNextShuttle(parsed.first, parsed.second))
    }

    @Test
    fun `finds sequential shuttle times`() {
        val input = """
            33
            47,x,61
        """.trimIndent()

        assertEquals(1222, findSequentialShuttleTimes(parseInputIndexed(input.lines()[1])))
    }
}
