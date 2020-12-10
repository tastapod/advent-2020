package day7

import java.io.File

fun main() {
    println("Day 7")
    println("${part1()} bags can contain a shiny gold bag")
}

val rulesText by lazy {
    File(ClassLoader.getSystemResource("day7/rules.txt").file).readText()
}

fun part1() = possibleContainers(parseBagRulesByContainer(rulesText),"shiny gold").size
