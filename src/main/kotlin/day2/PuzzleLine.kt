package day2

import java.lang.Integer.parseInt

data class PuzzleLine(val checker: Checker, val password: String) {
    fun isValid() = checker.isValid(password)
}

fun parsePuzzleLine(puzzleInput: String): PuzzleLine {
    val regex = Regex("""(\d+)-(\d+) (\w): (\w+)""")
    val result = regex.matchEntire(puzzleInput)
    val (min, max, char, password) = result!!.destructured
    return PuzzleLine(Checker().setRule(char[0], parseInt(min), parseInt(max)), password)
}