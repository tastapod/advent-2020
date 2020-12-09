package day5

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day5Test {
    @Test
    fun `parses seat code`() {
        assertEquals(Seat(44, 5, 357), parseSeatCode("FBFBBFFRLR"))
        assertEquals(Seat(70, 7, 567), parseSeatCode("BFFFBBFRRR"))
        assertEquals(Seat(14, 7, 119), parseSeatCode("FFFBBBFRRR"))
        assertEquals(Seat(102, 4, 820), parseSeatCode("BBFFBBFRLL"))
    }
}

