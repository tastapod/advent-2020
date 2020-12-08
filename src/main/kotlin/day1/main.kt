package day1

import java.io.File

fun main() {
    println(part1())
    println(part2())
}

fun part1(): Int {
    val addends = findAddends(2020, readNumbers(), 2)
    return addends?.reduce(Math::multiplyExact)!!
}

private fun readNumbers(): Set<Int> {
    val numbers = File(ClassLoader.getSystemResource("day1/numbers.txt").file)
        .readLines().map(String::toInt).toSet()
    return numbers
}

fun part2(): Int {
    val addends = findAddends(2020, readNumbers(), 3)
    return addends?.reduce(Math::multiplyExact)!!
}
