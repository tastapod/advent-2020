package day10

import common.file
import java.lang.Integer.parseInt

fun main() {
    println("Day 10")
    println("Product = ${part1()}")
    println("${part2()} valid paths")
}

val input by lazy { file("day10/input.txt").readLines().map(::parseInt) }

fun part1(): Int {
    val deltas = countJoltageDeltas(input)
    return deltas[1]!! * deltas[3]!!
}

fun part2() = countValidSequences(adapterList(input))
