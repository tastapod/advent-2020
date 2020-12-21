package day13

import common.file
import javax.print.attribute.IntegerSyntax

fun main() {
    println("Day 13")
    println("Part 1: ${part1()}")
}

val input by lazy { file("day13/input.txt").readText() }

fun part1(): Int {
    val input = parseInput(input)
    val nextShuttle = findNextShuttle(input.first, input.second)
    return nextShuttle.first * nextShuttle.second
}

