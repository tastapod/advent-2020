package day2

import java.io.File

fun main() {
    println("Day 2")
    println("${part1()} valid min/max passwords")
    println("${part2()} valid either/or passwords")
}

fun part1(): Int {
    return countValidPasswords(::parseMinMaxPuzzleLine)
}

fun part2(): Int {
    return countValidPasswords(::parseEitherOrPuzzleLine)
}

private fun countValidPasswords(parse: (String) -> PuzzleLine) =
    File(ClassLoader.getSystemResource("day2/records.txt").file)
        .readLines()
        .map(parse)
        .count(PuzzleLine::isValid)

