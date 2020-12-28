package day13

import common.file
import java.lang.Math.multiplyExact

fun main() {
    println("Day 13")
    println("Part 1: ${part1()}")
    println("Product = ${parseInput(input).second.map(Int::toLong).reduce(::multiplyExact)}")
    println("Part 2: ${part2()}")
}

val input by lazy { file("day13/input.txt").readText() }

fun part1(): Int {
    val input = parseInput(input)
    val nextShuttle = findNextShuttle(input.first, input.second)
    return nextShuttle.first * nextShuttle.second
}

fun part2() = findSequentialShuttleTimes(parseInputIndexed(input.lines()[1]).also {println(it)})
