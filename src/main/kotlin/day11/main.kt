package day11

import common.file
import java.lang.Integer.parseInt

fun main() {
    println("Day 11")
    println("${part1()} immediate occupied seats")
    println("${part2()} visible occupied seats")
}

val input by lazy { grid(file("day11/input.txt").readText()) }

fun part1() = countOccupiedSeats(stabliiseGrid(input)!!)

fun part2() = countOccupiedSeats(
    stabliiseSeatMap(
        buildSeatMap(
            findSeats(input), input.size, input[0].size
        )
    )!!
)
