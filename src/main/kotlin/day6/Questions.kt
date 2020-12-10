package day6

fun sumOfAnswers(input: String) =
    input.split(Regex("""^$""", RegexOption.MULTILINE)).sumOf {
        it.trim().lines().fold(setOf<Char>()) { acc, line ->
            acc.plus(line.asIterable())
        }.size
    }

fun sumOfCommonAnswers(input: String) =
    Regex("""^$""", RegexOption.MULTILINE).split(input).sumOf { answerGroup ->
        answerGroup.trim().lines().map { answerLine -> answerLine.asIterable().toSet() }
            .reduce { acc, answers -> acc.intersect(answers) }
            .size
    }
