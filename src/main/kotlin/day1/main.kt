package day1

import common.file
import java.lang.Math.multiplyExact

fun main() {
    println("Day 1")
    println("Product of 2 addends = ${part1()}")
    println("Product of 3 addends = ${part2()}")
}

private val numbers by lazy {
    file("day1/numbers.txt").readLines().map(String::toLong).toSet()
}

fun part1() = findAddends(2020, numbers, 2).reduce(::multiplyExact)

fun part2() = findAddends(2020, numbers, 3).reduce(::multiplyExact)
