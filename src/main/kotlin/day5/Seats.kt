package day5

import java.lang.Integer.parseInt

data class Seat(val row: Int, val column: Int, val seatId: Int = row * 8 + column)

fun parseSeatCode(seatCode: String): Seat {
    require(seatCode.length == 10) { "Seat code '$seatCode' must be 10 characters long" }
    val (rowCode, colCode) = seatCode.chunked(7)
    return Seat(
        parseInt(rowCode.replace('F', '0').replace('B', '1'), 2),
        parseInt(colCode.replace('L', '0').replace('R', '1'), 2),
    )
}
