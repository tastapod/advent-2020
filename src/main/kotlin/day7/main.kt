package day7

import common.file

fun main() {
    println("Day 7")
    println("${part1()} bags can contain a shiny gold bag")
    println("${part2()} bags are required in a shiny gold bag")
}

val rulesText by lazy {
    file("day7/rules.txt").readText()
}

fun part1() = possibleContainers(parseBagRulesByContainer(rulesText),"shiny gold").size

fun part2() = countContents(parseBagRulesByContents(rulesText), "shiny gold")
