package day2

import java.lang.Integer.parseInt

data class PuzzleLine(val checker: Checker, val password: String) {
    fun isValid() = checker.isValid(password)
}

fun parseMinMaxPuzzleLine(puzzleInput: String): PuzzleLine {
    val regex = Regex("""(\d+)-(\d+) (\w): (\w+)""")
    val result = regex.matchEntire(puzzleInput)
    val (min, max, char, password) = result!!.destructured
    return PuzzleLine(Checker().setMinMaxRule(char[0], parseInt(min), parseInt(max)), password)
}

fun parseEitherOrPuzzleLine(puzzleInput: String): PuzzleLine {
    val regex = Regex("""(\d+)-(\d+) (\w): (\w+)""")
    val result = regex.matchEntire(puzzleInput)
    val (pos1, pos2, char, password) = result!!.destructured
    return PuzzleLine(Checker().setEitherOrRule(char[0], parseInt(pos1), parseInt(pos2)), password)
}
