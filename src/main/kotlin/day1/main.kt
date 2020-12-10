package day1

import common.file

fun main() {
    println("Day 1")
    println("Product of 2 addends = ${part1()}")
    println("Product of 3 addends = ${part2()}")
}

private val numbers by lazy {
    file("day1/numbers.txt")
        .readLines().map(String::toInt).toSet()
}

fun part1(): Int {
    val addends = findAddends(2020, numbers, 2)
    return addends?.reduce(Math::multiplyExact)!!
}

fun part2(): Int {
    val addends = findAddends(2020, numbers, 3)
    return addends?.reduce(Math::multiplyExact)!!
}
