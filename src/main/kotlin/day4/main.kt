package day4

import java.io.File

fun main() {
    println("${part1()} valid passports")
}

fun part1(): Int {
    val passportData = File(ClassLoader.getSystemResource("day4/part1.txt").file).readText().trim()
    return parsePassportBatch(passportData).map(::parsePassport).count(::isValidPassport)
}
