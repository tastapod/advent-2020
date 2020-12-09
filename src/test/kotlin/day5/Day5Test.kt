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

    @Test
    fun `finds missing seat`() {
        val seats = listOf(
            Seat(0, 0, 3),
            Seat(0, 0, 2),
            Seat(0, 0, 1),
            // Seat 4 missing
            Seat(0, 0, 5),
            Seat(0, 0, 6),
        )
        assertEquals(4, findMissingSeatId(seats))
    }
}

