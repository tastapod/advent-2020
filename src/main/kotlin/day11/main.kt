package day11

import common.file
import day11.part1.stabliiseGrid
import day11.part2.buildSeatMap
import day11.part2.countOccupiedSeats
import day11.part2.findSeats
import day11.part2.stabliiseSeatMap

fun main() {
    println("Day 11")
    println("${part1()} immediate occupied seats")
    println("${part2()} visible occupied seats")
}

val input by lazy { grid(file("day11/input.txt").readText()) }

fun part1() = day11.part1.countOccupiedSeats(stabliiseGrid(input)!!)

fun part2(): Int {
    val seatMap = buildSeatMap(findSeats(input), input.size, input[0].size)
    return countOccupiedSeats(stabliiseSeatMap(seatMap)!!)
}
