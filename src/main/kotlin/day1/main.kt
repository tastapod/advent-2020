package day1

import java.io.File

fun main() {
    println(part1())
    println(part2())
}

private val numbers by lazy {
    File(ClassLoader.getSystemResource("day1/numbers.txt").file)
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
