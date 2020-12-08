package day3

import java.io.File

fun main() {
    println("${part1()} trees found")
}

fun part1(): Int {
    return TreesMap(File(ClassLoader.getSystemResource("day3/part1.txt").file).readText().trim()).countTrees(3, 1)
}
