package day9

import common.file

fun main() {
    println("Day 9")
    println("${part1()} is the first invalid value")
}

val input by lazy {
    file("day9/input.txt").readLines().map(String::toLong)
}

fun part1() = firstInvalidXmasNumber(input, 25)
