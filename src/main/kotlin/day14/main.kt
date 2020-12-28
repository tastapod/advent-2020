package day14

import common.file
import java.lang.Math.multiplyExact

fun main() {
    println("Day 14")
    println("Part 1: ${part1()}")
//    println("Part 2: ${part2()}")
}

val input by lazy { file("day14/input.txt").readText().trim() }

fun part1() = runProgram(input).totalValues()
