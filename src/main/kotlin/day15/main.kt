package day15

fun main() {
    println("Day 15")
    println("Part 1: ${Game.play2020Turns(listOf(1,12,0,20,8,16))}")
    println("Part 2: ${part2()}")
}

fun part2() =
    Game.startingWith(listOf(1,12,0,20,8,16))
        .playUntil { g -> g.turn == 30000001 }
        .previous.value
