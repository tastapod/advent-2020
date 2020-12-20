package day11

typealias Grid = List<List<Char>>

fun grid(input: String): Grid =
    input.trimIndent().lines().map(String::toList)
