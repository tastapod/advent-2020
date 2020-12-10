package day8

import common.file

fun main() {
    println("Day 8")
    println("acc = ${part1()}")
}

private val listing by lazy {
    file("day8/listing.txt").readLines()
}


fun part1() = Handheld(listing).execute().acc
