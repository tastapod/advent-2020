package day2

import java.io.File

fun main() {
    println(part1())
}

fun part1(): Int {
    return File(ClassLoader.getSystemResource("day2/records.txt").file)
        .readLines().map(::parsePuzzleLine).fold(0) {
            acc: Int, line: PuzzleLine ->
            acc + if (line.isValid()) 1 else 0
        }
}
