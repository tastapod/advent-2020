package day3

import java.io.File

fun main() {
    println("${part1()} trees found")
    println("${part2()} trees product")
}

fun part1(): Int {
    return readMap().countTrees(3, 1)
}

private fun readMap() = TreesMap(File(ClassLoader.getSystemResource("day3/part1.txt").file).readText().trim())

fun part2(): Long {
    val map = readMap()
    return listOf(
        Pair(1, 1),
        Pair(3, 1),
        Pair(5, 1),
        Pair(7, 1),
        Pair(1, 2)
    ).fold(1L) { acc: Long, step: Pair<Int, Int> ->
        val count = map.countTrees(step.first, step.second)
        println("count = $count")
        acc * count
    }
}