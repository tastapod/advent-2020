package day2

import java.io.File
import kotlin.reflect.KFunction1

fun main() {
    println(part1())
    println(part2())
}

fun part1(): Int {
    return countValidPasswords(::parseMinMaxPuzzleLine)
}

fun part2(): Int {
    return countValidPasswords(::parseEitherOrPuzzleLine)
}

private fun countValidPasswords(parse: KFunction1<String, PuzzleLine>) =
    File(ClassLoader.getSystemResource("day2/records.txt").file)
        .readLines().map(parse).count(PuzzleLine::isValid)

