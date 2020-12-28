package day13

import java.lang.Long.parseLong

fun findNextShuttle(earliest: Int, shuttles: List<Int>) =
    shuttles.fold(emptyMap<Int, Int>()) { acc, shuttle ->
        val delay = shuttle - (earliest % shuttle)
        acc + Pair(delay, shuttle)
    }.minByOrNull { (delay, _) -> delay }!!.toPair()

fun parseInput(input: String): Pair<Int, List<Int>> {
    val lines = input.lines()
    return Pair(
        Integer.parseInt(lines[0]),
        lines[1].split(",").filterNot { it == "x" }.map(Integer::parseInt)
    )
}

fun parseInputIndexed(input: String) =
    input.split(",").foldIndexed(emptyList<Pair<Int, Long>>()) { i, acc, it ->
        if (it == "x") acc
        else acc + Pair(i, parseLong(it))
    }

data class Goal(val delta: Long, val modulus: Long) {
    fun matches(time: Long) = (time - delta) % modulus == 0L
}

/**
 * For (0, s0), (1, s1, (2, s2), (3, s3), (4, s4), ...
 *
 * find n such that
 *  s0 divides n-0
 *  s1 divides n-1
 *  s2 divides n-2
 *  ...
 *
 *  n0 = s0 + d0 // can add any multiple of s0 to this
 *
 *  n1 = keep adding s0 until n-d1 % s1  == 0
 *  n2 = keep adding LCM(s0, s1) until n-d2 % s2 == 0
 *  n3 = keep adding LCM(s0, s1, s2) until n-d3 % s3 == 0
 *  ...
 */
fun findSequentialShuttleTimes(shuttles: List<Pair<Int, Long>>): Long {
    val goals = shuttles.map { (first, second) -> Goal(first.toLong(), second) }
        .sortedByDescending { it.modulus }

    println("Searching for $goals")

    val start = goals[0].modulus + goals[0].delta
    val increment = goals[0].modulus

    tailrec fun findValidTime2(time: Long, increment: Long, remainingGoals: List<Goal>): Long =
        if (remainingGoals.all { it.matches(time) }) time
        else {
            val goal = remainingGoals.first()
            if (goal.matches(time)) {
                findValidTime2(time, findLCM(increment, goal.modulus), remainingGoals.drop(1))
            } else {
                findValidTime2(time + increment, increment, remainingGoals)
            }
        }

    tailrec fun findValidTime(time: Long, increment: Long, remainingGoals: List<Goal>): Long =
        if (remainingGoals.all { it.matches(time) }) time
        else findValidTime(time + increment, increment, remainingGoals)

            println("Checking from $start in increments of $increment")

    return findValidTime(start, increment, goals.drop(1))
}


fun findLCM(a: Long, b: Long): Long {
    tailrec fun findGCD(a: Long, b: Long): Long =
        if (a == 0L) b else findGCD(b % a, a)

    return a * b / findGCD(a, b)
}

