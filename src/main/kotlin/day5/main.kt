package day5

import java.io.File

fun main() {
    println("Highest seat ID is ${part1()}")
}

val seats by lazy {
    File(ClassLoader.getSystemResource("day5/seats.txt").file)
        .readLines().map(::parseSeatCode)
}

private fun part1() = seats.reduce { acc, seat ->
    if (seat.seatId > acc.seatId) seat else acc
}.seatId
