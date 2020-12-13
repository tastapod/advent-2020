package day2

import common.file

fun main() {
    println("Day 2")
    println("${part1()} valid min/max passwords")
    println("${part2()} valid either/or passwords")
}

fun part1(): Int {
    return countValidPasswords(PuzzleLine::parseMinMaxPuzzleLine)
}

fun part2(): Int {
    return countValidPasswords(PuzzleLine::parseEitherOrPuzzleLine)
}

private fun countValidPasswords(parse: (String) -> PuzzleLine) =
    file("day2/records.txt")
        .readLines()
        .map(parse)
        .count(PuzzleLine::isValid)

