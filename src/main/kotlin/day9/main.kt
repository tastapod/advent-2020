package day9

import common.file

fun main() {
    println("Day 9")
    println("${part1()} is the first invalid value")
    println("${part2()} is the sum of min & max values")
}

val input by lazy {
    file("day9/input.txt").readLines().map(String::toLong)
}

fun part1() = findFirstInvalidXmasNumber(input, 25)

fun part2(): Long {
    val range = findRangeForInvalidValue(input, findFirstInvalidXmasNumber(input, 25))
    return range.minOrNull()!! + range.maxOrNull()!!
}
