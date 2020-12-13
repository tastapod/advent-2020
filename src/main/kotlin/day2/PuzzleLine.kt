package day2

import java.lang.Integer.parseInt

data class PuzzleLine(val checker: PasswordChecker, val password: String) {
    fun isValid() = checker.isValid(password)

    companion object {
        fun parseMinMaxPuzzleLine(puzzleInput: String): PuzzleLine {
            val (min, max, char, password) =
                Regex("""(\d+)-(\d+) (\w): (\w+)""")
                    .matchEntire(puzzleInput)!!
                    .destructured
            return PuzzleLine(PasswordChecker().setMinMaxRule(char[0], parseInt(min), parseInt(max)), password)
        }

        fun parseEitherOrPuzzleLine(puzzleInput: String): PuzzleLine {
            val (pos1, pos2, char, password) =
                Regex("""(\d+)-(\d+) (\w): (\w+)""")
                    .matchEntire(puzzleInput)!!
                    .destructured
            return PuzzleLine(PasswordChecker().setEitherOrRule(char[0], parseInt(pos1), parseInt(pos2)), password)
        }
    }
}
