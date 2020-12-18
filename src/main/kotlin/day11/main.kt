package day11

import common.file
import java.lang.Integer.parseInt

fun main() {
    println("Day 11")
    println("${part1()} occupied seats")
}

val input by lazy { grid(file("day11/input.txt").readText()) }

fun part1() = countOccupiedSeats(stabliiseGrid(input)!!)
