package day5

import common.file

fun main() {
    println("Day 5")
    println("Highest seat ID is ${part1()}")
    println("Missing seat ID is ${part2()}")
}

val seats by lazy {
    file("day5/seats.txt").readLines().map(::parseSeatCode)
}

private fun part1() = seats.reduce { acc, seat ->
    if (seat.seatId > acc.seatId) seat else acc
}.seatId

private fun part2() = findMissingSeatId(seats)
