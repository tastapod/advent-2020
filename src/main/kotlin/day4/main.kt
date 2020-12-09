package day4

import java.io.File
import java.lang.Integer.parseInt

fun main() {
    println("${part1()} valid passports")
    println("${part2()} validated passports")
}

private val passports by lazy {
    val passportData = File(ClassLoader.getSystemResource("day4/part1.txt").file).readText().trim()
    parsePassportBatch(passportData).map(::parsePassport)
}

fun part1() = passports.count(::isValidPassport)

fun part2(): Int {
    val checker = PassportChecker()
        .withRule(Rule("byr") {
            Regex("""^\d{4}$""").matches(it) && parseInt(it) in 1920..2002
        })
        .withRule(Rule("iyr") {
            Regex("""^\d{4}$""").matches(it) && parseInt(it) in 2010..2020
        })
        .withRule(Rule("eyr") {
            Regex("""^\d{4}$""").matches(it) && parseInt(it) in 2020..2030
        })
        .withRule(Rule("hgt") {
            Regex("""^(\d+)cm$""").matchEntire(it)?.run { parseInt(groupValues[1]) in 150..193 } ?: false ||
            Regex("""^(\d+)in$""").matchEntire(it)?.run { parseInt(groupValues[1]) in 59..76 } ?: false
        })
        .withRule(Rule("hcl") {
            Regex("""^#[0-9a-f]{6}$""").matches(it)
        })
        .withRule(Rule("ecl") {
            it in setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
        })
        .withRule(Rule("pid") {
            Regex("""^\d{9}$""").matches(it)
        })

    return passports.count { checker.isValid(it) }
}