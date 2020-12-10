package day6

import common.file

fun main() {
    println("Day 6")
    println("${part1()} questions answered 'yes'")
    println("${part2()} questions answered 'yes' by everyone")
}

fun part2(): Int = sumOfCommonAnswers(answers)

val answers by lazy {
    file("day6/answers.txt").readText()
}

fun part1() = sumOfAnswers(answers)
