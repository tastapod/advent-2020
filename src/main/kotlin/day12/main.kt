package day12

import common.file

fun main() {
    println("Day 12")
    println("${part1()} is the Manhattan distance")
}

val input by lazy { file("day12/input.txt").readLines() }

fun part1() = Ship(Direction.EAST, Position(0, 0)).navigate(input).manhattenDistance()
